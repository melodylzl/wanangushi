package com.byvoid.wanangushi.module.story.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.byvoid.lib.utils.ToastUtils;
import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.base.BaseResponse;
import com.byvoid.wanangushi.base.TakePhotoActivity;
import com.byvoid.wanangushi.http.BaseCallBack;
import com.byvoid.wanangushi.http.HttpService;
import com.byvoid.wanangushi.module.qiniu.QiniuUpload;
import com.byvoid.wanangushi.module.story.eventbus.EventAddRole;
import com.byvoid.wanangushi.utils.DialogUtils;
import com.byvoid.wanangushi.utils.LogUtils;
import com.byvoid.wanangushi.utils.TakePhotoUtils;

import org.devio.takephoto.model.TResult;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @author melody
 * @date 2018/8/21
 */
public class CreateRoleActivity extends TakePhotoActivity{

    @BindView(R.id.toolBar)
    protected Toolbar mToolBar;
    @BindView(R.id.avatarIv)
    protected ImageView mAvatarIv;
    @BindView(R.id.nameEt)
    protected EditText mNameEt;
    @BindView(R.id.publishTv)
    protected TextView mPublishTv;

    private String mAvatarUrl;

    public static void startToMe(Context context){
        Intent intent = new Intent(context,CreateRoleActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_role);
    }

    @Override
    protected void bindData() {
        super.bindData();
    }

    @Override
    protected void setListener() {
        super.setListener();
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAvatarIv.setOnClickListener(this);
        mPublishTv.setOnClickListener(this);
    }

    @Override
    protected void handleOnClick(View view) {
        super.handleOnClick(view);
        switch (view.getId()){
            case R.id.publishTv:
                final String name = mNameEt.getText().toString();
                if (TextUtils.isEmpty(mAvatarUrl) || TextUtils.isEmpty(name)){
                    ToastUtils.show("请添加角色头像或输入角色名称");
                    return;
                }
                List<String> dataList = new ArrayList<>();
                dataList.add(mAvatarUrl);
                final SweetAlertDialog sweetAlertDialog = DialogUtils.showProgressDialog(getContext(),"创建中...");
                QiniuUpload.uploadFile(dataList, new QiniuUpload.IUploadCallBack() {
                    @Override
                    public void onSuccess(HashMap<String, String> urlMaps) {
                        mAvatarUrl = urlMaps.get(mAvatarUrl);
                        HttpService.createRole(name, mAvatarUrl, new BaseCallBack<BaseResponse>() {
                            @Override
                            public void onSuccess(BaseResponse data, String msg) {
                                sweetAlertDialog.setTitleText("");
                                sweetAlertDialog.setConfirmText(msg);
                                sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                sweetAlertDialog.dismiss();
                                EventBus.getDefault().post(new EventAddRole());
                                finish();
                            }

                            @Override
                            public void onFail(String msg, int code) {
                                sweetAlertDialog.dismiss();
                                ToastUtils.show(msg);
                            }
                        });
                    }

                    @Override
                    public void onFail(String msg) {
                        ToastUtils.show(msg);
                        sweetAlertDialog.dismiss();
                        LogUtils.i("create role","onFail msg=" + msg);
                    }
                });
                break;
            case R.id.avatarIv:
                TakePhotoUtils.take(getTakePhoto(),true);
                break;
            default:
                break;
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        if (null == result || result.getImage() == null){
            return;
        }
        mAvatarUrl = result.getImage().getOriginalPath();
        Glide.with(this).load(mAvatarUrl).into(mAvatarIv);
    }
}
