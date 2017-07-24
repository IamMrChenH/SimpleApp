package com.example.chenhao.simpleapp.user.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by chenhao on 2017/3/29.
 */

public abstract class BaseListGridAdapter<T> extends android.widget.BaseAdapter {
    public List<T> mItems;
    public LayoutInflater mInflater;
    public Context mContext;

    public BaseListGridAdapter(Context context, List<T> items) {
        this.mItems = items;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public Drawable getDrawable(int id) {
        return mContext.getResources().getDrawable(id, null);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public T getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    public <T extends View> T findView(View view, int id) {

        return (T) view.findViewById(id);
    }

    public String getTime(String date) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss",
                Locale.CHINA);
        Long aLong = null;
        try {
            aLong = Long.valueOf(date);
            return "时间:" + sdr.format(aLong);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "无";
        }

    }

    public String getTime(long date) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss",
                Locale.CHINA);
        Long aLong = null;
        try {
            return sdr.format(date);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "无";
        }
    }

    public String isEmpty(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return "未填写";
        }
        return msg;
    }
}
