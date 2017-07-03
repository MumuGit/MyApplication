package com.mu.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by slx on 2017/6/23 0023.
 * 商家详情场地适配器
 */

public class BizProductApapter extends RecycleCommAdpater<String> {

    private List<String> trueUrls = null;

    public BizProductApapter(Context context, List<String> data, int resId) {
        super(context, data, resId);
        trueUrls = new ArrayList<>();
        for(String url:data){
            trueUrls.add("ss"+url);
        }
    }


    @Override
    protected void convert(RecycleCommHolder holder, String s) {

        ImageView ivShowImage = holder.getView(R.id.iv_item_single_iamge);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivShowImage.getLayoutParams();
        int screenSize = ScreenUtils.getScreenSize(mContext).x;
        int imgSize = (screenSize - DensityUtil.dip2px(mContext, 8)) / 4;
        params.width = imgSize;
        params.height = imgSize;
        ivShowImage.setLayoutParams(params);
        holder.setNetImage(ivShowImage,"ss" + s);

        ivShowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagPagerUtil imagPagerUtil = new ImagPagerUtil((Activity) mContext, trueUrls);
                imagPagerUtil.show();
            }
        });
    }
}
