package com.byvoid.wanangushi.mvp;

import com.byvoid.wanangushi.model.Story;

import java.util.List;

/**
 * @author melody
 * @date 2018/4/4
 */
public interface IStoryHomeModel {

    void getStoryList(int lastId,IStoryHomeFragment.IGetDataCallBack<List<Story>> callBack);
}
