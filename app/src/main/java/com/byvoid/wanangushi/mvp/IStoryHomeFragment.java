package com.byvoid.wanangushi.mvp;

import com.byvoid.wanangushi.model.Story;

import java.util.List;

/**
 * @author melody
 * @date 2018/4/4
 */
public interface IStoryHomeFragment {

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

    interface IView{

        /**
         * 更新故事首页列表
         * @param storyList storyList
         */
        void updateView(List<Story> storyList,int loadType);
    }
}
