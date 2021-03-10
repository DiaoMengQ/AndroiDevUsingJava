package com.diomun.learn.javademo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.model.Music.Info;

import java.util.List;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/10
 * @desc
 */
public class SongRecyclerAdapater extends RecyclerView.Adapter<SongRecyclerAdapater.MyViewHolder> implements View.OnClickListener {
    private Context context;
    private List<Info> songInfoList;
    private View view;
    private String TAG = "RecyclerAdapter";
    private RecyclerView songRecyclerView;
    private OnChildClickListener listener;


    @Override
    public void onClick(View v) {
        if (songRecyclerView != null && listener != null) {
            int position = songRecyclerView.getChildAdapterPosition(v);
            listener.onChildClick(songRecyclerView, v, position, songInfoList.get(position));
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView songName;
        private final TextView singerName;

        MyViewHolder(View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.tv_songName);
            singerName = itemView.findViewById(R.id.tv_songSinger);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_recycler_searchlist, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private interface OnChildClickListener{
        /**
         * @param parent RecyclerView 布局
         * @param view 点击时的视图
         * @param position item 位置
         * @param data item 数据
         */
        void onChildClick(RecyclerView parent, View view, int position, Info data);
    }
}
