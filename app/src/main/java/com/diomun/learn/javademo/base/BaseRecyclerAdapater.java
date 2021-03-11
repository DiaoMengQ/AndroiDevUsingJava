package com.diomun.learn.javademo.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/11
 * @desc
 */
public abstract class BaseRecyclerAdapater<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private Context mContext;
    private List<T> mList;
    private RecyclerView mRecyclerView;
    private OnItemClickListener mOnItemClickListener;

    public BaseRecyclerAdapater(Context mContext, List<T> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(getContentView(viewType), parent, false);
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这个地方一定要判断 Listener，否则当没有注册点击事件的时候，点击Item的时候报错
                if (v != null && mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mRecyclerView, v, position, mList.get(position));
                }
            }
        });
        // covert(holder, mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    protected abstract int getContentView(int viewType);

    protected abstract void covert(BaseViewHolder holder, T data, int position);

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    /**
     * 点击单个 item 时的接口
     */
    public interface OnItemClickListener {
        /**
         * 为了使点击发生时可以直接获取数据，因此除了view之外多加几个参数
         *
         * @param parent   RecyclerView 布局
         * @param view     点击时的视图
         * @param position item 位置
         * @param data     item 数据
         */
        void onItemClick(RecyclerView parent, View view, int position, Object data);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mRecyclerView = null;
    }
}
