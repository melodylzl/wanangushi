package com.byvoid.wanangushi.utils;

import android.os.Environment;

import com.byvoid.wanangushi.app.UtilsApplication;

import java.io.File;

/**
 * 文件路径管理类
 * @author melody
 * @date 2018/3/28
 */
public class FilePathManager {


    /**
     * 获取内存缓存目录的绝对路径
     * @return /data/data/<package_name>/cache
     */
    public static String getMemoryCachePath(){
        return UtilsApplication.getInstance().getCacheDir().getPath();
    }

    /**
     * 获取内存文件目录的绝对路径
     * @return /data/data/<package_name>/files
     */
    public static String getMemoryFilesPath(){
        return UtilsApplication.getInstance().getFilesDir().getPath();
    }

    /**
     * 获取外置SD卡缓存目录的绝对路径
     * 如果外置SD卡不存在或者不可用，则返回内存缓存目录
     * @return /Android/data/<package_name>/cache
     */
    public static String getExternalCachePath(){
        if (isExistSDCard()){
            File externalCacheFile = UtilsApplication.getInstance().getExternalCacheDir();
            if (externalCacheFile != null){
                return externalCacheFile.getPath();
            }
        }
        return getMemoryCachePath();
    }

    /**
     * 获取外置SD卡文件目录的绝对路径
     * 如果外置SD卡不存在或者不可用，则返回内存文件目录
     * @return /Android/data/<package_name>/files
     */
    public static String getExternalFilesPath(){
        if (isExistSDCard()){
            File externalFiles = UtilsApplication.getInstance().getExternalFilesDir(null);
            if (externalFiles != null){
                return externalFiles.getPath();
            }
        }
        return getMemoryFilesPath();
    }

    /**
     * 判断SD卡是否存在
     * @return true or false
     */
    private static boolean isExistSDCard(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


    public static final String DOWNLOAD_PATH = "/download";

    /**
     * 获取app下载文件目录
     * @return /Android/data/<package_name>/files/download
     */
    public static String getDownloadPath(){
        return getExternalFilesPath() + DOWNLOAD_PATH;
    }


}
