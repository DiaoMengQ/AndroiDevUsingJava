package com.diomun.learn.androidevusingjava.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.diomun.learn.androidevusingjava.R;
import com.diomun.learn.androidevusingjava.base.BaseActivity;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/1/21
 */
public class LoginActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
