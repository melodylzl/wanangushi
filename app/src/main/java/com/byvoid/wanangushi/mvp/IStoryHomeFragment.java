package com.byvoid.wanangushi.mvp;

import com.byvoid.wanangushi.model.Story;

import java.util.List;

/**
 * @author melody
 * @date 2018/4/4
 */
public interface IStoryHomeFragment extends IBaseCallBack{

    interface IView{

        /**
         * 更新故事首页列表
         * @param storyList storyList
         */
        void updateView(List<Story> storyList,int loadType);
    }
}
