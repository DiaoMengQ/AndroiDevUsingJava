package com.diomun.learn.javademo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.model.Music.Song;
import com.diomun.learn.javademo.ui.customview.RoundImageView;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        return songData.size();
    }

    @Override
    public Song getItem(int position) {
        return songData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder = null;
        if (convertView != null) {
            mHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_song, parent, false);
            mHolder = new ViewHolder(convertView);
            convertView.setTag(mHolder);
        }
        // mHolder.imgVSongAlbum.setImageDrawable(songData.get(position).getsAlbumUrl());
        // mHolder.imgVSongAlbum.setImageResource(R.mipmap.heiyu);
        // mHolder.tvSongSinger.setText(songData.get(position).getsSinger());
        // mHolder.tvSongName.setText(songData.get(position).getsName());

        return convertView;
    }

    /**
     * 添加一个元素
     */
    public void add(Song data) {
        if (songData == null) {
            songData = new LinkedList<>();
        }
        songData.add(data);
        notifyDataSetChanged();
    }

    /**
     * 往特定位置，添加一个元素
     */
    public void add(int position, Song data) {
        if (songData == null) {
            songData = new LinkedList<>();
        }
        songData.add(position, data);
        notifyDataSetChanged();
    }

    /**
     * 按数据内容删除
     */
    public void remove(Song data) {
        if (songData != null) {
            songData.remove(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 按数据位置删除
     */
    public void remove(int position) {
        if (songData != null) {
            songData.remove(position);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        if (songData != null) {
            songData.clear();
        }
        notifyDataSetChanged();
    }

    static
    class ViewHolder {
        @BindView(R.id.imgV_songAlbum)
        RoundImageView imgVSongAlbum;
        @BindView(R.id.tv_songName)
        TextView tvSongName;
        @BindView(R.id.tv_songSinger)
        TextView tvSongSinger;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
