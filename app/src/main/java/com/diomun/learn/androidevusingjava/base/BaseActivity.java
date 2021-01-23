package com.diomun.learn.androidevusingjava.base;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.badoo.mobile.util.WeakHandler;
import com.diomun.learn.androidevusingjava.R;

import java.util.Objects;

/**
 * Activity 基本类，设定通用方法
 * <p>
 * Author: DiaoMengQi
 * Email: dmq1212@qq.com
 * created on 2021/1/19
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();
    protected BaseActivity mContext;
    protected View mView;
    private AlertDialog mDialogLoading;
    private int layoutId = -1;

    protected WeakHandler mHandler = new WeakHandler(msg -> {
        handlerMsg(msg);
        return false;
    });

    /**
     * 处理handler消息
     */
    public void handlerMsg(Message msg) {
    }

    public WeakHandler getHandler() {
        return mHandler;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, String.format("%s onNewIntent", TAG));
    }

    /**
     * 初始化转圈圈
     */
    private void initLoading() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        mDialogLoading = builder.create();
        mDialogLoading.setCanceledOnTouchOutside(false);
    }

    /**
     * 显示转圈圈
     */
    public void showLoading() {
        if (mDialogLoading != null && !mDialogLoading.isShowing()) {
            mDialogLoading.show();
            @SuppressLint("InflateParams") View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_progress, null);
            mDialogLoading.setContentView(view);
            Objects.requireNonNull(mDialogLoading.getWindow()).setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            mDialogLoading.getWindow().getDecorView().setBackgroundColor(0x00000000);
            mDialogLoading.getWindow().getDecorView().setPadding(0, 0, 0, 0);
        }
    }

    /**
     * 隐藏转圈圈
     */
    public void hideLoading() {
        if (mDialogLoading != null && mDialogLoading.isShowing()) {
            mDialogLoading.dismiss();
        }
    }

    /**
     * 设置根布局
     * @return int layout的唯一识别号(R.layout.XXX)
     */
    public abstract int initLayout();

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 设置数据
     */
    public abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, String.format("%s onCreate", TAG));
        mContext = this;
        initLoading();

        layoutId = initLayout();
        setContentView(layoutId);
        initView();
        initData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
