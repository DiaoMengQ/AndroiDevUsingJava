package com.diomun.learn.javademo.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.diomun.learn.javademo.R;

import static android.os.SystemClock.sleep;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/1/2
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final int SPLASH_DISPLAY_LENGHT = 1000;

        // TODO: 不能显示创建线程，使用Android的线程池
        new Thread(() -> {
            //耗时任务，比如加载网络数据
            runOnUiThread(() -> {
                // 这里可以睡几秒钟，如果要放广告的话
                sleep(SPLASH_DISPLAY_LENGHT);
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            });
        }).start();
    }
}
