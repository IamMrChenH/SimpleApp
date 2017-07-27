package com.example.chenhao.simpleapp.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.bean.TrafficItem;


/**
 * Created by chenhao on 17-3-22.
 * RecycleView的Item显示不同的操作
 */
public class TraTwoViewHolder extends BaseViewHolder {


    private ImageView mImager1;
    private TextView topText1;

    /**
     * Instantiates a new Tra two view holder.
     *
     * @param itemView the item view
     */
    public TraTwoViewHolder(View itemView) {
        super(itemView);
        mImager1 = finView(R.id.item_line_imager);
        topText1 = finView(R.id.item_line_text);

    }

    @Override
    public void onBinView(TrafficItem data) {

        mImager1.setImageResource(data.mIcon1);
        topText1.setText(data.topText);
    }

}
