package com.mayi.douyu.api;

import android.util.Log;

import com.mayi.douyu.entity.DYClassify;
import com.mayi.douyu.entity.DYDetails;
import com.mayi.douyu.entity.DYRoom;
import com.mayi.douyu.entity.DYSearchDetails;
import com.mayi.douyu.entity.HYClassify;
import com.mayi.douyu.entity.HYDetails;
import com.mayi.douyu.entity.HYRoom;
import com.mayi.douyu.entity.PDClassify;
import com.mayi.douyu.entity.PDDetails;
import com.mayi.douyu.entity.PDResponse;
import com.mayi.douyu.entity.PDRoom;
import com.mayi.douyu.util.MD5;
import com.mayi.douyu.util.rx.RxResultHelper;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * 作者 by yugai 时间 16/11/24.
 * ＊ 邮箱 784787081@qq.com
 */

public class ApiManager {
    private static final String TAG = "ApiManager";
    private Retrofit mRetrofit;
    private DouyuService mDouyuService;
    private DouyuRoomService mDouyuRoomService;
    private PandaService mPandaService;
    private PandaRoomService mPandaRoomService;
    private HuYaService mHuYaService;
    private static final int DEFAULT_TIMEOUT = 5;
    OkHttpClient.Builder builder;


    private ApiManager() {
        //手动创建一个OkHttpClient并设置超时时间
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(TAG, "log: "+message);
            }
        });
        loggingInterceptor.setLevel(level);
        builder.addInterceptor(loggingInterceptor);
    }
    private static class SingletonHolder{
        private static final ApiManager INSTANCE = new ApiManager();
    }

    //获取单例
    public static ApiManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private <T> T configRetrofit(Class<T> service,String url) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return mRetrofit.create(service);

    }

    /**
     * 斗鱼直播列表数据
     * @param page 页码
     * @return
     */
    public Observable<List<DYRoom>> getDYList(int page){
        if (mDouyuService==null) mDouyuService=configRetrofit(DouyuService.class,DouyuService.URL);
        return mDouyuService.getRoomList(DouyuService.PAGE_SIZE,page)
                .compose(RxResultHelper.<List<DYRoom>>douyuResult());
    }

    /**
     * 斗鱼游戏频道分类
     * @return
     */
    public Observable<List<DYClassify>> getDYClassify(){
        if (mDouyuService==null) mDouyuService=configRetrofit(DouyuService.class,DouyuService.URL);
        return mDouyuService.getClassify()
                .compose(RxResultHelper.<List<DYClassify>>douyuResult());
    }

    /**
     * 斗鱼房间直播数据
     * @param roomId 房间id
     * @return
     */
    public Observable<DYDetails> getRoomUrl(String roomId){
        long time = System.currentTimeMillis()/(1000*60);
        String did = UUID.randomUUID().toString().toUpperCase();
        did = did.replace("-", "");
        String str = roomId + did + "A12Svb&%1UUmf@hC" + time;
        String sign = MD5.strToMd5Low32(str);
        Log.i(TAG, "getDouyuRoomParams: "+roomId+"-------"+str+"-----"+sign+"-----"+did+"------");

        if (mDouyuRoomService ==null) mDouyuRoomService =configRetrofit(DouyuRoomService.class, DouyuRoomService.URL);
        return mDouyuRoomService.getRoomUrl(roomId,"ws","0",String.valueOf(time),did,sign)
                .compose(RxResultHelper.<DYDetails>douyuResult());
    }

    /**
     * 斗鱼搜索
     * @param text 房间号或者关键字
     * @return
     */
    public Observable<DYSearchDetails> search(String text){
        if (mDouyuService==null) mDouyuService=configRetrofit(DouyuService.class,DouyuService.URL);
        return mDouyuService.search(text)
                .compose(RxResultHelper.<DYSearchDetails>douyuResult());
    }

    /**
     * 熊猫直播列表
     * @param page 页码
     * @return
     */
    public Observable<PDRoom> getPDList(int page){
        if (mPandaService==null) mPandaService=configRetrofit(PandaService.class,PandaService.URL);
        return mPandaService.getRoomList(page)
                .compose(RxResultHelper.<PDRoom>pandaResult());
    }

    /**
     * 熊猫分类
     * @return
     */
    public Observable<List<PDClassify>> getPDClassify(){
        if (mPandaService==null) mPandaService=configRetrofit(PandaService.class,PandaService.URL);
        return mPandaService.getClassify()
                .compose(RxResultHelper.<List<PDClassify>>pandaResult());
    }

    /**
     * 获取直播详情
     * @param roomId
     * @return
     */
    public Observable<PDDetails> getPDRoomDetails(String roomId){
        if (mPandaRoomService==null) mPandaRoomService=configRetrofit(PandaRoomService.class,PandaRoomService.URL);
        return mPandaRoomService.getRoomDetails(roomId)
                .compose(RxResultHelper.<PDDetails>pandaResult());
    }

    /**
     * 虎牙直播
     * @param gameId 游戏分类
     * @param page 页码
     * @return
     */
    public Observable<List<HYRoom>> getHYList(int gameId,int page){
        if (mHuYaService==null) mHuYaService=configRetrofit(HuYaService.class,HuYaService.URL);
        return mHuYaService.getRoomList(gameId,page)
                .compose(RxResultHelper.<List<HYRoom>>huyaResult());
    }

    public Observable<HYDetails> getHYRoomDetails(int sid,long subsid){
        if (mHuYaService==null) mHuYaService=configRetrofit(HuYaService.class,HuYaService.URL);
        return mHuYaService.getRoomInfo(sid,subsid)
                .compose(RxResultHelper.<HYDetails>huyaResult());
    }

    public Observable<List<HYClassify>> getHYClassify(){
        if (mHuYaService==null) mHuYaService=configRetrofit(HuYaService.class,HuYaService.URL);
        return mHuYaService.getClassify()
                .compose(RxResultHelper.<List<HYClassify>>huyaResult());
    }
}
