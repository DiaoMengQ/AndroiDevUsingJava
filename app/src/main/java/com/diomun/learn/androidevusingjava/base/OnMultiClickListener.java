package com.diomun.learn.androidevusingjava.base;

import android.view.View;

/**
 * 实现对按钮连续点击的控制
 * 目前存在问题： 当多个控件使用同一个监听器时，点击了其中一个，就不能点击同监听器监听的其他控件
 *
 * Author: DiaoMengQi
 * Email: dmq1212@qq.com
 * created on 2021/1/21
 */
public abstract class OnMultiClickListener implements View.OnClickListener {
    // 两次点击按钮之间的点击间隔不能少于指定时间
    private static final int MIN_CLICK_DELAY_TIME = 5000;
    private static long lastClickTime;

    public abstract void onMultiClick(View v);

    @Override
    public void onClick(View v) {
        long curClickTime = System.currentTimeMillis();
        if((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            // 超过点击间隔后再将lastClickTime重置为当前点击时间
            lastClickTime = curClickTime;
            onMultiClick(v);
        }
    }

}
