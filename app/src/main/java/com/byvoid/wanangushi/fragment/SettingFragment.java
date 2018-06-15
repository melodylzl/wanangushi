package com.byvoid.wanangushi.fragment;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.base.BaseFragment;
import com.byvoid.wanangushi.http.HttpService;
import com.byvoid.wanangushi.model.UpdateInfo;
import com.byvoid.wanangushi.mvp.IBaseCallBack;
import com.byvoid.wanangushi.mvp.IUpdateView;
import com.byvoid.wanangushi.mvp.UpdateModel;
import com.byvoid.wanangushi.mvp.UpdatePresenter;
import com.byvoid.wanangushi.utils.ConfigUtils;
import com.byvoid.wanangushi.utils.DialogUtils;
import com.byvoid.wanangushi.utils.ResourceUtils;
import com.byvoid.wanangushi.utils.ToastUtils;

import butterknife.BindView;

/**
 * @author melody
 * @date 2018/6/14
 */
public class SettingFragment extends BaseFragment implements IUpdateView{

    @BindView(R.id.checkUpdateView)
    protected View mCheckUpdateView;
    @BindView(R.id.updateTv)
    protected TextView mUpdateTv;
    @BindView(R.id.versionTv)
    protected TextView mVersionTv;

    private UpdatePresenter mUpdatePresenter = new UpdatePresenter(this);

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void bindData() {
        super.bindData();
        mVersionTv.setText(ConfigUtils.getVersionName());
    }

    @Override
    protected void setListener() {
        super.setListener();
        mCheckUpdateView.setOnClickListener(this);
    }

    @Override
    protected void handleOnClick(View view) {
        switch (view.getId()){
            case R.id.checkUpdateView:
                mUpdatePresenter.getUpdateInfo();
                break;
            default:
                break;
        }
    }

    @Override
    public void showUpdateDialog(final UpdateInfo updateInfo) {
        if (null == getActivity()){
            return;
        }
        int versionCode = ConfigUtils.getVersionCode();
        if (versionCode <= updateInfo.getVersionCode()){
            DialogUtils.show(getActivity(), updateInfo.getTitle(), updateInfo.getContent(), "更新", new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    HttpService.downloadApk(getActivity(),updateInfo.getUrl());
                }
            }, "取消", null);
        }else{
            ToastUtils.show(ResourceUtils.getString(R.string.is_latest_version));
        }

    }
}
