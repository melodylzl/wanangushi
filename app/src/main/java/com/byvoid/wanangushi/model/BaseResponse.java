package com.byvoid.wanangushi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author melody
 * @date 2018/8/22
 */
public class BaseResponse implements Serializable{

    private static final long serialVersionUID = -6355234441472277211L;

    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }
}
