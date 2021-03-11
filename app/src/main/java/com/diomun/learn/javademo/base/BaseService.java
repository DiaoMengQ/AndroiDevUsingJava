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
        Log.d(TAG, "onCreate: ");

        initData();
    }

    protected abstract void initData();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: -------------------服务启动");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * return的值固定为true，以使Activity重新绑定服务后执行onRebind() 而不是 bind()
     * @param intent
     * @return 默认值 super.onUnbind(intent)
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: Service 已解除绑定");
        return true;
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
