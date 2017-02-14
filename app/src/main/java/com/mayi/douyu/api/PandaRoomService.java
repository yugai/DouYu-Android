package com.mayi.douyu.api;

import com.mayi.douyu.entity.PDDetails;
import com.mayi.douyu.entity.PDResponse;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者 by yugai 时间 17/1/17.
 * ＊ 邮箱 784787081@qq.com
 */

public interface PandaRoomService {
    String URL="http://www.panda.tv/";
    @POST("api_room_v2")
    Observable<PDResponse<PDDetails>> getRoomDetails(@Query("roomid")String roomId);
}
