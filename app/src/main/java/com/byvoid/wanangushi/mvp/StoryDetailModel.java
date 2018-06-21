package com.byvoid.wanangushi.mvp;

import com.byvoid.wanangushi.http.BaseCallBack;
import com.byvoid.wanangushi.http.HttpService;
import com.byvoid.wanangushi.model.StoryDetail;

/**
 * @author melody
 * @date 2018/6/21
 */
public class StoryDetailModel implements IStoryDetailModel{

    @Override
    public void getStoryDetail(int id, final IBaseCallBack.IGetDataCallBack<StoryDetail> callBack) {
        HttpService.getStoryDetail(id, new BaseCallBack<StoryDetail>() {
            @Override
            public void onSuccess(StoryDetail data, String msg) {
                callBack.onSuccess(data,msg);
            }

            @Override
            public void onFail(String msg, int code) {
                callBack.onFail(msg,code);
            }
        });
    }
}
