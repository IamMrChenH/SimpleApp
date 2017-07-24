package com.example.chenhao.simpleapp.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.chenhao.simpleapp.bean.TrafficItem;


/**
 * Created by chenhao on 17-3-21.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder
{

    public BaseViewHolder(View itemView)
    {
        super(itemView);
    }


    public abstract void onBinView(TrafficItem data);


    public <T extends View> T finView(int id)
    {

        return (T) itemView.findViewById(id);
    }
}
