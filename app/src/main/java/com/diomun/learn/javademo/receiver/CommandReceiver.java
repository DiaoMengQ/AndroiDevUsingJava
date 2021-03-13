package com.diomun.learn.javademo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/13
 * @desc 广播接收器
 */
public class CommandReceiver extends BroadcastReceiver {
    private String TAG = "-------------------- CommandReceiver 广播接收器";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: 接收到广播");
        Toast.makeText(context, "接收到广播", Toast.LENGTH_SHORT).show();
    }
}
