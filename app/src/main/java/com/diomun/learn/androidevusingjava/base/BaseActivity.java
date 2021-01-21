package com.diomun.learn.androidevusingjava.base;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.badoo.mobile.util.WeakHandler;
import com.diomun.learn.androidevusingjava.R;

import java.util.Objects;

/**
 * Activity 基本类，设定通用方法
 *
 * Author: DiaoMengQi
 * Email: dmq1212@qq.com
 * created on 2021/1/19
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();
    protected BaseActivity mContext;
    protected View mView;
    private AlertDialog mDialogLoading;

    protected WeakHandler mHandler = new WeakHandler(msg -> {
        handlerMsg(msg);
        return false;
    });
    /**
     * 处理handler消息
     */
    public void handlerMsg(Message msg) {}
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
        if(mDialogLoading != null && !mDialogLoading.isShowing()) {
            mDialogLoading.show();
            @SuppressLint("InflateParams") View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_progress, null);
            mDialogLoading.setContentView(view);
            Objects.requireNonNull(mDialogLoading.getWindow()).setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            mDialogLoading.getWindow().getDecorView().setBackgroundColor(0x00000000);
            mDialogLoading.getWindow().getDecorView().setPadding(0, 0, 0, 0);
        }
    }

    /**
     *隐藏转圈圈
     */
    public void hideLoading() {
        if(mDialogLoading != null && mDialogLoading.isShowing()) {
            mDialogLoading.dismiss();
        }
    }

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 设置数据
     */
    public abstract void initData();

}
