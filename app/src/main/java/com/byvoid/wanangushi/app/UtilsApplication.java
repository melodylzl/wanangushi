package com.byvoid.wanangushi.app;

import com.byvoid.wanangushi.base.BaseApplication;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

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
    }

    protected void initLogger(){
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
