package com.diomun.learn.javademo.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.base.BaseService;
import com.diomun.learn.javademo.ui.activity.MainActivity;
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
    private CommandReceiver cmdReceiver;

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
    protected void initData() {
        cmdReceiver = new CommandReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startTask();
        return super.onStartCommand(intent, flags, startId);
    }

    private void startTask() {
        // IntentFilter intentFilter = new IntentFilter();
        // intentFilter.addAction(getString(R.string.action_stopBackService));
        // registerReceiver(cmdReceiver, intentFilter);

        // 线程工厂，可以对每个线程进行单独处理，如设定线程的标识符
        final ThreadFactory ScheduledTF = r -> {
            Thread scheduledThread = new Thread(r, "Scheduled-task"); // 这是对r的处理，别忘了把原本的 r 传进去！
            return scheduledThread;
        };

        ScheduledExecutorService singleThreadScheduledExecutor = new ScheduledThreadPoolExecutor(1, ScheduledTF);
        singleThreadScheduledExecutor.scheduleWithFixedDelay(() ->
                Log.e(TAG, "run: 定时任务" + MyTimeUtils.getSystemTime()), 1, 3, TimeUnit.SECONDS);

    }

    // 接收广播
    private class CommandReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int cmd = intent.getIntExtra("cmd", -1);
            Log.d(TAG, "onReceive: 接收广播参数" + cmd);
            if (cmd == MainActivity.CMD_STOP_SERVICE) { //如果等于0
                // TODO: 问题:停止服务后线程仍继续运行
                stopSelf(); // 停止服务
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 因为是服务开启的广播，所以当服务被关闭时同时清除广播
        // unregisterReceiver(cmdReceiver);
        // Log.d(TAG, "onDestroy: 清除广播注册");
    }

}
