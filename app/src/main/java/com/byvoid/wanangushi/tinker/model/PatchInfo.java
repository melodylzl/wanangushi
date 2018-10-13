package com.byvoid.wanangushi.tinker.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author liuziliang
 * @date 2018/10/13
 */
public class PatchInfo implements Serializable{

    private static final long serialVersionUID = -417138137285488659L;

    @SerializedName("url")
    private String mUrl;

    public String getUrl() {
        return mUrl;
    }
}
