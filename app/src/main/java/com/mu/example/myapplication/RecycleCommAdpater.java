package com.mu.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27 0027.
 */

public abstract class RecycleCommAdpater<T> extends RecyclerView.Adapter<RecycleCommHolder> {

    //数据
    protected List<T> mDatas;
    protected Context mContext;
    protected int mResId;
    protected LayoutInflater mInflater;

    public RecycleCommAdpater(Context context, List<T> data, int resId) {
        this.mContext = context;
        this.mDatas = data;
        this.mResId = resId;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecycleCommHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecycleCommHolder(mInflater.inflate(mResId,parent,false),mContext);
    }


    @Override
    public void onBindViewHolder(RecycleCommHolder holder, int position) {
        convert(holder,mDatas.get(position));
    }

    /**
     * 让子类实现填充view的方法
     * @param holder
     */
    protected abstract void convert(RecycleCommHolder holder, T t);



    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
