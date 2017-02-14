package com.mayi.douyu.api;

import com.mayi.douyu.entity.DYResponse;
import com.mayi.douyu.entity.DYDetails;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 作者 by yugai 时间 16/11/30.
 * ＊ 邮箱 784787081@qq.com
 */

public interface DouyuRoomService {
    String URL="http://www.douyu.com/";
    @FormUrlEncoded
    @POST("lapi/live/getPlay/{roomId}")
    Observable<DYResponse<DYDetails>> getRoomUrl(@Path("roomId") String id,
                                                 @Field("cdn") String cdn,
                                                 @Field("rate") String rate,
                                                 @Field("tt") String tt,
                                                 @Field("did") String did,
                                                 @Field("sign") String sign);
}
