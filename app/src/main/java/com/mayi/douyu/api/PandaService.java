package com.mayi.douyu.api;

import com.mayi.douyu.entity.PDClassify;
import com.mayi.douyu.entity.PDDetails;
import com.mayi.douyu.entity.PDResponse;
import com.mayi.douyu.entity.PDRoom;

import java.util.List;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者 by yugai 时间 16/12/21.
 * ＊ 邮箱 784787081@qq.com
 */

public interface PandaService {
    String URL="http://static.api.m.panda.tv/";

    @POST("android_hd/alllist_.json")
    Observable<PDResponse<PDRoom>> getRoomList(@Query("pageno")int page);

    @GET("android_hd/cate.json")
    Observable<PDResponse<List<PDClassify>>> getClassify();
}
