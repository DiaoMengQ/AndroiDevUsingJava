package com.diomun.learn.javademo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/2/23
 */
public class BackServ extends Service {
    private final String TAG = "BackServ";

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
        Log.d(TAG, "onStartCommand: 服务启动，不可交互，定时任务");

        final ThreadFactory timedThreadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        Log.d(TAG, "run: 线程测试");
                    }
                };
            }
        };
        ScheduledExecutorService singleThreadScheduledExecutor = new ScheduledThreadPoolExecutor(1,timedThreadFactory);

        return super.onStartCommand(intent, flags, startId);
    }
}
