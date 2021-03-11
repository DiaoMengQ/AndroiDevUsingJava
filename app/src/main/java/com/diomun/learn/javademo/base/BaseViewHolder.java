package com.diomun.learn.javademo.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/11
 * @desc RecyclerView 的基础 ViewHolder 类
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    View mView;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mView = itemView;
    }

    public View getView() {
        return mView;
    }
}
