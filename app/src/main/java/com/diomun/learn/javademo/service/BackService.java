package com.diomun.learn.javademo.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.diomun.learn.javademo.base.BaseService;
import com.diomun.learn.javademo.util.MyTimeUtils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/2/23
 */
public class BackService extends BaseService {
    private static final int CMD_STOP_SERVICE = 0;
    private boolean flag;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return new MyBinder();
    }

    /**
     * 需要返回给前台的 Binder类
     */
    public static class MyBinder extends Binder {
        public void showTip() {
            Log.d("BackService MyBinder", "showTip: 已和Activity绑定");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        flag = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final ThreadFactory timedThreadFactory = r -> new Thread(() -> {
            r.run();
            Log.d(TAG, "run: test ThreadFactory");
        });

        ScheduledExecutorService singleThreadScheduledExecutor = new ScheduledThreadPoolExecutor(1, timedThreadFactory);
        singleThreadScheduledExecutor.scheduleWithFixedDelay(() -> {
            Log.e(TAG, "run: 定时任务" + MyTimeUtils.getSystemTime());
        }, 1, 3, TimeUnit.SECONDS);

        return super.onStartCommand(intent, flags, startId);
    }

    //接受广播
    private class CommandReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int cmd = intent.getIntExtra("cmd", -1);
            if (cmd == BackService.CMD_STOP_SERVICE) {//如果等于0
                flag = false;//停止线程
                stopSelf();//停止服务
            }
        }

    }
}
