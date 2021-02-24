package com.diomun.learn.javademo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.diomun.learn.javademo.util.MyTimeUtils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/2/23
 */
public class BackService extends Service {
    private final String TAG = "BackService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: 服务启动，不可交互，定时任务");


        final ThreadFactory timedThreadFactory = r -> new Thread(() -> {
            r.run();
            Log.d(TAG, "run: test ThreadFactory");
        });

        ScheduledExecutorService singleThreadScheduledExecutor = new ScheduledThreadPoolExecutor(1, timedThreadFactory);
        singleThreadScheduledExecutor.scheduleWithFixedDelay(() -> {
            Log.e(TAG, "run: 定时任务" + MyTimeUtils.getSystemTime());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, 1, 1, TimeUnit.SECONDS);
        Log.d(TAG, "onStartCommand: start end");

        return super.onStartCommand(intent, flags, startId);
    }
}
