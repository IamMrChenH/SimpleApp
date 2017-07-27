package com.example.chenhao.simpleapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.bean.TrafficItem;
import com.example.chenhao.simpleapp.ui.holder.BaseViewHolder;
import com.example.chenhao.simpleapp.ui.holder.TraOneViewHolder;
import com.example.chenhao.simpleapp.ui.holder.TraTwoViewHolder;

import java.util.List;


/**
 * Created by chenhao on 17-3-20.
 * RecyclerView的一个适配器 可以制作一个对于ListView来说不规则的一个界面
 */
public class TrafficHomeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    /**
     * The interface On item click listener.
     */
    public interface OnItemClickListener {

        /**
         * On item click.
         *
         * @param view     the view
         * @param position the position
         */
        public void onItemClick(View view, int position);
    }


    /**
     * The M item click listener.
     */
    public OnItemClickListener mItemClickListener;


    /**
     * Sets on item click listener.
     *
     * @param mItemClickListener the m item click listener
     */
    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private LayoutInflater mInflater;
    private List<TrafficItem> mTrafficItems;


    /**
     * Instantiates a new Traffic home adapter.
     *
     * @param context      the context
     * @param TrafficItems the traffic items
     */
    public TrafficHomeAdapter(Context context, List<TrafficItem> TrafficItems) {
        mInflater = LayoutInflater.from(context);
        mTrafficItems = TrafficItems;
    }


    /**
     * 创建VIew
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        TrafficItem tempItem = mTrafficItems.get(0);
        switch (viewType) {
            case 0:
                return new TraOneViewHolder(mInflater.inflate(R.layout.item_line_6, parent, false));
            case 1:
                return new TraTwoViewHolder(mInflater.inflate(R.layout.item_line_7, parent, false));
            case 2:
                return new TraTwoViewHolder(mInflater.inflate(R.layout.item_line_8, parent, false));
            case 3:
                return new TraTwoViewHolder(mInflater.inflate(R.layout.item_line_8, parent, false));
        }
        return new TraOneViewHolder(mInflater.inflate(R.layout.item_line_6, parent, false));

    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).mType;
    }


    /**
     * Gets item.
     *
     * @param position the position
     * @return the item
     */
    public TrafficItem getItem(int position) {
        return mTrafficItems.get(position);
    }

    /**
     * 绑定View
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        LayoutParams lp = null;
        switch (getItemViewType(position)) {
            case 0:
                break;
            case 1:
                lp = holder.itemView.getLayoutParams();
                lp.height = 200;
                holder.itemView.setLayoutParams(lp);
                break;
            case 2:
                lp = holder.itemView.getLayoutParams();
                lp.height = 400;
                holder.itemView.setLayoutParams(lp);
                break;
            case 3:
                lp = holder.itemView.getLayoutParams();
                lp.width = 1080;
                holder.itemView.setLayoutParams(lp);
                break;
        }


        holder.onBinView(getItem(position));
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(v, holder.getLayoutPosition());
                }
            });
        }


    }

    /**
     * 设置item长度
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mTrafficItems.size();
    }


}
