package com.mayi.douyu.api;

import com.mayi.douyu.entity.HYClassify;
import com.mayi.douyu.entity.HYDetails;
import com.mayi.douyu.entity.HYResponse;
import com.mayi.douyu.entity.HYRoom;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者 by yugai 时间 17/1/18.
 * ＊ 邮箱 784787081@qq.com
 */

public interface HuYaService {
    String URL="http://phone.huya.com/";

    @POST("api/game")
    Observable<HYResponse<List<HYRoom>>> getRoomList(@Query("gameId") int gameId, @Query("page") int page);

    @POST("/api/liveinfo")
    Observable<HYResponse<HYDetails>> getRoomInfo(@Query("sid") int sid, @Query("subsid") long subsid);

    @GET("api/game")
    Observable<HYResponse<List<HYClassify>>> getClassify();

}
