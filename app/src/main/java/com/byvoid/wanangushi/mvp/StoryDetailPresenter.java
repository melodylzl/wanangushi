package com.byvoid.wanangushi.mvp;

import com.byvoid.wanangushi.model.StoryDetail;
import com.byvoid.wanangushi.utils.ToastUtils;

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
