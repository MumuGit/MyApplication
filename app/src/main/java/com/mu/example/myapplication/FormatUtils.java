package com.mu.example.myapplication;

import android.text.TextUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/6/26 0026.
 */

public class FormatUtils {

    /**
     * 格式化账户号码，四个一组
     *
     * @param text
     * @return
     */
    public static String formatAccount(String text) {
        String res = "";
        if (TextUtils.isEmpty(text) || text.length() <= 4) {
            res = text;
        } else {
            //截取账户
            res = formatAccountSelf(text);
        }
        return res;
    }

    //格式化账户
    private static String formatAccountSelf(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (i % 4 == 0 && i != 0) {
                sb.append("-");
            }
            sb.append(c);
        }

        return sb.toString();
    }

    //格式化带区号的座机
    private static String formatMobileSelf(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (i == 4) {
                sb.append("-");
            }
            sb.append(c);
        }

        return sb.toString();
    }

    /**
     * 格式化手机号码
     *
     * @param text
     * @return
     */
    public static String formatPhone(String text) {
        String res = text;
        if (TextUtils.isEmpty(text) || text.length() <= 4) {
            res = text;
        } else {
            java.text.DecimalFormat df = new java.text.DecimalFormat("###,####,####");
            res = df.format(Long.parseLong(text)).replaceAll(",", "-");
        }
        return res;
    }

    public final static String[] zoonNums = {"010", "020", "021", "023", "024", "025", "027", "029", "022", "028"};

    /**
     * 格式化电话号码
     *
     * @param text
     * @return
     */
    public static String formatMobile(String text) {
        String res = "";
        String threeNum = text.substring(0, 3);
        List<String> zoomList = Arrays.asList(zoonNums);
        if (text.startsWith("0")) {
            //普通座机
            if (zoomList.contains(threeNum)) {
                //三位区号
                res = threeNum + "-" + text.substring(3, text.length());
            } else {
                //四位区号
                String zoonNum = text.substring(0, 4);
                String mobile = text.substring(4, text.length());
                res = zoonNum + "-" + mobile;
            }
        } else {
            //免费座机
            java.text.DecimalFormat df = new java.text.DecimalFormat("###,####,###");
            res = df.format(Long.parseLong(text)).replaceAll(",", "-");
        }
        return res;
    }
}
