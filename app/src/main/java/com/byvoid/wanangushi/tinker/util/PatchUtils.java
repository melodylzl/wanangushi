package com.byvoid.wanangushi.tinker.util;

import android.text.TextUtils;

import com.byvoid.wanangushi.app.UtilsApplicationLike;
import com.byvoid.wanangushi.constant.HawkConstants;
import com.byvoid.wanangushi.http.BaseCallBack;
import com.byvoid.wanangushi.http.HttpService;
import com.byvoid.wanangushi.tinker.model.PatchInfo;
import com.byvoid.wanangushi.utils.FilePathManager;
import com.byvoid.wanangushi.utils.LogUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.orhanobut.hawk.Hawk;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

/**
 * @author liuziliang
 * @date 2018/10/23
 */
public class PatchUtils {

    /**
     * 获取补丁包
     */
    public static void getPatchInfo(){
        HttpService.getPatchInfo(new BaseCallBack<PatchInfo>() {
            @Override
            public void onSuccess(PatchInfo data, String msg) {
                String patchUrl = data.getUrl();
                if (TextUtils.isEmpty(patchUrl)){
                    return;
                }
                Integer cachePatchUrlHashCode = Hawk.get(HawkConstants.PATCH_KEY);
                if (cachePatchUrlHashCode == null || patchUrl.hashCode() != cachePatchUrlHashCode){
                    downloadPatch(patchUrl);
                }
            }

            @Override
            public void onFail(String msg, int code) {

            }
        });
    }


    /**
     * 下载补丁包
     * @param patchUrl 补丁包url
     */
    private static void downloadPatch(final String patchUrl){
        final String downloadFilePath = FilePathManager.getPatchPath() + "/" + patchUrl.hashCode() + ".apk";
        FileDownloader.getImpl().create(patchUrl)
                .setPath(downloadFilePath)
                .setListener(new FileDownloadSampleListener(){
                    @Override
                    protected void completed(BaseDownloadTask task) {
                        super.completed(task);
                        TinkerInstaller.onReceiveUpgradePatch(UtilsApplicationLike.getAppContext(), downloadFilePath);
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        super.error(task, e);
                        LogUtils.i("downloadPatch","e = " + e);
                    }
                })
                .start();
    }


}
