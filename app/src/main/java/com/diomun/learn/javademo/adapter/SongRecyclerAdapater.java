package com.diomun.learn.javademo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.model.Music.Info;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/10
 * @desc
 */
public class SongRecyclerAdapater extends RecyclerView.Adapter<SongRecyclerAdapater.searchListHolder> {
    private Context context;
    private List<Info> songInfoList;
    private View view;
    private String TAG = "SongRecyclerAdapater";
    private RecyclerView songRecyclerView;
    private OnChildClickListener onChildClickListener;

    public void setOnChildClickListener(OnChildClickListener listener) {
        this.onChildClickListener = listener;
    }

    public SongRecyclerAdapater(Context context, List<Info> songInfoList) {
        this.context = context;
        this.songInfoList = songInfoList;
    }

    static class searchListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_songName)
        TextView songName;
        @BindView(R.id.tv_songSinger)
        TextView singerName;

        searchListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public searchListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_recycler_searchlist, parent, false);
        // view.setOnClickListener(this);
        return new searchListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searchListHolder holder, int position) {
        holder.songName.setText(songInfoList.get(position).getSongname());
        holder.singerName.setText(songInfoList.get(position).getSingername());
    }

    @Override
    public int getItemCount() {
        return songInfoList.size();
    }

    /**
     * 点击单个 item 时的接口
     */
    public interface OnChildClickListener {
        /**
         * @param parent   RecyclerView 布局
         * @param view     点击时的视图
         * @param position item 位置
         * @param data     item 数据
         */
        void onChildClick(RecyclerView parent, View view, int position, Info data);
    }

    /**
     * 对整个视图View的点击相应
     */
    @OnClick
    public void onViewClicked(View view) {
        if (songRecyclerView != null && onChildClickListener != null) {
            int position = songRecyclerView.getChildAdapterPosition(view);
            onChildClickListener.onChildClick(songRecyclerView, view, position, songInfoList.get(position));
        }
        else {
            Log.d(TAG, "onViewClicked: error");
        }
    }

    // @Override
    // public void onClick(View v) {
    //     if (songRecyclerView != null && listener != null) {
    //         int position = songRecyclerView.getChildAdapterPosition(v);
    //         listener.onChildClick(songRecyclerView, v, position, songInfoList.get(position));
    //     }
    // }

    /**
     * 当这个 adapter 被连接到一个 recyclerView 时会被调用
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Log.d(TAG, "onAttachedToRecyclerView: adapter 连接 recyclerView");
        this.songRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.songRecyclerView = null;
    }
}
