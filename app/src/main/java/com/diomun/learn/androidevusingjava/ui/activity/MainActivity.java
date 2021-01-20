package com.diomun.learn.androidevusingjava.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.diomun.learn.androidevusingjava.R;
import com.diomun.learn.androidevusingjava.ui.base.BaseActivity;

/**
 * @author DiaoMengQ
 */
public class MainActivity extends BaseActivity implements View.OnClickListener{
    /* 两次点击按钮之间的点击间隔不能少于1000毫秒 */
    private static final int MIN_CLICK_DELAY_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, String.format("%s onCreate", TAG));
        mContext = this;
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }


    @Override
    public void initView() {
        Button listBtn = findViewById(R.id.btn_toListView);
        Button testBtn = findViewById(R.id.btn_test);

        Toast.makeText(this, "initVIEW", Toast.LENGTH_SHORT).show();
        testBtn.setText("just4Test");
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_toListView:
                Toast.makeText(mContext, "點擊btn_toListView", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_test:
                Toast.makeText(mContext, "點擊btn_test", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}
