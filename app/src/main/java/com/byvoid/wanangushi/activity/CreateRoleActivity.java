package com.byvoid.wanangushi.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.base.BaseActivity;
import com.byvoid.wanangushi.base.TakePhotoActivity;
import com.byvoid.wanangushi.eventbus.EventAddRole;
import com.byvoid.wanangushi.http.BaseCallBack;
import com.byvoid.wanangushi.http.HttpService;
import com.byvoid.wanangushi.model.BaseResponse;
import com.byvoid.wanangushi.utils.TakePhotoUtils;

import org.devio.takephoto.model.TResult;
import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

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
    @BindView(R.id.completeBtn)
    protected Button mCompleteBtn;

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
        mCompleteBtn.setOnClickListener(this);
    }

    @Override
    protected void handleOnClick(View view) {
        super.handleOnClick(view);
        switch (view.getId()){
            case R.id.completeBtn:
                String name = mNameEt.getText().toString();
                if (TextUtils.isEmpty(mAvatarUrl) || TextUtils.isEmpty(name)){
                    return;
                }
                HttpService.createRole(name, mAvatarUrl, new BaseCallBack<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse data, String msg) {
                        EventBus.getDefault().post(new EventAddRole());
                    }

                    @Override
                    public void onFail(String msg, int code) {

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
