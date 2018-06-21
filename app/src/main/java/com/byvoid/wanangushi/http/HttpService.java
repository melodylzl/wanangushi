package com.byvoid.wanangushi.http;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.byvoid.wanangushi.BuildConfig;
import com.byvoid.wanangushi.model.Story;
import com.byvoid.wanangushi.model.StoryDetail;
import com.byvoid.wanangushi.model.UpdateInfo;
import com.byvoid.wanangushi.utils.AppUtils;
import com.byvoid.wanangushi.utils.FileIOUtils;
import com.byvoid.wanangushi.utils.FilePathManager;
import com.byvoid.wanangushi.utils.LogUtils;
import com.byvoid.wanangushi.utils.ToastUtils;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author melody
 * @date 2018/4/9
 */
public class HttpService {

    private static HttpService INSTANCE;

    private Retrofit retrofit;
    private ApiService apiService;

    public static HttpService getInstance(){
        if (null == INSTANCE){
            synchronized (HttpService.class){
                if (null == INSTANCE){
                    INSTANCE = new HttpService();
                }
            }
        }
        return INSTANCE;
    }

    private HttpService(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(Server.HOST_BASE)
                .client(httpClientBuilder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static Retrofit getRetrofit() {
        return getInstance().retrofit;
    }

    public static ApiService getApiService() {
        return getInstance().apiService;
    }


    /*-----------------------------以下是Http请求方法-----------------------------------*/


    public static void getStoryList(int page, BaseCallBack<List<Story>> callBack){
        getApiService().getStoryList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack);
    }

    public static void getStoryDetail(int id, BaseCallBack<StoryDetail> callBack){
        getApiService().getStoryDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack);
    }

    public static void getUpdateInfo(BaseCallBack<UpdateInfo> callBack){
        getApiService().getUpdateInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack);
    }

    public static void downloadApk(final Context context, final String apkUrl){
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                Call<ResponseBody> call = getApiService().downloadApk(apkUrl);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ResponseBody responseBody = response.body();
                        if (responseBody != null){
                            String downloadFileUrl = FilePathManager.getDownloadPath() + "/app.apk";
                            boolean success = FileIOUtils.writeFileFromIS(downloadFileUrl,responseBody.byteStream());
                            if (success){
                                emitter.onNext(downloadFileUrl);
                            }else{
                                emitter.onError(new Throwable("下载安装包失败"));
                            }
                        }else{
                            emitter.onError(new Throwable(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        emitter.onError(t);
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        AppUtils.installApk(context,new File(s));
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.show(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




}
