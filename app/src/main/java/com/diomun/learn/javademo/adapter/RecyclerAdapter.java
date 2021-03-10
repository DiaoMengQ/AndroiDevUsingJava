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

import java.util.List;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/5
 * @desc 测试文字数据 RecycleView 适配器
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> implements View.OnClickListener {
    private Context context;
    private List<String> list;
    private View view;
    private OnChildClickListener listener;
    private String TAG = "RecyclerAdapter";
    private RecyclerView recyclerView;

    public void setOnChildClickListener(OnChildClickListener listener) {
        this.listener = listener;
    }

    public RecyclerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public void remove(int position){
        list.remove(position);
        // notifyDataSetChanged(); // 提醒列表刷新,直接移除,无动画效果
        notifyItemRemoved(position); // 移除时会有动画效果提醒

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_recycler_test, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    /** 当这个 adapter 被连接到一个 recyclerView 时会被调用 */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Log.d(TAG, "onAttachedToRecyclerView: ");
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemText.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (recyclerView!=null && listener!=null) {
            int position = recyclerView.getChildAdapterPosition(v);
            listener.onChildClick(recyclerView,v,position,list.get(position));
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView itemText;

        MyViewHolder(View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.item_text);
        }
    }

    private interface OnChildClickListener{
        /**
         * @param parent RecyclerView 布局
         * @param view 点击时的视图
         * @param position item 位置
         * @param data item 数据
         */
        void onChildClick(RecyclerView parent, View view, int position, String data);
    }
}
