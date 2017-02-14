package com.mayi.douyu.ui.main.douyu;

import android.util.Log;

import com.mayi.douyu.api.ApiManager;
import com.mayi.douyu.api.DouyuService;
import com.mayi.douyu.entity.DYRoom;
import com.mayi.douyu.mvpbase.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * 作者 by yugai 时间 16/11/3.
 * ＊ 邮箱 784787081@qq.com
 */

public class LivePresenter extends BasePresenterImpl<LiveContract.View> implements LiveContract.Presenter{
    private static final String TAG = "LivePresenter";
    private int page=0;

    @Override
    public void getListData(final boolean forceUpdate) {
        if (forceUpdate){
            page=0;
        }else{
            page+= DouyuService.PAGE_SIZE;
        }
        Log.i(TAG, "getListData: "+page);
        ApiManager.getInstance().getDYList(page).
                subscribe(new Subscriber<List<DYRoom>>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(List<DYRoom> DYRooms) {
                for (DYRoom DYRoom : DYRooms) {
                    Log.i(TAG, "onNext: "+ DYRoom);
                }
                if (forceUpdate){
                    mView.onRefreshData(DYRooms);
                }else{
                    mView.onRequestData(DYRooms);
                }
            }
        }) ;
    }
}
