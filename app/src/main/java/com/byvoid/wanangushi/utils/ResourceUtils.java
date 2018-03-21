package com.byvoid.wanangushi.utils;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import com.byvoid.wanangushi.app.UtilsApplication;

/**
 * @author melody
 * @date 2018/2/8
 */
public class ResourceUtils {

    public static String getString(@StringRes int resId){
        return UtilsApplication.getInstance().getString(resId);
    }

    public static int getColor(@ColorRes int resId){
        return ContextCompat.getColor(UtilsApplication.getInstance(),resId);
    }

    public static Drawable getDrawable(@DrawableRes int resId){
        return ContextCompat.getDrawable(UtilsApplication.getInstance(),resId);
    }



}
