package com.mayi.douyu.util;

import android.util.Log;

import com.google.gson.Gson;
import com.mayi.douyu.entity.DYDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者 by yugai 时间 16/11/29.
 * ＊ 邮箱 784787081@qq.com
 */

public class VideoUrlUtils {
    public static void getDouyuRoomParams(int roomId, final VideoRequest videoRequest) {
        long time = System.currentTimeMillis()/(1000*60);
        String did = UUID.randomUUID().toString().toUpperCase();
        did = did.replace("-", "");
        String str = roomId + did + "A12Svb&%1UUmf@hC" + time;
        String sign = MD5.strToMd5Low32(str);


        Log.i("Tag", "getDouyuRoomParams: "+str+"-----"+sign+"-----"+did);



        OkHttpClient mOkHttpClient=new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("cdn", "ws")
                .add("rate", "0")
                .add("tt", String.valueOf(time))
                .add("did", did)
                .add("sign", sign)
                .build();

        Request request = new Request.Builder()
                .url("http://www.douyu.com/lapi/live/getPlay/"+roomId)
                .post(formBody)
                .build();
        Log.i("Tag", "http://www.douyu.com/lapi/live/getPlay/"+roomId+"?cdn=ws&rate=0&tt="+String.valueOf(time)+"&did="+did+"&sign="+sign);
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String str = response.body().string();
                    Log.i("Tag", str);
                    Gson gson=new Gson();
                    JSONObject json=new JSONObject(str);
                    DYDetails detail=gson.fromJson(json.getString("data"),DYDetails.class);
                    String url = detail.getRtmp_url() + "/" + detail.getRtmp_live();
                    videoRequest.onRequestUrl(url);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
    }
    public interface VideoRequest{
        void onRequestUrl(String url);
        void onFailure();
    }
}
