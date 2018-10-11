package com.byvoid.wanangushi.utils;

import android.os.Environment;

import com.byvoid.wanangushi.app.UtilsApplicationLike;

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
        return UtilsApplicationLike.getAppContext().getCacheDir().getPath();
    }

    /**
     * 获取内存文件目录的绝对路径
     * @return /data/data/<package_name>/files
     */
    public static String getMemoryFilesPath(){
        return UtilsApplicationLike.getAppContext().getFilesDir().getPath();
    }

    /**
     * 获取外置SD卡缓存目录的绝对路径
     * 如果外置SD卡不存在或者不可用，则返回内存缓存目录
     * @return /Android/data/<package_name>/cache
     */
    public static String getExternalCachePath(){
        if (isExistSDCard()){
            File externalCacheFile = UtilsApplicationLike.getAppContext().getExternalCacheDir();
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
            File externalFiles = UtilsApplicationLike.getAppContext().getExternalFilesDir(null);
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
    public static final String IMAGE_PATH = "/image";
    public static final String OKHTTP_CACHE_PATH = "/okHttp";

    /**
     * 获取app下载文件目录
     * @return /Android/data/<package_name>/files/download
     */
    public static String getDownloadPath(){
        return getExternalFilesPath() + DOWNLOAD_PATH;
    }

    /**
     * 获取app图片目录
     * @return /Android/data/<package_name>/files/image
     */
    public static String getImagePath(){
        return getExternalFilesPath() + IMAGE_PATH;
    }

    /**
     * 获取OkHttp内存缓存目录的绝对路径
     * @return /data/data/<package_name>/cache/okHttp
     */
    public static String getOkhttpCachePath(){
        return getMemoryCachePath() + OKHTTP_CACHE_PATH;
    }


}
