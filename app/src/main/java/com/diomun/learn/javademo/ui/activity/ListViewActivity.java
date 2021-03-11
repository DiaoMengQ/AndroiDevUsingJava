package com.diomun.learn.javademo.ui.activity;

import android.widget.ListView;
import android.widget.Toast;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.base.BaseActivity;
import com.diomun.learn.javademo.base.MyListAdapter;
import com.diomun.learn.javademo.model.TestData;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/5
 * @desc 简单 ListView 的实现
 */
public class ListViewActivity extends BaseActivity {
    @BindView(R.id.lv_song)
    ListView lvSong;
    private List<TestData> dataList = null;
    private MyListAdapter<TestData> mAdapter = null;

    @Override
    public int initLayout() {
        return R.layout.activity_list;
    }

    @Override
    public void initView() {
        // SongListAdapter mAdapter = new SongAdapter((LinkedList<TestData>) dataList, mContext); // 原始Adapter方法
        lvSong.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        dataList = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            String str = String.valueOf(i);
            dataList.add(new TestData(str, str));
        }

        // 使用自定义 MyBaseAdapter
        mAdapter = new MyListAdapter<TestData>((LinkedList<TestData>) dataList, R.layout.item_list_testdata) {
            @Override
            protected void bindView(ViewHolder holder, TestData obj) {
                holder.setImageResource(R.id.imgV_songAlbum, R.mipmap.heiyu);
                holder.setText(R.id.tv_songName, obj.getTitle());
                holder.setText(R.id.tv_songSinger, obj.getDetail());
                holder.setOnClickListener(R.id.layout_item,
                        v -> Toast.makeText(mContext,
                                "Title: " + obj.getTitle() + "\nDetail: " + obj.getDetail(), Toast.LENGTH_SHORT).show());
            }
        };
    }
}
