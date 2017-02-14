package com.mayi.douyu.ui.search;

import com.mayi.douyu.api.ApiManager;
import com.mayi.douyu.entity.DYSearchDetails;
import com.mayi.douyu.mvpbase.BasePresenterImpl;

import rx.Subscriber;

/**
 * 作者 by yugai 时间 17/1/19.
 * ＊ 邮箱 784787081@qq.com
 */

public class SearchPresenter extends BasePresenterImpl<SearchContract.View> implements SearchContract.Presenter{

    @Override
    public void search(String text) {
        ApiManager.getInstance().search(text)
                .subscribe(new Subscriber<DYSearchDetails>() {
                    @Override
                    public void onCompleted() {
                        mView.onRequestEnd();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onRequestError(e.getMessage());
                    }

                    @Override
                    public void onNext(DYSearchDetails dySearchDetails) {
                        mView.onSearchRequest(dySearchDetails.getRoom());
                    }
                });
    }
}
