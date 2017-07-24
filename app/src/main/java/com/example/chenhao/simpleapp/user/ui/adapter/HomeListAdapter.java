package com.example.chenhao.simpleapp.user.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.user.bean.HomeMenuItem;

import java.util.List;

public class HomeListAdapter extends BaseAdapter {
    List<HomeMenuItem> menuItems;
    LayoutInflater mLayoutInflater;

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
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_line_1, null);
        }

        ImageView itemIcon = (ImageView) convertView.findViewById(R.id.item_line_imager);
        TextView itemText = (TextView) convertView.findViewById(R.id.item_line_topText);
        HomeMenuItem item = getItem(position);
        itemIcon.setImageResource(item.mIcon);
        itemText.setText(item.mString);

        return convertView;
    }


}