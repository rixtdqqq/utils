package com.zhuyx.mytraining.util;

import android.content.Context;
import android.util.TypedValue;

import java.text.DecimalFormat;

/**
 * 单位转换类
 * <p/>
 * Created by zhuyingxin on 2016/6/7.
 * email : rixtdqqq_2015@163.com
 */
public class DensityUtils {

    private DensityUtils() {

        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static int dp2px(Context context, int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    public static int px2dp(Context context, int pxVal) {
        return (int)(0.5f + pxVal / context.getResources().getDisplayMetrics().density);
    }

    public static int px2sp(Context context, int pxVal) {
        return (int)(0.5f + pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static String formatFileSize(long size) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeStr = "";
        String wrongSize = "0B";
        if (size <= 0) {
            return wrongSize;
        } else if (size < 1024) {
            fileSizeStr = df.format(size) + "B";
        } else if (size < 1048576) {
            fileSizeStr = df.format(size / 1024) + "KB";
        } else if (size < 1073741824) {
            fileSizeStr = df.format(size / 1048576) + "MB";
        } else {
            fileSizeStr = df.format(size / 1073741824) + "GB";
        }
        return fileSizeStr;
    }
}
