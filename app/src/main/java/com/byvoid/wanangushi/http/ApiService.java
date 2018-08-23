package com.byvoid.wanangushi.http;

import com.byvoid.wanangushi.model.BaseResponse;
import com.byvoid.wanangushi.model.Role;
import com.byvoid.wanangushi.model.Story;
import com.byvoid.wanangushi.model.StoryDetail;
import com.byvoid.wanangushi.model.UpdateInfo;
import com.byvoid.wanangushi.mvp.IBaseCallBack;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author melody
 * @date 2018/4/9
 */
public interface ApiService {

    @GET("story/list")
    Observable<BaseResult<List<Story>>> getStoryList(@Query("lastId") int lastId);
    @GET("story/detail")
    Observable<BaseResult<StoryDetail>> getStoryDetail(@Query("id") int id);
    @GET("update/check")
    Observable<BaseResult<UpdateInfo>> getUpdateInfo();
    @GET
    Call<ResponseBody> downloadApk(@Url String apkUrl);
    @GET("role/list")
    Observable<BaseResult<List<Role>>> getRoleList(@Query("lastId") int lastId);
    @FormUrlEncoded
    @POST("role/create")
    Observable<BaseResult<BaseResponse>> createRole(@Field("name") String name, @Field("avatar") String avatar);
    @FormUrlEncoded
    @POST("story/create")
    Observable<BaseResult<BaseResponse>> createStory(@Field("name") String name, @Field("cover") String cover, @Field("talkListJson") String talkListJson);

}
