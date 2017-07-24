package com.example.chenhao.simpleapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by chenhao on 17-3-21.
 */

public abstract class BaseRecycleViewHolder<T> extends RecyclerView.ViewHolder
{

    public BaseRecycleViewHolder(View itemView)
    {
        super(itemView);
    }


    public abstract void onBinView(T data);

    public <T extends View> T finView(int id)
    {
        return (T) itemView.findViewById(id);
    }
}
