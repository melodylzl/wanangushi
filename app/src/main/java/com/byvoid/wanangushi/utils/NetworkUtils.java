package com.byvoid.wanangushi.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.byvoid.wanangushi.app.UtilsApplication;

/**
 * @author melody
 * @date 2018/9/21
 */
public class NetworkUtils {


    /**
     * 是否有网络连接
     * @return true 是 false 否
     */
    public static boolean isNetWorkConnected(){
        ConnectivityManager mConnectivityManager  = (ConnectivityManager) UtilsApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnectivityManager == null){
            return false;
        }
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        return mNetworkInfo != null && mNetworkInfo.isAvailable();
    }

    /**
     * 是否Wifi连接
     * @return true 是 false 否
     */
    public static boolean isWifiConnected(){
        ConnectivityManager mConnectivityManager = (ConnectivityManager) UtilsApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnectivityManager == null){
            return false;
        }
        NetworkInfo mNetWorkInfo = mConnectivityManager.getActiveNetworkInfo();
        return mNetWorkInfo != null && ConnectivityManager.TYPE_WIFI == mNetWorkInfo.getType();
    }

    /**
     * 是否移动连接
     * @return true 是 false 否
     */
    public static boolean isMobileConnected(){
        ConnectivityManager mConnectivityManager = (ConnectivityManager) UtilsApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnectivityManager == null){
            return false;
        }
        NetworkInfo mNetWorkInfo = mConnectivityManager.getActiveNetworkInfo();
        return mNetWorkInfo != null && ConnectivityManager.TYPE_MOBILE == mNetWorkInfo.getType();
    }


}
