package com.byvoid.wanangushi.mvp;

import com.byvoid.wanangushi.model.UpdateInfo;

/**
 * @author melody
 * @date 2018/6/14
 */
public interface IUpdateModel {

    void getUpdateInfo(IBaseCallBack.IGetDataCallBack<UpdateInfo> callBack);
}