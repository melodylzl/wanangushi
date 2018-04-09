package com.byvoid.wanangushi.http;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author melody
 * @date 2018/4/9
 */
public abstract class BaseCallBack<T> implements Observer<BaseResult<T>> {

    /**
     * 成功回调
     * @param data 转换的数据
     * @param msg 反馈信息
     */
    public abstract void onSuccess(T data,String msg);

    /**
     * 失败的回调
     * @param msg 反馈信息
     * @param code 提示码
     */
    public abstract void onFail(String msg,int code);

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseResult<T> result) {
        if (result.isError()){
            onFail(result.getMsg(),result.getCode());
        }else{
            onSuccess(result.getData(),result.getMsg());
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
