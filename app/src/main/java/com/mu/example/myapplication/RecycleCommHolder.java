package com.mu.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/6/27 0027.
 * recycle公共holder
 */

public class RecycleCommHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private Context mContext;

    public RecycleCommHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        mViews = new SparseArray<>();
    }

    /**
     * 根据ID查找view
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View childView = mViews.get(viewId);
        if (childView == null) {
            childView = itemView.findViewById(viewId);
            mViews.put(viewId, childView);
        }
        return (T) childView;
    }

    public RecycleCommHolder setText(int resId, String text) {
        TextView tv = getView(resId);
        tv.setText(text);
        return this;
    }

    public RecycleCommHolder setNetImage(int resId, String imageUrl) {
        ImageView iv = getView(resId);
        ImageUtils.displayImageFromUrl(mContext, imageUrl, iv);
        return this;
    }

    public RecycleCommHolder setNetImage(ImageView imageView, String imageUrl) {
        ImageUtils.displayImageFromUrl(mContext, imageUrl, imageView);
        return this;
    }
}
