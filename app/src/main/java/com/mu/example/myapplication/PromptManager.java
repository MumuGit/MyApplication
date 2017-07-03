package com.mu.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;


/**
 * Created by slx on 2017/4/1.
 */

public class PromptManager {

    public static AlertDialog getmDialog() {
        return mDialog;
    }

    private static AlertDialog mDialog;

    /**
     * 显示加载进度条
     */
    public static void showLoadingDialog(Context context) {
        mDialog = new AlertDialog.Builder(context).create();
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

        mDialog.setContentView(R.layout.view_loading_process_dialog);
    }






    /**
     * 关闭mDialog
     */
    public static void closeLoadingDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

}
