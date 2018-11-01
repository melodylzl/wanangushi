package com.byvoid.wanangushi.http;

import com.byvoid.lib.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author melody
 * @date 2018/9/21
 */
public class CacheInterceptor implements Interceptor{


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        boolean isNetworkAvailable = NetworkUtils.isNetWorkConnected();
        if (isNetworkAvailable){
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
        }else {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        if (isNetworkAvailable){
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control","public,max-age=" + 60 * 60)
                    .build();
        }else{
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control","public,only-if-cached,max-stale=" + 7 * 24 * 60 * 60)
                    .build();
        }
        return response;
    }
}
