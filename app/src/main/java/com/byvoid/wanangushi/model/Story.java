package com.byvoid.wanangushi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author melody
 * @date 2018/3/21
 */
public class Story implements Serializable{
    private static final long serialVersionUID = 2540300759677642120L;

    @SerializedName("name")
    private String mName;
    @SerializedName("cover")
    private String mCoverUrl;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getCoverUrl() {
        return mCoverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.mCoverUrl = coverUrl;
    }
}