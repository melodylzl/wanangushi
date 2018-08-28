package com.byvoid.wanangushi.module.qiniu.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author melody
 * @date 2018/8/28
 */
public class UploadTokenResult implements Serializable{

    private static final long serialVersionUID = 9010039380292523487L;

    @SerializedName("uploadToken")
    private String uploadToken;

    public String getUploadToken() {
        return uploadToken;
    }
}
