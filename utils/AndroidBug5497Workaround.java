package com.zhuyx.mytraining.util;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * 修复软键盘挡住编辑输入框的问题
 * Created by zhuyingxin on 2016/8/20.
 * email : rixtdqqq_2015@163.com
 */
public class AndroidBug5497Workaround {

    //For more information, see https://code.google.com/p/android/issues/detail?id=5497
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.

    private View mChildOfContent;
    private int usableHeightPrevious;
    private FrameLayout.LayoutParams frameLayoutParams;
    private Activity mActivity;

    /**
     * 在setContentView(R.layout.xxx)之后调用。
     */
    public static void assistActivity(Activity activity) {
        new AndroidBug5497Workaround(activity);
    }

    private AndroidBug5497Workaround(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
            mChildOfContent = content.getChildAt(0);
            mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    possiblyResizeChildOfContent();
                }
            });
            frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
        }
        mActivity = activity;
    }

    private synchronized void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard / 4)) {
                //keyboard probably just became visible
                frameLayoutParams.height = usableHeightNow;
            } else {
                // keyboard probably just became hidden
                frameLayoutParams.height = usableHeightSansKeyboard;
            }
            mChildOfContent.requestLayout();
            usableHeightPrevious = frameLayoutParams.height;
        }
    }

    /**
     * @return 当前可见window的尺寸，有些厂商把statusBar算进去，而有些厂商则并不算进去
     */
    private int computeUsableHeight() {
        Rect rect = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return rect.bottom;
//      return (rect.bottom - rect.top);
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resID = mActivity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resID > 0) {
            result = mActivity.getResources().getDimensionPixelSize(resID);
        }
        return result;
    }
}
