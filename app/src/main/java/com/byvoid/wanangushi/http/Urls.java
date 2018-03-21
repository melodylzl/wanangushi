package com.byvoid.wanangushi.http;

/**
 * @author melody
 * @date 2018/1/24
 */
public class Urls {

    private static final String TEST_URL = "/OkHttpUtils/jsonObject";

    public static String getTestJson(){
        return Server.HOST_BASE + TEST_URL;
    }

}
