package com.diomun.learn.javademo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/2/23
 */
public class BackServ extends Service {
    private final String TAG = this.getClass().getSimpleName();
    private Thread mThread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mThread = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        // 等待停止线程
                        if (this.isInterrupted()) {
                            throw new InterruptedException();
                        }
                        // 耗时操作
                        Log.d(TAG, "run: 执行耗时操作");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        return super.onStartCommand(intent, flags, startId);
    }
}
