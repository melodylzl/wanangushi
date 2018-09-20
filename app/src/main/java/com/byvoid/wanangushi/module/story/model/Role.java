package com.byvoid.wanangushi.module.story.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author melody
 * @date 2018/3/23
 */
public class Role implements Serializable{
    private static final long serialVersionUID = -9066930415986633041L;

    /**
     * 旁白的id
     */
    public static final int ASIDE_ID = 1;

    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("avatar")
    private String mAvatar;

    public Role(){

    }

    public void setId(int id) {
        this.mId = id;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        this.mAvatar = avatar;
    }

    public static Role getAsideRole(){
        Role role = new Role();
        role.setId(ASIDE_ID);
        role.setName("旁白");
        role.setAvatar("");
        return role;
    }

    private boolean mIsSelected = false;

    public void setSelected(boolean selected) {
        mIsSelected = selected;
    }

    public boolean isSelected() {
        return mIsSelected;
    }
}
