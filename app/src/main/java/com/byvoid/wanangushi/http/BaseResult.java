package com.byvoid.wanangushi.http;

import java.io.Serializable;

/**
 * @author melody
 * @date 2018/4/9
 */
public class BaseResult<T> implements Serializable{

    private static final long serialVersionUID = -5882674145689374034L;

    private boolean error;

    private int code;

    private String msg;

    private T data;


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
