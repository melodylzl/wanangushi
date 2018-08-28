package com.byvoid.wanangushi.module.story.mvp;

import com.byvoid.wanangushi.module.story.model.Story;

import java.util.List;

/**
 * @author melody
 * @date 2018/4/4
 */
public interface IStoryHomeModel {

    void getStoryList(int lastId,IStoryHomeFragment.IGetDataCallBack<List<Story>> callBack);
}
