package com.byvoid.wanangushi.utils;

import com.byvoid.wanangushi.app.UtilsApplicationLike;

/**
 * @author melody
 * @date 2018/2/6
 */
public class SizeUtils {

    /**
     * px 转 dp
     * @param pxValue px
     * @return dp
     */
    public static int px2dp(final float pxValue){
        float scale = UtilsApplicationLike.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * dp 转 px
     * @param dpValue dp
     * @return px
     */
    public static int dp2px(final float dpValue){
        final float scale = UtilsApplicationLike.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * sp 转 px
     * @param spValue sp
     * @return px
     */
    public static int sp2px(final float spValue) {
        final float fontScale = UtilsApplicationLike.getAppContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px 转 sp
     * @param pxValue px
     * @return sp
     */
    public static int px2sp(final float pxValue) {
        final float fontScale = UtilsApplicationLike.getAppContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    /**
     * 获取屏幕的宽
     * @return screenWidth
     */
    public static int getScreenWidth(){
        return UtilsApplicationLike.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕的高
     * @return screenHeight
     */
    public static int getScreenHeight(){
        return UtilsApplicationLike.getAppContext().getResources().getDisplayMetrics().heightPixels;
    }

}
