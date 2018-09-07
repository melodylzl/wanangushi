package com.byvoid.wanangushi.app;

import com.byvoid.wanangushi.base.BaseApplication;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.NoEncryption;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.umeng.commonsdk.UMConfigure;

/**
 * @author melody
 * @date 2018/1/24
 */
public class UtilsApplication extends BaseApplication{

    private static UtilsApplication sInstance;

    public static UtilsApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initLogger();
        initUMeng();
        initHawk();
    }

    protected void initLogger(){
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    protected void initUMeng(){
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
    }

    protected void initHawk(){
        Hawk.init(this)
            .setEncryption(new NoEncryption())
            .build();
    }
}
