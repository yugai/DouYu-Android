package com.mayi.douyu.ui.test;

import android.content.Context;

import com.mayi.douyu.mvpbase.BasePresenterImpl;
import com.mayi.douyu.util.rx.RxBus;

/**
 * 作者 by yugai 时间 16/11/1.
 * ＊ 邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{
    @Override
    public void login(Context context, String userName, String password) {
        if (userName.equals("784787081")&&password.equals("123456")){
            mView.onLoginSuccess();
        }else{
            mView.onLoginFail();
        }
    }
}
