package com.diomun.learn.javademo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.model.Song;

import java.util.LinkedList;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/6
 * @desc 歌曲展示 ListView 适配器
 */
public class SongAdapter extends BaseAdapter {
    private LinkedList<Song> songData;
    private Context mContext;

    public SongAdapter(LinkedList<Song> songData, Context context) {
        this.songData = songData;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater.from(mContext).inflate(R.layout.item_list_song, parent,false);
        return null;
    }


}
