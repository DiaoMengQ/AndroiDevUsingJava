package com.diomun.learn.javademo.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.base.BaseActivity;
import com.diomun.learn.javademo.base.OnMultiClickListener;

/**
 * @author DIOMUN
 */
public class MainActivity extends BaseActivity {
    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    OnMultiClickListener multiClickListener = new OnMultiClickListener() {
        @Override
        public void onMultiClick(View v) {
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
    };

    @Override
    public void initView() {
        Button listBtn = findViewById(R.id.btn_toListView);
        Button testBtn = findViewById(R.id.btn_test);

        listBtn.setOnClickListener(multiClickListener);
        testBtn.setOnClickListener(multiClickListener);
    }

    @Override
    public void initData() {
    }
}

