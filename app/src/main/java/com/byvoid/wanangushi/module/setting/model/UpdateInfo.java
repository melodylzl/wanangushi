package com.byvoid.wanangushi.module.setting.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author melody
 * @date 2018/6/14
 */
public class UpdateInfo implements Serializable{

    private static final long serialVersionUID = 7488097686679809492L;

    @SerializedName("versionCode")
    private int versionCode;
    @SerializedName("versionName")
    private String versionName;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("mustUpdate")
    private int mustUpdate;
    @SerializedName("url")
    private String url;

    public UpdateInfo(){

    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isMustUpdate(){
        return mustUpdate == 1;
    }

    public int getMustUpdate() {
        return mustUpdate;
    }

    public void setMustUpdate(int mustUpdate) {
        this.mustUpdate = mustUpdate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
