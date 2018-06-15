package com.byvoid.wanangushi.mvp;

/**
 * @author melody
 * @date 2018/6/14
 */
public interface IBaseCallBack {

    interface IGetDataCallBack<T>{

        /**
         * 成功回调
         * @param t t
         * @param msg msg
         */
        void onSuccess(T t,String msg);

        /**
         * 失败回调
         * @param msg msg
         * @param code code
         */
        void onFail(String msg,int code);
    }
}
