package com.diomun.learn.javademo.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.diomun.learn.javademo.base.BaseService;
import com.diomun.learn.javademo.ui.activity.MainActivity;
import com.diomun.learn.javademo.util.MyTimeUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/2/23
 */
public class BackService extends BaseService {
    CommandReceiver cmdReceiver;
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
    protected void initData() {
        flag = true;
        cmdReceiver = new CommandReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 线程工厂，可以对每个线程进行单独处理，如设定线程的标识符
        final ThreadFactory ScheduledTF = r -> {
            Log.d(TAG, "run: test ThreadFactory");
            Thread scheduledThread = new Thread(r,"Scheduled-task");
            return scheduledThread;
        };

        ScheduledExecutorService singleThreadScheduledExecutor = new ScheduledThreadPoolExecutor(1, ScheduledTF);
        singleThreadScheduledExecutor.scheduleWithFixedDelay(() -> Log.e(TAG, "run: 定时任务" + MyTimeUtils.getSystemTime()), 1, 3, TimeUnit.SECONDS);

        return super.onStartCommand(intent, flags, startId);
    }

    // 接收广播
    private class CommandReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int cmd = intent.getIntExtra("cmd", -1);
            if (cmd == MainActivity.CMD_STOP_SERVICE) {//如果等于0
                flag = false; // 停止线程
                stopSelf(); // 停止服务
            }
        }

    }

    public void doJob() {
        ExecutorService catchThreadExecutor =
                new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                        60L, TimeUnit.SECONDS,
                        new SynchronousQueue<Runnable>());

        // new Thread() {
        //     @Override
        //     public void run() {
        //         while (flag) {//如果==true执行发送广播
        //             try {
        //                 Thread.sleep(1000);//休眠1秒
        //             } catch (InterruptedException e) {
        //                 e.printStackTrace();
        //             }
        //             Log.i("onStartCommand", "run=");
        //             Intent intent = new Intent();
        //             intent.setAction("AAAAA");
        //             intent.putExtra("data", UUID.randomUUID() + "");
        //             sendBroadcast(intent);//发送广播名称aaaaa 参数名字data
        //
        //         }
        //     }
        // }.start();
    }
}
