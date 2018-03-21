package com.byvoid.wanangushi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author melody
 * @date 2018/1/25
 */
public class TestModel implements Serializable{

    @SerializedName("des")
    private String mDes;

    public String getDes() {
        return mDes;
    }

    public void setDes(String des) {
        this.mDes = des;
    }
}
