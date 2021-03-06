package com.byvoid.wanangushi.module.story.mvp;

import com.byvoid.wanangushi.http.HttpService;
import com.byvoid.wanangushi.http.BaseCallBack;
import com.byvoid.wanangushi.module.story.model.Story;

import java.util.List;

/**
 * @author melody
 * @date 2018/4/4
 */
public class StoryHomeModel implements IStoryHomeModel{


    @Override
    public void getStoryList(int lastId,final IStoryHomeFragment.IGetDataCallBack<List<Story>> callBack) {
        HttpService.getStoryList(lastId, new BaseCallBack<List<Story>>() {
            @Override
            public void onSuccess(List<Story> data, String msg) {
                callBack.onSuccess(data,msg);
            }

            @Override
            public void onFail(String msg, int code) {
                callBack.onFail(msg,code);
            }
        });
    }
}
