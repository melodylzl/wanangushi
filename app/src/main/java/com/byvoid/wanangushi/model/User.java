package com.byvoid.wanangushi.model;

import java.io.Serializable;

/**
 * @author melody
 * @date 2018/3/23
 */
public class User implements Serializable{
    private static final long serialVersionUID = -9066930415986633041L;

    private int mUid;

    private String mName;

    private String mAvatar;

    public void setUid(int uid) {
        this.mUid = uid;
    }

    public int getUid() {
        return mUid;
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
}
