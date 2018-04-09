package com.byvoid.wanangushi.http;

import com.byvoid.wanangushi.BuildConfig;
import com.byvoid.wanangushi.model.Story;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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

}
