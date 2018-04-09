package com.byvoid.wanangushi.http;

/**
 * @author melody
 * @date 2018/1/24
 */
public class Urls {

    private static final String URL_STORY_LIST = "story/list";

    public static String getUrlStoryList(){
        return Server.HOST_BASE + URL_STORY_LIST;
    }

}
