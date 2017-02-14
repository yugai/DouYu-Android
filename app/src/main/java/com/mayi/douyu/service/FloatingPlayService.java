package com.mayi.douyu.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.mayi.douyu.R;
import com.mayi.douyu.api.ApiManager;
import com.mayi.douyu.entity.DYDetails;
import com.mayi.douyu.widget.FloatWindow;

import rx.functions.Action1;

/**
 * 作者 by yugai 时间 17/1/24.
 * ＊ 邮箱 784787081@qq.com
 */

public class FloatingPlayService extends Service{
    private static final String TAG = "FloatingPlayService";
    FloatWindow mFloatWindow;
    MyBinder mBinder;
    private String roomId;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBinder=new MyBinder();
        mFloatWindow=new FloatWindow(this);
        View floatView= LayoutInflater.from(this).inflate(R.layout.window_float,null);
        mFloatWindow.setContentView(floatView);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void show(){
        mFloatWindow.show();
        getVideoUrl();

    }
    public void dismiss(){
        mFloatWindow.dismiss();
        mFloatWindow.stop();
    }

    public class MyBinder extends Binder {
        public void startPlay(String roomId){
            FloatingPlayService.this.roomId=roomId;
            show();
        }
        public void closePlay(){
            dismiss();
        }
    }

    /**
     * 获取直播url
     */
    private void getVideoUrl() {
        ApiManager.getInstance().getRoomUrl(roomId)
                .subscribe(new Action1<DYDetails>() {
                    @Override
                    public void call(DYDetails dyDetails) {
                        mFloatWindow.start(dyDetails.getRtmp_url() + "/" + dyDetails.getRtmp_live());
                    }
                });
    }
}
