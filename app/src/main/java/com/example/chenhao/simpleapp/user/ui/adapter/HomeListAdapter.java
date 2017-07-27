package com.example.chenhao.simpleapp.user.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.user.bean.HomeMenuItem;

import java.util.List;

/**
 * The type Home list adapter.
 * 用户主界面的ListView 的适配器
 */
public class HomeListAdapter extends BaseAdapter {
    /**
     * The Menu items.
     */
    List<HomeMenuItem> menuItems;
    /**
     * The M layout inflater.
     */
    LayoutInflater mLayoutInflater;

    /**
     * Instantiates a new Home list adapter.
     *
     * @param context the context
     * @param items   the items
     */
    public HomeListAdapter(Context context, List<HomeMenuItem> items) {
        mLayoutInflater = LayoutInflater.from(context);
        menuItems = items;
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public HomeMenuItem getItem(int i) {
        return menuItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = mLayoutInflater.inflate(R.layout.item_line_3, null);
        TextView t1 = (TextView) convertView.findViewById(R.id.item_t1);
        TextView t2 = (TextView) convertView.findViewById(R.id.item_t2);
        HomeMenuItem item = getItem(position);
        t1.setText(item.getName());
        t2.setText(item.getContent());
        t2.setTextColor(item.getContentColor());
        if (item.getContentSize() != -1)
            t2.setTextSize(item.getContentSize());

        return convertView;
    }


}