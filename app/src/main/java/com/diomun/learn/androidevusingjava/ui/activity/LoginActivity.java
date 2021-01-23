package com.diomun.learn.androidevusingjava.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.diomun.learn.androidevusingjava.R;
import com.diomun.learn.androidevusingjava.base.BaseActivity;

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
    @BindView(R.id.text_top)
    ConstraintLayout textTop;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.tv_forgot, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Toast.makeText(mContext, "登录", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onViewClicked: login");
                break;
            case R.id.tv_forgot:
                break;
            case R.id.tv_register:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}
