package com.zhuyx.mytraining.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 万能适配器
 * Created by zhuyingxin on 2016/6/6.
 * email : rixtdqqq_2015@163.com
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    public static final String TAG = CommonAdapter.class.getName();
    private List<T> mData;
    private LayoutInflater mInflater;
    private int mItemLayoutId;
    private Context mContext;

    public CommonAdapter(Context context, List<T> data, int itemLayoutId) {
        mData = data;
        mContext=context;
        mItemLayoutId = itemLayoutId;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == holder) {
            holder = ViewHolder.get(mContext,position,convertView,parent,mItemLayoutId);
        }
        bindView(holder, getItem(position), position);
        return holder.getConvertView();
    }

    public abstract void bindView(ViewHolder holder, T t, int position);
}
