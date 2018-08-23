package com.byvoid.wanangushi.utils;

import android.net.Uri;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.model.CropOptions;

import java.io.File;

/**
 * @author melody
 * @date 2018/8/22
 */
public class TakePhotoUtils {

    public static void take(TakePhoto takePhoto){
        take(takePhoto,false);
    }

    public static void take(TakePhoto takePhoto,boolean isCrop){
        File file = new File(FilePathManager.getImagePath(), System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            return;
        }
        Uri imageUri = Uri.fromFile(file);
        if (isCrop){
            takePhoto.onPickFromGalleryWithCrop(imageUri,getCropOptions());
        }else{
            takePhoto.onPickFromGallery();
        }
    }


    private static CropOptions getCropOptions() {
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setOutputX(800).setOutputY(800);
        builder.setWithOwnCrop(true);
        return builder.create();
    }

}
