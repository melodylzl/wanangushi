package com.byvoid.wanangushi.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

import com.byvoid.wanangushi.app.UtilsApplication;

/**
 * 屏幕适配方案
 * 参考：https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA
 * @author melody
 * @date 2018/8/2
 */
public class ScreenAdaptUtils {


    public static void cancelCustomDensity(@NonNull Activity activity){
        final DisplayMetrics appDisplayMetrics = UtilsApplication.getInstance().getResources().getDisplayMetrics();
        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = appDisplayMetrics.density;
        activityDisplayMetrics.scaledDensity = appDisplayMetrics.scaledDensity;
        activityDisplayMetrics.densityDpi = appDisplayMetrics.densityDpi;
    }

    public static void setCustomDensityVertical(@NonNull Activity activity, int size){
        setCustomDensity(activity,size,true);
    }

    public static void setCustomDensityHorizontal(@NonNull Activity activity, int size){
        setCustomDensity(activity,size,false);
    }

    public static void setCustomDensity(@NonNull Activity activity, int size, boolean isVertical){
        final DisplayMetrics appDisplayMetrics = UtilsApplication.getInstance().getResources().getDisplayMetrics();
        float targetDensity;
        if (isVertical){
            targetDensity = appDisplayMetrics.widthPixels / size;
        }else{
            targetDensity = appDisplayMetrics.heightPixels / size;
        }
        final int targetDensityDpi = (int) (160 * targetDensity);
        final float targetScaledDensity = targetDensity * (appDisplayMetrics.scaledDensity / appDisplayMetrics.density);

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

}
