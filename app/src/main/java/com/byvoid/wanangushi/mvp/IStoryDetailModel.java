package com.byvoid.wanangushi.mvp;

import com.byvoid.wanangushi.model.StoryDetail;

/**
 * @author melody
 * @date 2018/6/21
 */
public interface IStoryDetailModel {

    void getStoryDetail(int id, IBaseCallBack.IGetDataCallBack<StoryDetail> callBack);

    StoryDetail getStoryDetailData();
}
