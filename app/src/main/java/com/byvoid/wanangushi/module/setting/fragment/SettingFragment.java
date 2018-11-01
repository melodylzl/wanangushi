package com.byvoid.wanangushi.module.setting.fragment;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.byvoid.lib.utils.AppUtils;
import com.byvoid.lib.utils.ConfigUtils;
import com.byvoid.lib.utils.ResourceUtils;
import com.byvoid.lib.utils.ToastUtils;
import com.byvoid.wanangushi.BuildConfig;
import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.base.BaseFragment;
import com.byvoid.wanangushi.module.setting.model.UpdateInfo;
import com.byvoid.wanangushi.module.setting.mvp.IUpdateView;
import com.byvoid.wanangushi.module.setting.mvp.UpdatePresenter;
import com.byvoid.wanangushi.module.story.activity.CreateStoryActivity;
import com.byvoid.wanangushi.utils.DialogUtils;
import com.byvoid.wanangushi.utils.FilePathManager;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;

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
    @BindView(R.id.createStoryView)
    protected View mCreateStoryView;

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
        mCreateStoryView.setVisibility(BuildConfig.DEBUG ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mCheckUpdateView.setOnClickListener(this);
        mCreateStoryView.setOnClickListener(this);
    }

    @Override
    protected void handleOnClick(View view) {
        switch (view.getId()){
            case R.id.checkUpdateView:
                mUpdatePresenter.getUpdateInfo();
                break;
            case R.id.createStoryView:
                CreateStoryActivity.startToMe(getContext());
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
        if (versionCode < updateInfo.getVersionCode()){
            DialogUtils.show(getActivity(), updateInfo.getTitle(), updateInfo.getContent(), "更新", new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    downloadAndInstallApk(updateInfo.getUrl());
                }
            }, "取消", null);
        }else{
            ToastUtils.show(ResourceUtils.getString(R.string.is_latest_version));
        }

    }

    private void downloadAndInstallApk(String url){
        final String downloadFilePath = FilePathManager.getDownloadPath() + "/app.apk";
        FileDownloader.getImpl().create(url)
                .setPath(downloadFilePath)
                .setListener(new FileDownloadSampleListener(){
                    @Override
                    protected void completed(BaseDownloadTask task) {
                        super.completed(task);
                        AppUtils.installApk(getContext(),new File(downloadFilePath));
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        super.error(task, e);
                        ToastUtils.show("下载安装包失败");
                    }
                })
                .start();
    }
}
