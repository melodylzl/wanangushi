package com.byvoid.wanangushi.module.qiniu;

import com.byvoid.wanangushi.http.BaseResult;
import com.byvoid.wanangushi.http.HttpService;
import com.byvoid.wanangushi.module.qiniu.model.UploadTokenResult;
import com.byvoid.wanangushi.utils.LogUtils;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;



/**
 * @author melody
 * @date 2018/8/28
 */
public class QiniuUpload {

    private static UploadManager mUploadManager;

    public static synchronized UploadManager getUploadManager(){
        if (null == mUploadManager){
            Configuration config = new Configuration.Builder()
                    // 分片上传时，每片的大小。 默认256K
                    .chunkSize(512 * 1024)
                    // 启用分片上传阀值。默认512K
                    .putThreshhold(1024 * 1024)
                    // 链接超时。默认10秒
                    .connectTimeout(10)
                    // 是否使用https上传域名
                    .useHttps(true)
                    // 服务器响应超时。默认60秒
                    .responseTimeout(60)
                    // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                    .zone(FixedZone.zone2)
                    .build();
            mUploadManager = new UploadManager(config);
        }
        return mUploadManager;
    }

    public static void uploadFile(final List<String> dataList, final IUploadCallBack callBack){
        if (null == dataList || dataList.isEmpty()){
            return;
        }
        final HashMap<String,String> result = new HashMap<>(dataList.size());
        HttpService.getApiService().getQiniuUploadToken()
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseResult<UploadTokenResult>, String>() {

                    @Override
                    public String apply(BaseResult<UploadTokenResult> uploadTokenResultBaseResult) throws Exception {
                        return uploadTokenResultBaseResult.getData().getUploadToken();
                    }
                }).observeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final String token) {
                        Observable.fromIterable(dataList)
                                .subscribe(new Observer<String>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(String data) {
                                        uploadFile(data,token,result);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        callBack.onFail("上传文件失败,e="+e);
                                    }

                                    @Override
                                    public void onComplete() {
                                        callBack.onSuccess(result);
                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail("获取上传token失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private static void uploadFile(final String data, String token, final HashMap<String,String> result){
        getUploadManager().put(data, null, token,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject res) {
                        if (info.isOK()) {
                            result.put(data,key);
                            LogUtils.i("qiniu", "Upload Success");
                        } else {
                            LogUtils.i("qiniu", "Upload Fail");
                        }
                        LogUtils.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
                    }
                }, null);
    }


    public interface IUploadCallBack {

        void onSuccess(HashMap<String,String> urlMaps);

        void onFail(String msg);
    }



}
