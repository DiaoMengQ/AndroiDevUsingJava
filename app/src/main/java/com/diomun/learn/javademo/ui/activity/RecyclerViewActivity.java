package com.diomun.learn.javademo.ui.activity;

import androidx.recyclerview.widget.RecyclerView;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.adapter.Adapter4RecyclerView;
import com.diomun.learn.javademo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/4
 * @desc
 */
public class RecyclerViewActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private Adapter4RecyclerView adapter4RecyclerView;

    @Override
    public int initLayout() {
        return R.layout.activity_recyclerview;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.format(Locale.CHINA, "第%03d条数据", i));
        }
        adapter4RecyclerView = new Adapter4RecyclerView(this, list);
        recycler.setAdapter(adapter4RecyclerView);
    }

}
