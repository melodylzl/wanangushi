package com.byvoid.wanangushi.mvp;

import com.byvoid.wanangushi.http.HttpRequestUtil;
import com.byvoid.wanangushi.model.Story;
import com.lzy.callback.JsonCallback;
import com.lzy.model.LzyResponse;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * @author melody
 * @date 2018/4/4
 */
public class StoryHomeModel implements IStoryHomeModel{


    @Override
    public void getStoryList(int page,final IStoryHomeFragment.IGetDataCallBack<List<Story>> callBack) {
        HttpRequestUtil.getStoryList(page,new JsonCallback<LzyResponse<List<Story>>>() {
            @Override
            public void onSuccess(LzyResponse<List<Story>> response) {
                callBack.onSuccess(response.data,response.msg);
            }

            @Override
            public void onFail(LzyResponse<List<Story>> response) {
                callBack.onFail(response.msg,response.code);
            }
        });
    }
}
