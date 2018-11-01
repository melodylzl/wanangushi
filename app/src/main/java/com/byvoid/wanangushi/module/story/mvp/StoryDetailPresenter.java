package com.byvoid.wanangushi.module.story.mvp;

import com.byvoid.lib.utils.ToastUtils;
import com.byvoid.wanangushi.base.IBaseCallBack;
import com.byvoid.wanangushi.module.story.model.StoryDetail;

/**
 * @author melody
 * @date 2018/6/21
 */
public class StoryDetailPresenter {

    private IStoryDetailModel mModel;
    private IStoryActivity mView;

    public StoryDetailPresenter(IStoryActivity view){
        mView = view;
        mModel = new StoryDetailModel();
    }

    public void getStoryDetail(int id){
        mModel.getStoryDetail(id, new IBaseCallBack.IGetDataCallBack<StoryDetail>() {
            @Override
            public void onSuccess(StoryDetail storyDetail, String msg) {
                mView.setData(storyDetail);
            }

            @Override
            public void onFail(String msg, int code) {
                ToastUtils.show(msg);
            }
        });
    }

    public StoryDetail getStoryDetailData(){
        return mModel.getStoryDetailData();
    }
}
