package com.diomun.learn.javademo.ui.activity;

import android.widget.ListView;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.base.BaseActivity;
import com.diomun.learn.javademo.base.MyListAdapter;
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
    private MyListAdapter<Song> mAdapter = null;

    @Override
    public int initLayout() {
        return R.layout.activity_list;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

        // 原始Adapter方法
        // SongAdapter mAdapter = new SongAdapter((LinkedList<Song>) songList, mContext);
        // Log.d(TAG, "initView: " + songList.get(0).getsName());

        lvSong.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        songList = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            String str = String.valueOf(i);
            songList.add(new Song(str, str, str));
        }

        // 使用自定义 MyBaseAdapter
        mAdapter = new MyListAdapter<Song>((LinkedList<Song>) songList, R.layout.item_list_song) {

            // 完成原本自定义Adapter类中的功能(SongAdapter)
            @Override
            protected void bindView(ViewHolder holder, Song obj) {
                holder.setImageResource(R.id.imgV_songAlbum, R.mipmap.heiyu);
                holder.setText(R.id.tv_songName, obj.getsName());
                holder.setText(R.id.tv_songSinger, obj.getsSinger());
            }
        };

    }
}
