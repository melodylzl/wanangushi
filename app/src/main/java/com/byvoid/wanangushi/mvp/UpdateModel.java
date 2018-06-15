package com.byvoid.wanangushi.mvp;

import com.byvoid.wanangushi.http.BaseCallBack;
import com.byvoid.wanangushi.http.HttpService;
import com.byvoid.wanangushi.model.UpdateInfo;

/**
 * @author melody
 * @date 2018/6/14
 */
public class UpdateModel implements IUpdateModel{

    @Override
    public void getUpdateInfo(final IBaseCallBack.IGetDataCallBack<UpdateInfo> callBack) {
        HttpService.getUpdateInfo(new BaseCallBack<UpdateInfo>() {
            @Override
            public void onSuccess(UpdateInfo data, String msg) {
                callBack.onSuccess(data,msg);
            }

            @Override
            public void onFail(String msg, int code) {
                callBack.onFail(msg,code);
            }
        });
    }
}
