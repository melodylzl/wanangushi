package com.byvoid.wanangushi.utils;

import android.app.Activity;
import android.content.res.Resources;
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
        final DisplayMetrics systemDisplayMetrics = Resources.getSystem().getDisplayMetrics();
        final DisplayMetrics appDisplayMetrics = UtilsApplication.getInstance().getResources().getDisplayMetrics();
        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = systemDisplayMetrics.density;
        activityDisplayMetrics.scaledDensity = systemDisplayMetrics.scaledDensity;
        activityDisplayMetrics.densityDpi = systemDisplayMetrics.densityDpi;
        appDisplayMetrics.density = systemDisplayMetrics.density;
        appDisplayMetrics.scaledDensity = systemDisplayMetrics.scaledDensity;
        appDisplayMetrics.densityDpi = systemDisplayMetrics.densityDpi;
    }

    public static void setCustomDensityVertical(@NonNull Activity activity, int size){
        setCustomDensity(activity,size,true);
    }

    public static void setCustomDensityHorizontal(@NonNull Activity activity, int size){
        setCustomDensity(activity,size,false);
    }

    public static void setCustomDensity(@NonNull Activity activity, int size, boolean isVertical){
        final DisplayMetrics systemDisplayMetrics = Resources.getSystem().getDisplayMetrics();
        final DisplayMetrics appDisplayMetrics = UtilsApplication.getInstance().getResources().getDisplayMetrics();
        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        if (isVertical){
            activityDisplayMetrics.density = appDisplayMetrics.widthPixels / size;
        }else{
            activityDisplayMetrics.density = appDisplayMetrics.heightPixels / size;
        }
        activityDisplayMetrics.densityDpi = (int) (160 * activityDisplayMetrics.density);
        activityDisplayMetrics.scaledDensity = activityDisplayMetrics.density * (systemDisplayMetrics.scaledDensity / systemDisplayMetrics.density);

        appDisplayMetrics.density = activityDisplayMetrics.density;
        appDisplayMetrics.scaledDensity = activityDisplayMetrics.scaledDensity;
        appDisplayMetrics.densityDpi = activityDisplayMetrics.densityDpi;
    }

}
