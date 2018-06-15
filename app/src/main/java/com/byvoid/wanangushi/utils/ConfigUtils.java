package com.byvoid.wanangushi.utils;

import android.content.pm.PackageManager;

import com.byvoid.wanangushi.app.UtilsApplication;

/**
 * @author melody
 * @date 2018/6/14
 */
public class ConfigUtils {

    /**
     * 获取app的版本号
     * @return 版本号
     */
    public static int getVersionCode(){
        int versionCode = 0;
        try {
            versionCode = UtilsApplication.getInstance().getPackageManager().getPackageInfo(
                    UtilsApplication.getInstance().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取app的版本号名称
     * @return 版本号名称
     */
    public static String getVersionName() {
        String verName = "";
        try {
            verName = UtilsApplication.getInstance().getPackageManager().getPackageInfo(
                    UtilsApplication.getInstance().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

}
