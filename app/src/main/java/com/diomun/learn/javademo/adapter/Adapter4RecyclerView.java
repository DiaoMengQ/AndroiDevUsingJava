package com.diomun.learn.javademo.adapter;

import android.content.Context;
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
public class Adapter4RecyclerView extends RecyclerView.Adapter<Adapter4RecyclerView.MyViewHolder> {
    private Context context;
    private List<String> list;
    private View view;

    public Adapter4RecyclerView(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_recycler_test, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemText.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView itemText;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.item_text);
        }
    }
}
