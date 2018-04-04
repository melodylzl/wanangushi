package com.byvoid.wanangushi.http;

import com.byvoid.wanangushi.model.Story;
import com.lzy.callback.JsonCallback;
import com.lzy.model.LzyResponse;
import com.lzy.okgo.OkGo;

import java.util.List;

/**
 * @author melody
 * @date 2018/1/24
 */
public class HttpRequestUtil {

    public static void getStoryList(int page,JsonCallback<LzyResponse<List<Story>>> callback){
        OkGo.<LzyResponse<List<Story>>>get(Urls.getUrlStoryList())
                .params("page",page)
                .execute(callback);
    }
}
