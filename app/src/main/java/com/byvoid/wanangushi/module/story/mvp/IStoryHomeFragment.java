package com.byvoid.wanangushi.module.story.mvp;

import com.byvoid.wanangushi.module.story.model.Story;
import com.byvoid.wanangushi.base.IBaseCallBack;

import java.util.List;

/**
 * @author melody
 * @date 2018/4/4
 */
public interface IStoryHomeFragment extends IBaseCallBack {

    interface IView{

        /**
         * 更新故事首页列表
         * @param storyList storyList
         */
        void updateView(List<Story> storyList,int loadType);
    }
}
