package com.byvoid.wanangushi.module.setting.mvp;

import com.byvoid.wanangushi.module.setting.model.UpdateInfo;
import com.byvoid.wanangushi.base.IBaseCallBack;

/**
 * @author melody
 * @date 2018/6/14
 */
public interface IUpdateModel {

    void getUpdateInfo(IBaseCallBack.IGetDataCallBack<UpdateInfo> callBack);
}
