package com.byvoid.wanangushi.http;

import com.byvoid.wanangushi.model.Story;
import com.byvoid.wanangushi.model.StoryDetail;
import com.byvoid.wanangushi.model.UpdateInfo;
import com.byvoid.wanangushi.mvp.IBaseCallBack;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author melody
 * @date 2018/4/9
 */
public interface ApiService {

    @GET("story/list")
    Observable<BaseResult<List<Story>>> getStoryList(@Query("page") int page);
    @GET("story/detail")
    Observable<BaseResult<StoryDetail>> getStoryDetail(@Query("id") int id);
    @GET("update/check")
    Observable<BaseResult<UpdateInfo>> getUpdateInfo();
    @GET
    Call<ResponseBody> downloadApk(@Url String apkUrl);

}
