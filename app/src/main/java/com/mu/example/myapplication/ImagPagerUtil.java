package com.mu.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * 查看大图
 */


public class ImagPagerUtil {
    private List<String> mPicList;
    private Activity mActivity;
    private Dialog dialog;
    private LazyViewPager mViewPager;
    private int screenWidth;
    private TextView tv_img_current_index;
    private TextView tv_img_count;

    public ImagPagerUtil(Activity activity, List<String> mPicList) {
        this.mPicList = mPicList;
        this.mActivity = activity;
        init();
    }

    public ImagPagerUtil(Activity activity, String[] picarr) {
        mPicList = new ArrayList<>();
        for (int i = 0; i < picarr.length; i++) {
            mPicList.add(picarr[i]);
        }
        this.mActivity = activity;
       init();
    }

    public void show() {

        dialog.show();
        Log.i("muqi"," dialog.show()");
    }

    private void init() {
        dialog = new Dialog(mActivity, R.style.fullDialog);
        RelativeLayout contentView = (RelativeLayout) View.inflate(mActivity, R.layout.view_dialogpager_img, null);
        mViewPager =getView (contentView, R.id.view_pager);
        tv_img_current_index = getView(contentView, R.id.tv_img_current_index);
        tv_img_count = getView(contentView, R.id.tv_img_count);
        dialog.setContentView(contentView);

        tv_img_count.setText(mPicList.size() + "");
        tv_img_current_index.setText("1");

        int size = mPicList.size();
        ArrayList<ImageView> imageViews = new ArrayList<>();
        ZoomImageView imageView = new ZoomImageView(mActivity);
        //imageView.measure(0, 0);//?
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        screenWidth = display.getWidth();
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(screenWidth, display.getHeight());
        imageView.setLayoutParams(marginLayoutParams);
        imageView.setOnClickListener(new View.OnClickListener() {//如果不需要点击图片关闭的需求，可以去掉这个点击事件
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Log.i("muqi"," dialog.dismiss()");

            }
        });
        for (int i = 0; i < size; i++) {
            imageViews.add(imageView);
        }
        initViewPager(imageViews);
    }

    private void initViewPager(ArrayList<ImageView> list) {
        mViewPager.setOnPageChangeListener(new LazyViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tv_img_current_index.setText("" + (position + 1));
            }
        });

        MyImagPagerAdapter myImagPagerAdapter = new MyImagPagerAdapter(list);
        mViewPager.setAdapter(myImagPagerAdapter);
    }

    class MyImagPagerAdapter extends PagerAdapter {
        ArrayList<ImageView> mList;

        public MyImagPagerAdapter(ArrayList<ImageView> mList) {
            this.mList = mList;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = mList.get(position);
            showPic(imageView, mPicList.get(position));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }

        @Override
        public int getCount() {
            if (null == mList || mList.size() <= 0) {
                return 0;
            }
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }


    private void showPic(final ImageView imageView, String url) {


       PromptManager.showLoadingDialog(mActivity);
        ImageUtils.displayImageFromUrlWithListener(mActivity, url, imageView, new ImageUtils.OnLoadFinishListener() {

            @Override
            public void finsish(Bitmap bm) {
                //imageView.setImageBitmap(bm);
               PromptManager.closeLoadingDialog();
            }
        });
        Log.i("muqi"," dialog.show()2");

        //dialog.show();
    }


    public static final <E extends View> E getView(View parent, int id) {
        try {
            View v=parent.findViewById(id);
            return (E)v;
        } catch (ClassCastException ex) {
            throw ex;
        }
    }
}
