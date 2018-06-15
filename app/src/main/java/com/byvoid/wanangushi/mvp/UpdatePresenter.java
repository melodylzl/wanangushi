package com.byvoid.wanangushi.mvp;

import com.byvoid.wanangushi.http.BaseCallBack;
import com.byvoid.wanangushi.http.HttpRequestUtil;
import com.byvoid.wanangushi.http.HttpService;
import com.byvoid.wanangushi.model.UpdateInfo;
import com.byvoid.wanangushi.utils.ToastUtils;

/**
 * @author melody
 * @date 2018/6/14
 */
public class UpdatePresenter {

    private IUpdateModel mUpdateModel;
    private IUpdateView mUpdateView;

    public UpdatePresenter(IUpdateView updateView){
        mUpdateModel = new UpdateModel();
        mUpdateView = updateView;
    }

    public void getUpdateInfo(){
        mUpdateModel.getUpdateInfo(new IBaseCallBack.IGetDataCallBack<UpdateInfo>() {
            @Override
            public void onSuccess(UpdateInfo updateInfo, String msg) {
                mUpdateView.showUpdateDialog(updateInfo);
            }

            @Override
            public void onFail(String msg, int code) {
                ToastUtils.show(msg);
            }
        });
    }
}
