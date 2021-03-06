package com.diomun.learn.javademo.ui.activity;

import android.util.Log;
import android.widget.ListView;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.adapter.SongAdapter;
import com.diomun.learn.javademo.base.BaseActivity;
import com.diomun.learn.javademo.model.Song;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/5
 * @desc
 */
public class ListViewActivity extends BaseActivity {
    @BindView(R.id.lv_song)
    ListView lvSong;
    private List<Song> songList = null;
    private SongAdapter mAdapter = null;

    @Override
    public int initLayout() {
        return R.layout.activity_list;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

        mAdapter = new SongAdapter((LinkedList<Song>) songList, mContext);
        Log.d(TAG, "initView: " + songList.get(0).getsName());
        lvSong.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        songList = new LinkedList<Song>();
        for (int i = 0; i < 20; i++) {
            String str = String.valueOf(i);
            songList.add(new Song(str, str, str));
        }
    }
}
