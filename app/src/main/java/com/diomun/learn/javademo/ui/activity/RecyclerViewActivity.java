package com.diomun.learn.javademo.ui.activity;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.adapter.RecyclerAdapter;
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
public class RecyclerViewActivity extends BaseActivity implements RecyclerAdapter.OnChildClickListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private RecyclerAdapter recyclerAdapter;
    private List<String> list;

    @Override
    public int initLayout() {
        return R.layout.activity_recyclerview;
    }

    @Override
    public void initView() {
        recyclerAdapter = new RecyclerAdapter(this, list);

        // 线性布局
        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);

        // 网格布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 3;
                }
                if (position == 1) {
                    return 2;
                }
                return 1;
            }
        });

        // 瀑布流!
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        recycler.setLayoutManager(staggeredGridLayoutManager);
        recycler.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnChildClickListener(this);
    }

    @Override
    public void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String str = i % 3 != 0 ? "" : "--------------------------------";
            list.add(String.format(Locale.CHINA, "%s第%03d条数据%s", str, i, str));
        }

    }

    @Override
    public void onChildClick(RecyclerView parent, View view, int position, String data) {
        Toast.makeText(mContext, data, Toast.LENGTH_SHORT).show();
        // recyclerAdapter.remove(position);
    }
}
