package com.byvoid.wanangushi.module.story.mvp;

import com.byvoid.wanangushi.module.story.model.StoryDetail;
import com.byvoid.wanangushi.base.IBaseCallBack;

/**
 * @author melody
 * @date 2018/6/21
 */
public interface IStoryDetailModel {

    void getStoryDetail(int id, IBaseCallBack.IGetDataCallBack<StoryDetail> callBack);

    StoryDetail getStoryDetailData();
}
