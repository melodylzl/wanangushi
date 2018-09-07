package com.byvoid.wanangushi.module.story.model;


import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author melody
 * @date 2018/3/23
 */
public class StoryTalk implements Serializable,MultiItemEntity{
    private static final long serialVersionUID = 7564884743499849261L;

    public static final int TYPE_TEXT = 0;
    public static final int TYPE_IMAGE = 1;

    public static final int SENDER_ASIDE = 0;
    public static final int SENDER_FIRST_PERSON = 1;
    public static final int SENDER_THIRD_PERSON = 2;

    @SerializedName("id")
    private int id;
    @SerializedName("roleId")
    private int roleId;
    @SerializedName("content")
    private String content = "";
    @SerializedName("image")
    private String imageUrl = "";
    @SerializedName("type")
    private int type;
    @SerializedName("sender")
    private int sender;

    public StoryTalk(){

    }

    public int getId() {
        return id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    private Role role;

    public Role getRole() {
        if (null == role){
            role = new Role();
        }
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int getItemType() {
        return sender;
    }
}
