package com.diomun.learn.javademo.ui.activity;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/5
 * @desc
 */
public class ListViewActivity extends BaseActivity {
    @Override
    public int initLayout() {
        return R.layout.activity_list;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {

    }
}
