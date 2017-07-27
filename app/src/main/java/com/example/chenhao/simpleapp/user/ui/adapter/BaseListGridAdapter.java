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
 *一个通用的抽象适配器
 * @param <T> the type parameter
 */
public abstract class BaseListGridAdapter<T> extends android.widget.BaseAdapter {
    /**
     * The M items.
     */
    public List<T> mItems;
    /**
     * The M inflater.
     */
    public LayoutInflater mInflater;
    /**
     * The M context.
     */
    public Context mContext;

    /**
     * Instantiates a new Base list grid adapter.
     *
     * @param context the context
     * @param items   the items
     */
    public BaseListGridAdapter(Context context, List<T> items) {
        this.mItems = items;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    /**
     * Gets drawable.
     *
     * @param id the id
     * @return the drawable
     */
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

    /**
     * Find view t.
     *
     * @param <T>  the type parameter
     * @param view the view
     * @param id   the id
     * @return the t
     */
    public <T extends View> T findView(View view, int id) {

        return (T) view.findViewById(id);
    }

    /**
     * Gets time.
     *
     * @param date the date
     * @return the time
     */
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

    /**
     * Gets time.
     *
     * @param date the date
     * @return the time
     */
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

    /**
     * Is empty string.
     *
     * @param msg the msg
     * @return the string
     */
    public String isEmpty(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return "未填写";
        }
        return msg;
    }
}
