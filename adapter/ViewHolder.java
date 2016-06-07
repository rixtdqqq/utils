package com.zhuyx.mytraining.util;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
            return (ViewHolder) convertView.getTag();
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
}
