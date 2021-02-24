package com.diomun.learn.javademo.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.base.BaseActivity;
import com.diomun.learn.javademo.service.BackService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/1/21
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.btn_toListView)
    Button btnToListView;
    @BindView(R.id.btn_database)
    Button btnDatabase;
    @BindView(R.id.btn_startBackService)
    Button btnStartBackService;

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.btn_toListView, R.id.btn_database, R.id.btn_startBackService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_toListView:
                Toast.makeText(mContext, "點擊btn_toListView", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_database:
                Toast.makeText(mContext, "数据库管理页", Toast.LENGTH_SHORT).show();
                Intent mIntent = new Intent(this, DBManagerActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_startBackService:
                Intent intent2startService = new Intent(this, BackService.class);
                startService(intent2startService);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}

