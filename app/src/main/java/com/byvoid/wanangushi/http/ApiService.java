package com.byvoid.wanangushi.http;

import com.byvoid.wanangushi.model.Story;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author melody
 * @date 2018/4/9
 */
public interface ApiService {

    @GET("story/list")
    Observable<BaseResult<List<Story>>> getStoryList(@Query("page") int page);

}
