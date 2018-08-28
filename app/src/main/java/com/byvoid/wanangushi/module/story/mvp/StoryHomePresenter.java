package com.byvoid.wanangushi.module.story.mvp;

import com.byvoid.wanangushi.base.LoadType;
import com.byvoid.wanangushi.module.story.model.Story;

import java.util.List;

/**
 * @author melody
 * @date 2018/4/4
 */
public class StoryHomePresenter {

    private IStoryHomeFragment.IView mView;
    private IStoryHomeModel iStoryHomeModel;

    public StoryHomePresenter(IStoryHomeFragment.IView view){
        mView = view;
        iStoryHomeModel = new StoryHomeModel();
    }

    public void getStoryList(final int lastId){
        iStoryHomeModel.getStoryList(lastId,new IStoryHomeFragment.IGetDataCallBack<List<Story>>() {
            @Override
            public void onSuccess(List<Story> stories, String msg) {
                mView.updateView(stories,lastId > 0 ? LoadType.LOAD_MORE : LoadType.REFRESH);
            }

            @Override
            public void onFail(String msg, int code) {

            }
        });
    }

}
