package com.diomun.learn.javademo.adapter;

import android.content.Context;
import android.widget.TextView;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.base.BaseRecyclerAdapater;
import com.diomun.learn.javademo.base.BaseViewHolder;
import com.diomun.learn.javademo.model.Music.Info;

import java.util.List;

import butterknife.BindView;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/11
 * @desc 搜索页 Adapter
 */
public class SongRecyclerAdapterNew extends BaseRecyclerAdapater<Info> {
    @BindView(R.id.tv_songName)
    TextView tvSongName;
    @BindView(R.id.tv_songSinger)
    TextView tvSongSinger;

    public SongRecyclerAdapterNew(Context mContext, List<Info> mList) {
        super(mContext, mList);
    }

    @Override
    protected int getContentView(int viewLayout) {
        return R.layout.item_list_song;
    }

    @Override
    protected void convertView(BaseViewHolder holder, List<Info> data, int position) {
        tvSongName.setText(data.get(position).getSongname());
        tvSongSinger.setText(data.get(position).getSingername());
    }
}
