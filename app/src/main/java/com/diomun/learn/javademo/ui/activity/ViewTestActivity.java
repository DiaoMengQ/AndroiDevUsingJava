package com.diomun.learn.javademo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.base.BaseActivity;

import butterknife.BindView;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/2/1
 * @desc
 */
public class ViewTestActivity extends BaseActivity {
    @BindView(R.id.tv_httpTest)
    TextView tvHttpTest;
    private Intent intentFromOthers;

    @Override
    public int initLayout() {
        return R.layout.activity_viewtest;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        Intent intentFromOthers = getIntent();
        Bundle bundleExtra = intentFromOthers.getBundleExtra(getString(R.string.bundleKey_httpTest));
        String dataStr = bundleExtra != null ? bundleExtra.getString(getString(R.string.bundleDataKey_httpTest)) : "null";
        Log.d(TAG, "initData: " + dataStr);

        tvHttpTest.setText(dataStr);
    }
}
