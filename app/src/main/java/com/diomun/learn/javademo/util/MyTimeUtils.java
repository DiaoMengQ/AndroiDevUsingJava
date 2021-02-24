package com.diomun.learn.javademo.util;

import java.text.SimpleDateFormat;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/2/24
 * @desc 获取当前时间并格式化
 */
public class MyTimeUtils {
    public static String getSystemTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String timeStr = dateFormat.format(System.currentTimeMillis());

        return timeStr;
    }
}
