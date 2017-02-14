package com.mayi.douyu.ui.video.full;

import android.util.Log;

import com.mayi.douyu.api.ApiManager;
import com.mayi.douyu.entity.DYDetails;
import com.mayi.douyu.entity.HYDetails;
import com.mayi.douyu.entity.PDDetails;
import com.mayi.douyu.mvpbase.BasePresenterImpl;
import com.yugai.danmu.DouYu;
import com.yugai.danmu.message.ChatMsg;
import com.yugai.danmu.message.handler.ChatMsgHandler;
import com.yugai.danmu.socket.Connector;
import com.yugai.danmu.util.LogUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者 by yugai 时间 16/11/29.
 * ＊ 邮箱 784787081@qq.com
 */

public class FullVideoPresenter extends BasePresenterImpl<FullVideoContract.View> implements FullVideoContract.Presenter {
    private static final String TAG = "FullVideoPresenter";
    DouYu mDouYu;

    @Override
    public void getDouyuVideo(String roomId) {
        ApiManager.getInstance().getRoomUrl(roomId).subscribe(new Subscriber<DYDetails>() {
            @Override
            public void onCompleted() {
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(DYDetails dYDetails) {
                mView.onRequestData(dYDetails.getRtmp_url() + "/" + dYDetails.getRtmp_live());
            }
        });
    }

    @Override
    public void getPandaVideo(String roomId) {
        ApiManager.getInstance().getPDRoomDetails(roomId).subscribe(new Subscriber<PDDetails>() {
            @Override
            public void onCompleted() {
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(PDDetails pdDetails) {
                String plflag = pdDetails.getVideoinfo().getPlflag();
                String url = "http://pl-hls" + plflag.substring(plflag.indexOf("_") + 1, plflag.length()) + ".live.panda.tv/live_panda/" + pdDetails.getVideoinfo().getRoom_key() + ".m3u8";
                mView.onRequestData(url);
            }
        });
    }

    @Override
    public void getHuYaVideo(int sid, long subsid) {
        ApiManager.getInstance().getHYRoomDetails(sid, subsid)
                .subscribe(new Subscriber<HYDetails>() {
                    @Override
                    public void onCompleted() {
                        mView.onRequestEnd();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onRequestError(e.getMessage());
                    }

                    @Override
                    public void onNext(HYDetails hyDetails) {

                    }
                });
    }

    @Override
    public void connectDanMu(String roomId) {
        mDouYu = new DouYu(roomId);
        Observable.create(new Observable.OnSubscribe<ChatMsg>() {
            @Override
            public void call(final Subscriber<? super ChatMsg> subscriber) {
                mDouYu.addMessageHandler(new ChatMsgHandler() {
                    @Override
                    protected void handleMessage0(Connector connector, ChatMsg chatMsg) {
                        super.handleMessage0(connector, chatMsg);
                        subscriber.onNext(chatMsg);
                    }
                }).start();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<ChatMsg>() {
                    @Override
                    public void call(ChatMsg chatMsg) {
                        Log.i(TAG, "call: "+chatMsg.getMessage());
                        if (mView!=null)
                        mView.receiveMessage(chatMsg);
                    }
                });
    }

    @Override
    public void closeDanMu() {
        try {
            if (mDouYu != null) {
                mDouYu.stop();
            }
        } catch (Exception var2) {
            var2.printStackTrace();
            Log.i(TAG, "closeDanMu: error");
        }
    }
}
