package com.mayi.douyu.ui.main.huya;

import com.mayi.douyu.api.ApiManager;
import com.mayi.douyu.entity.HYRoom;
import com.mayi.douyu.mvpbase.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * 作者 by yugai 时间 17/1/18.
 * ＊ 邮箱 784787081@qq.com
 */

public class LivePresenter extends BasePresenterImpl<LiveContract.View> implements LiveContract.Presenter {
    int page = 0;
    int gameId = 1;

    @Override
    public void getListData(final boolean forceUpdate) {
        if (forceUpdate) {
            page = 0;
        } else {
            page++;
        }
        ApiManager.getInstance().getHYList(gameId, page)
                .subscribe(new Subscriber<List<HYRoom>>() {
                    @Override
                    public void onCompleted() {
                        mView.onRequestEnd();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onRequestError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<HYRoom> rooms) {
                        if (forceUpdate) {
                            mView.onRefreshData(rooms);
                        } else {
                            mView.onRequestData(rooms);
                        }
                    }
                });
    }
}
