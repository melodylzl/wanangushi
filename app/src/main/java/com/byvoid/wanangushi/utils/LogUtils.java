package com.byvoid.wanangushi.utils;

import com.byvoid.wanangushi.BuildConfig;

/**
 * @author melody
 * @date 2018/2/10
 */
public class LogUtils {

    public static void d(String tag,String message, Object... args) {
        if (BuildConfig.DEBUG){
            com.orhanobut.logger.Logger.t(tag);
            com.orhanobut.logger.Logger.d(message, args);
        }
    }

    public static void d(String tag,Object... args) {
        if (BuildConfig.DEBUG){
            com.orhanobut.logger.Logger.t(tag);
            com.orhanobut.logger.Logger.d(args);
        }
    }

    public static void e(String tag,String message, Object... args) {
        if (BuildConfig.DEBUG){
            com.orhanobut.logger.Logger.t(tag);
            com.orhanobut.logger.Logger.e(null, message, args);
        }
    }

    public static void e(String tag,Throwable throwable, String message, Object... args) {
        if (BuildConfig.DEBUG){
            com.orhanobut.logger.Logger.t(tag);
            com.orhanobut.logger.Logger.e(throwable, message, args);
        }
    }

    public static void i(String tag,String message, Object... args) {
        if (BuildConfig.DEBUG){
            com.orhanobut.logger.Logger.t(tag);
            com.orhanobut.logger.Logger.i(message, args);
        }
    }

    public static void v(String tag,String message, Object... args) {
        if (BuildConfig.DEBUG){
            com.orhanobut.logger.Logger.t(tag);
            com.orhanobut.logger.Logger.v(message, args);
        }
    }

    public static void w(String tag,String message, Object... args) {
        if (BuildConfig.DEBUG){
            com.orhanobut.logger.Logger.t(tag);
            com.orhanobut.logger.Logger.w(message, args);
        }
    }


}
