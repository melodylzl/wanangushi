package com.byvoid.wanangushi.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.byvoid.wanangushi.app.UtilsApplicationLike;

/**
 * @author melody
 * @date 2018/4/9
 */
public class ToastUtils {

    public static void show(String msg){
        show(msg,Toast.LENGTH_SHORT);
    }

    public static void show(@StringRes int resId){
        show(resId,Toast.LENGTH_SHORT);
    }

    public static void show(String msg,int time){
        Toast.makeText(UtilsApplicationLike.getAppContext(),msg,time).show();
    }

    public static void show(@StringRes int resId,int time){
        Toast.makeText(UtilsApplicationLike.getAppContext(),resId,time).show();
    }

}
