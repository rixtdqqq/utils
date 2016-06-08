package com.zhuyx.mytraining.util;

import android.content.Context;
import android.os.Build;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zhuyingxin on 2016/6/6.
 * email : rixtdqqq_2015@163.com
 */
public class ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private int mPosition;
    private Context mContext;
    private int mItemLayoutId;

    public ViewHolder(Context context, int position, ViewGroup parent, int itemLayoutId) {
        mViews = new SparseArray();
        mPosition = position;
        mContext = context;
        mItemLayoutId = itemLayoutId;
        mConvertView = LayoutInflater.from(context).inflate(itemLayoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, int position, View convertView, ViewGroup parent, int itemLayoutId) {
        if (null == convertView) {
            return new ViewHolder(context, position, parent, itemLayoutId);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    /**
     * 通过view的id获取相应的控件，因为不知道控件的类型，则使用泛型
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (null == view) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }


    public View getConvertView() {
        return mConvertView;
    }

    public int getPosition() {
        return mPosition;
    }

    public ViewHolder setText(int viewId, String text) {
        TextView textView = (TextView) mViews.get(viewId);
        textView.setText(text);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView imageView = (ImageView) mViews.get(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    /**
     * 【1】3.0以下版本,不支持setalpha, 且不支持在xml文件中设置android:alpha = "0.5"这种方法(如果xml中设置则一律是不透明的);
     * 【2】3.0以下版本, 在xml中配置透明度,只能通过ARGB设置.,如background="#0123"
     * 【3】3.0以下版本通过以下2种方式：
     * a：view.getBackground().setAlpha(255)这种方法设置;
     * b: 通过这种方法设置时,xml中的view的background只能设置RGB,不能是ARGB的格式
     */
    public ViewHolder setAlpha(int viewId, float alpha) {
        View view = mViews.get(viewId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            view.setAlpha(alpha);
        } else {

        }
        return this;
    }

    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = mViews.get(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = mViews.get(viewId);
        view.setOnTouchListener(listener);
        return this;
    }
}
