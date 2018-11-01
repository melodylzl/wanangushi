package com.byvoid.wanangushi.utils;

import com.byvoid.lib.utils.FilePathUtils;

/**
 * 文件路径管理类
 * @author melody
 * @date 2018/3/28
 */
public class FilePathManager {


    public static final String DOWNLOAD_PATH = "/download";
    public static final String IMAGE_PATH = "/image";
    public static final String OKHTTP_CACHE_PATH = "/okHttp";
    public static final String PATCH_PATH = "/patch";

    /**
     * 获取app下载文件目录
     * @return /Android/data/<package_name>/files/download
     */
    public static String getDownloadPath(){
        return FilePathUtils.getExternalFilesPath() + DOWNLOAD_PATH;
    }

    /**
     * 获取补丁包文件目录
     * @return /Android/data/<package_name>/files/download/patch
     */
    public static String getPatchPath(){
        return getDownloadPath() + PATCH_PATH;
    }

    /**
     * 获取app图片目录
     * @return /Android/data/<package_name>/files/image
     */
    public static String getImagePath(){
        return FilePathUtils.getExternalFilesPath() + IMAGE_PATH;
    }

    /**
     * 获取OkHttp内存缓存目录的绝对路径
     * @return /data/data/<package_name>/cache/okHttp
     */
    public static String getOkhttpCachePath(){
        return FilePathUtils.getMemoryCachePath() + OKHTTP_CACHE_PATH;
    }


}
