package com.example.chenhao.simpleapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by chenhao on 17-3-21.
 *
 * 继承RecyclerView.ViewHolder的一个类，目的是为了减少其他继承这个类的代码
 * @param <T> the type parameter
 */
public abstract class BaseRecycleViewHolder<T> extends RecyclerView.ViewHolder
{

    /**
     * Instantiates a new Base recycle view holder.
     *
     * @param itemView the item view
     */
    public BaseRecycleViewHolder(View itemView)
    {
        super(itemView);
    }


    /**
     * On bin view.
     *
     * @param data the data
     */
    public abstract void onBinView(T data);

    /**
     * Fin view t.
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the t
     */
    public <T extends View> T finView(int id)
    {
        return (T) itemView.findViewById(id);
    }
}
