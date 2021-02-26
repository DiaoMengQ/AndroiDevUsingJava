package com.diomun.learn.javademo.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/2/25
 * @desc
 */
public abstract class BaseService extends Service {
    protected String TAG = this.getClass().getSimpleName();

    @Nullable
    @Override
    public abstract IBinder onBind(Intent intent);

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: 父类");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: Service 已解除绑定");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind: ");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory: 内存不足！");
    }
}
