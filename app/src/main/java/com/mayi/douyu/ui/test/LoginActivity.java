package com.mayi.douyu.ui.test;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mayi.douyu.R;
import com.mayi.douyu.mvpbase.MVPBaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者 by yugai 时间 16/11/1.
 * ＊ 邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {
    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.et_password)
    EditText mEtPassword;
    @Bind(R.id.bt_login)
    Button mBtLogin;


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFail() {
        Toast.makeText(this, "登陆失败", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.bt_login)
    public void onClick() {
        mPresenter.login(this,mEtName.getText().toString(),mEtPassword.getText().toString());
    }
}
