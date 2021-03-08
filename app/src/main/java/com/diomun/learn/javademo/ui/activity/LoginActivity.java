package com.diomun.learn.javademo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/1/21
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.tv_proj)
    TextView tvProj;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_forgot)
    TextView tvForgot;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.layout_head)
    ConstraintLayout layoutHead;

    @Override
    public int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_login, R.id.tv_forgot, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Log.d(TAG, "onViewClicked: login");
                break;
            case R.id.tv_forgot:
                break;
            case R.id.tv_register:
                startActivity(new Intent(mContext, RegisterActivity.class));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}
