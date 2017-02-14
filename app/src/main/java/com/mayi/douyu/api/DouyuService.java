package com.mayi.douyu.api;

import com.mayi.douyu.entity.DYClassify;
import com.mayi.douyu.entity.DYResponse;
import com.mayi.douyu.entity.DYRoom;
import com.mayi.douyu.entity.DYSearchDetails;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者 by yugai 时间 16/11/24.
 * ＊ 邮箱 784787081@qq.com
 */

public interface DouyuService {
    String URL="http://capi.douyucdn.cn/api/v1/";
    int PAGE_SIZE=10;
    @POST("live")
    Observable<DYResponse<List<DYRoom>>> getRoomList(@Query("limit") int limit, @Query("offset") int offset);

    @GET("getColumnDetail?shortName=game")
    Observable<DYResponse<List<DYClassify>>> getClassify();

    @GET("searchNew/{text}/1?limit=1&offset=0")
    Observable<DYResponse<DYSearchDetails>> search(@Path("text") String text);
}
