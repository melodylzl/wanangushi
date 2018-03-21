package com.byvoid.wanangushi.utils;

import com.byvoid.wanangushi.BuildConfig;

/**
 * @author melody
 * @date 2018/2/10
 */
public class LogUtils {

    public static void d(String message, Object... args) {
        if (BuildConfig.DEBUG){
            com.orhanobut.logger.Logger.d(message, args);
        }
    }

    public static void d(Object object) {
        if (BuildConfig.DEBUG){
            com.orhanobut.logger.Logger.d(object);
        }
    }

    public static void e(String message, Object... args) {
        if (BuildConfig.DEBUG){
            com.orhanobut.logger.Logger.e(null, message, args);
        }
    }

    public static void e(Throwable throwable, String message, Object... args) {
        if (BuildConfig.DEBUG){
            com.orhanobut.logger.Logger.e(throwable, message, args);
        }
    }

    public static void i(String message, Object... args) {
        if (BuildConfig.DEBUG){
            com.orhanobut.logger.Logger.i(message, args);
        }
    }

    public static void v(String message, Object... args) {
        if (BuildConfig.DEBUG){
            com.orhanobut.logger.Logger.v(message, args);
        }
    }

    public static void w(String message, Object... args) {
        if (BuildConfig.DEBUG){
            com.orhanobut.logger.Logger.w(message, args);
        }
    }


}
