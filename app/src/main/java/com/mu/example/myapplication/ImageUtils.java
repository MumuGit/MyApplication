package com.mu.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


/**
 * 加载Image工具类
 * Created by SLX on 2017/6/13 0013.
 */

public class ImageUtils {

    //------------对外部暴露的方法---------

    /**
     * 从网络默认加载图片（包括内存缓存、硬盘缓存）
     *
     * @param targeImage
     */
    public static void displayImageFromUrl(Context context, String url, ImageView targeImage) {
        _displayImage(context, url, targeImage, false, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
    }

    public static void displayImageFromUrlWithListener(Context context, String url, ImageView targeImage, OnLoadFinishListener listener) {
        _displayImageWithListener(context, url, targeImage, false, listener);
    }

    /**
     * 从网络默认加载图片并且不使用内存缓存
     *
     * @param targeImage
     */
    public static void displayImageFromUrlWithNoMemoryCache(Context context, String url, ImageView targeImage) {
        _displayImage(context, url, targeImage, true, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
    }

    /**
     * 展示GIF
     *
     * @param targeImage
     */
    public static void displayGif(Context context, String url, ImageView targeImage) {
        _displayGif(context, url, targeImage, false, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
    }

    /**
     * 从网络默认加载图片并指定大小
     *
     * @param targeImage
     */
    public static void displayImageFromUrlWithSize(Context context, String url, ImageView targeImage, int width, int height) {
        _displayImageWithSize(context, url, targeImage, false, width, height, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
    }

    //-------内部私有方法------------
    private static void _displayImage(Context cotext, String url, ImageView targeImage, boolean isSkipMemoryCache, int errorImgId, int loadingImgId) {
        Glide.with(cotext)
                .load(url)
                .error(errorImgId)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .skipMemoryCache(isSkipMemoryCache)//跳过内存缓存
                .placeholder(loadingImgId)
                .into(targeImage);
    }

    //带加载成功监听
    private static void _displayImageWithListener(Context cotext, String url, final ImageView targeImage, boolean isSkipMemoryCache, final OnLoadFinishListener listener) {
        Glide.with(cotext)
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        targeImage.setImageBitmap(resource);
                        listener.finsish(resource);
                    }
                });
    }

    public interface OnLoadFinishListener {
        void finsish(Bitmap bm);
    }

    private static void _displayGif(Context cotext, String url, ImageView targeImage, boolean isSkipMemoryCache, int errorImgId, int loadingImgId) {
        Glide.with(cotext)
                .load(url)
                .asGif()
                .placeholder(loadingImgId)
                .error(errorImgId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(isSkipMemoryCache)//跳过内存缓存
                .into(targeImage);
    }

    private static void _displayImageWithSize(Context cotext, String url, ImageView targeImage, boolean isSkipMemoryCache, int width, int height, int errorImgId, int loadingImgId) {
        Glide.with(cotext)
                .load(url)
                .error(errorImgId)
                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .skipMemoryCache(isSkipMemoryCache)//跳过内存缓存
                .placeholder(loadingImgId)
                .into(targeImage);
    }

    public static void displayImageInSelectPhoto(Context cotext, File file, ImageView targeImage, int width, int height) {
        Glide.with(cotext)
                .load(file)
                .placeholder(R.mipmap.mis_default_error)
                .override(width, height)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(targeImage);


    }

    //使用BitmapFactory.Options的inSampleSize参数来缩放
    public static Bitmap resizeImage(Bitmap bitmap, int w, int h) {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);
        return resizedBitmap;
    }

    public static Bitmap resizeImageInRatio(Bitmap bitmap, int maxSide) {
        int width = bitmap.getWidth();
        int high = bitmap.getHeight();
        if (width > maxSide || high > maxSide) {
            int max = Math.max(width, high);
            int wc = (int) (((float) width) / max * maxSide);
            int hc = (int) (((float) high) / max * maxSide);

            bitmap = ImageUtils.resizeImage(bitmap, wc, hc);
        }
        return bitmap;
    }

    public static boolean isJPG(String path) {
        File file = new File(path);

        return file.getName().endsWith("jpg");
    }

    public static byte[] transformBitmapToByte(Bitmap bm) {
        byte[] bytes = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
        bytes = out.toByteArray();
        if (bytes == null || bytes.length <= 0) {
//            ToastUtil.toast("transformBitmapToByte,Fail to transform data");
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

}


