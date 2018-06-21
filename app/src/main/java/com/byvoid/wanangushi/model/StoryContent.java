package com.byvoid.wanangushi.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author melody
 * @date 2018/3/23
 */
public class StoryContent implements Serializable{
    private static final long serialVersionUID = 7564884743499849261L;

    public static final int TYPE_TEXT = 0;
    public static final int TYPE_IMAGE = 1;

    @SerializedName("id")
    private int mId;
    @SerializedName("role")
    private Role mRole;
    @SerializedName("content")
    private String mContent;
    @SerializedName("imageUrl")
    private String mImageUrl;
    /**
     * 内容的类型，是文字还是图片
     */
    @SerializedName("contentType")
    private int mContentType;

    /**
     * 类型，区分旁白、第一人称、第三人称
     */
    @SerializedName("type")
    private int mType;

    public StoryContent(){

    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Role getRole() {
        if (null == mRole){
            mRole = new Role();
        }
        return mRole;
    }

    public void setRole(Role role) {
        this.mRole = role;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public int getContentType() {
        return mContentType;
    }

    public void setContentType(int contentType) {
        this.mContentType = contentType;
    }

    public int getType(){
        return mType;
    }

    public void setType(int type){
        this.mType = type;
    }
}
