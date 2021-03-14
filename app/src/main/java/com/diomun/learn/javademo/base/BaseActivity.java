package com.diomun.learn.javademo.base;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.badoo.mobile.util.WeakHandler;
import com.bumptech.glide.Glide;
import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.ui.activity.MainActivity;

import java.util.Objects;

import butterknife.ButterKnife;
import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;

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
    private int layoutRes = -1;
    private CustomEventListener customErrorListener = new CustomEventListener();

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
        Log.d(TAG, String.format("%s 从其他应用返回onNewIntent", TAG));
    }

    /**
     * 初始化根布局
     *
     * @return int layout的唯一识别号(R.layout.XXX)
     */
    public abstract int initLayout();

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, String.format("%s onCreate", TAG));
        mContext = this;
        layoutRes = initLayout();
        setContentView(layoutRes);
        ButterKnife.bind(this);

        // 切勿顛倒 data 和 view 的初始化順序
        initData();
        initView();

        // 崩溃信息页 设置项
        // 整个配置属性，可以设置零个或多个，如果没有任何配置，程序崩溃显示的是默认的设置
        CaocConfig.Builder.create()
                //程序在后台时，发生崩溃的三种处理方式
                //BackgroundMode.BACKGROUND_MODE_SHOW_CUSTOM: //当应用程序处于后台时崩溃，也会启动错误页面（default），
                //BackgroundMode.BACKGROUND_MODE_CRASH:      //当应用程序处于后台崩溃时显示默认系统错误（一个系统提示的错误对话框），
                //BackgroundMode.BACKGROUND_MODE_SILENT:     //当应用程序处于后台时崩溃，默默地关闭程序！
                // .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT)
                .enabled(true)     //false表示对崩溃的拦截阻止。用它来禁用customactivityoncrash框架
                // .showErrorDetails(false) //这将隐藏错误活动中的“错误详细信息”按钮，从而隐藏堆栈跟踪,—》针对框架自带程序崩溃后显示的页面有用(DefaultErrorActivity)。。
                // .showRestartButton(false)    //是否可以重启页面,针对框架自带程序崩溃后显示的页面有用(DefaultErrorActivity)。
                .trackActivities(true)     //错误页面中显示错误详细信息；针对框架自带程序崩溃后显示的页面有用(DefaultErrorActivity)。
                .minTimeBetweenCrashesMs(3000)      //定义应用程序崩溃之间的最短时间，以确定我们不在崩溃循环中。比如：在规定的时间内再次崩溃，框架将不处理，让系统处理！
                // .errorDrawable(R.mipmap.ic_launcher)     //崩溃页面显示的图标 default: bug image
                .restartActivity(MainActivity.class)      //重新启动后的页面
                // .errorActivity(MyDefaultErrorActivity.class) //程序崩溃后显示的页面
                .eventListener(customErrorListener)//设置监听
                .apply();

        CustomActivityOnCrash.install(this);
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

    /**
     * 监听程序崩溃/重启
     */
    private static class CustomEventListener implements CustomActivityOnCrash.EventListener {
        private static String crashTag = CustomEventListener.class.getSimpleName();

        //程序崩溃回调
        @Override
        public void onLaunchErrorActivity() {
            Log.e(crashTag, "程序崩溃回调");
        }

        //重启程序时回调
        @Override
        public void onRestartAppFromErrorActivity() {
            Log.e(crashTag, "重启程序时回调");
        }

        //在崩溃提示页面关闭程序时回调
        @Override
        public void onCloseAppFromErrorActivity() {
            Log.e(crashTag, "在崩溃提示页面关闭程序时回调");
        }

    }

    /**
     * 初始化转圈圈
     */
    public void initLoading() {
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
            ImageView ivLoading = view.findViewById(R.id.iv_loading);
            mDialogLoading.setContentView(view);
            Objects.requireNonNull(mDialogLoading.getWindow()).setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            mDialogLoading.getWindow().getDecorView().setBackgroundColor(0x00000000);
            mDialogLoading.getWindow().getDecorView().setPadding(0, 0, 0, 0);
            Glide.with(mContext).load(R.drawable.loading).into(ivLoading);
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

}
