package com.mayi.douyu.mvpbase;

/**
 * 作者 by yugai 时间 16/11/1.
 * ＊ 邮箱 784787081@qq.com
 */

public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V>{
    protected V mView;
    @Override
    public void attachView(V view) {
        mView=view;
    }

    @Override
    public void detachView() {
        mView=null;
    }
}
