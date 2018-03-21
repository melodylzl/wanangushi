package com.byvoid.wanangushi.http;

import com.byvoid.wanangushi.model.TestModel;
import com.lzy.callback.JsonCallback;
import com.lzy.model.LzyResponse;
import com.lzy.okgo.OkGo;

/**
 * @author melody
 * @date 2018/1/24
 */
public class HttpRequestUtil {

    public static void getTextData(int id, JsonCallback<LzyResponse<TestModel>> callback){
        OkGo.<LzyResponse<TestModel>>get(Urls.getTestJson())
                .params("id",id)
                .execute(callback);
    }
}
