package com.byvoid.wanangushi.http;

import com.byvoid.lib.constants.MemoryConstants;
import com.byvoid.lib.utils.ConfigUtils;
import com.byvoid.wanangushi.BuildConfig;
import com.byvoid.wanangushi.base.BaseResponse;
import com.byvoid.wanangushi.module.qiniu.model.UploadTokenResult;
import com.byvoid.wanangushi.module.setting.model.UpdateInfo;
import com.byvoid.wanangushi.module.story.model.Role;
import com.byvoid.wanangushi.module.story.model.Story;
import com.byvoid.wanangushi.module.story.model.StoryDetail;
import com.byvoid.wanangushi.tinker.model.PatchInfo;
import com.byvoid.wanangushi.utils.FilePathManager;

import java.io.File;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
        File cacheFile = new File(FilePathManager.getOkhttpCachePath());
        httpClientBuilder.cache(new Cache(cacheFile,10 * MemoryConstants.MB));
        httpClientBuilder.addInterceptor(new CacheInterceptor());

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


    public static void getStoryList(int lastId, BaseCallBack<List<Story>> callBack){
        getApiService().getStoryList(lastId)
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


    public static void getPatchInfo(BaseCallBack<PatchInfo> callBack){
        getApiService().getPatchInfo(ConfigUtils.getVersionName())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack);
    }

    public static void getRoleList(int lastId, BaseCallBack<List<Role>> callBack){
        getApiService().getRoleList(lastId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack);
    }

    public static void createRole(String name,String avatar,BaseCallBack<BaseResponse> callBack){
        getApiService().createRole(name,avatar)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack);

    }

    public static void createStory(String name,String cover,String talkListJson,BaseCallBack<BaseResponse> callBack){
        getApiService().createStory(name,cover,talkListJson)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack);
    }

    public static void getQiniuUploadToken(BaseCallBack<UploadTokenResult> callBack){
        getApiService().getQiniuUploadToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack);
    }




}
