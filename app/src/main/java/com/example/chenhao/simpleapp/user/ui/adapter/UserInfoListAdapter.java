package com.example.chenhao.simpleapp.user.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.user.bean.UserInfoItem;

import java.util.List;


/**
 * Created by chenhao on 17-3-19.
 * 显示用户信息的一个ListView 适配器
 */
public class UserInfoListAdapter extends BaseListGridAdapter<UserInfoItem> {

    /**
     * Instantiates a new User info list adapter.
     *
     * @param context the context
     * @param items   the items
     */
    public UserInfoListAdapter(Context context, List<UserInfoItem> items) {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_line_3, null);
        }


//        ImageView img = findView(convertView, R.id.item_line_imager);
        TextView t1 = findView(convertView, R.id.item_t1);
        TextView t2 = findView(convertView, R.id.item_t2);

        UserInfoItem item = getItem(position);
        t1.setText(item.mTitle);
        if (item.mType == 0) {
//            img.setVisibility(View.VISIBLE);
//            img.setImageResource(item.mIconId);
            t2.setVisibility(View.GONE);
        } else if (item.mType == 1) {
            t2.setVisibility(View.VISIBLE);
            t2.setText(isEmpty(item.mContent));
//            img.setVisibility(View.GONE);
        } else if (item.mType == 2) {
            t2.setVisibility(View.VISIBLE);
            t2.setText(isEmpty(item.mContent));
            t2.setTextColor(Color.RED);
//            img.setVisibility(View.GONE);
        }

        return convertView;
    }


}
