package com.example.chenhao.simpleapp.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.chenhao.simpleapp.bean.TrafficItem;


/**
 * Created by chenhao on 17-3-21.
 * 不要管 没怎么用
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder
{

    /**
     * Instantiates a new Base view holder.
     *
     * @param itemView the item view
     */
    public BaseViewHolder(View itemView)
    {
        super(itemView);
    }


    /**
     * On bin view.
     *
     * @param data the data
     */
    public abstract void onBinView(TrafficItem data);


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
