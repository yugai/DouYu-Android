package com.mayi.douyu.ui.main.panda;

import android.util.Log;

import com.mayi.douyu.api.ApiManager;
import com.mayi.douyu.api.DouyuService;
import com.mayi.douyu.entity.PDRoom;
import com.mayi.douyu.mvpbase.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * 作者 by yugai 时间 16/11/3.
 * ＊ 邮箱 784787081@qq.com
 */

public class LivePresenter extends BasePresenterImpl<LiveContract.View> implements LiveContract.Presenter {
    private static final String TAG = "LivePresenter";
    int page = 0;

    @Override
    public void getListData(final boolean forceUpdate) {
        if (forceUpdate) {
            page = 0;
        } else {
            page++;
        }
        ApiManager.getInstance().getPDList(page)
                .subscribe(new Subscriber<PDRoom>() {
                    @Override
                    public void onCompleted() {
                        mView.onRequestEnd();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onRequestError(e.getMessage());
                    }

                    @Override
                    public void onNext(PDRoom pdRoom) {
                        if (forceUpdate){
                            mView.onRefreshData(pdRoom.getItems());
                        }else{
                            mView.onRequestData(pdRoom.getItems());
                        }
                    }
                });
    }
}
