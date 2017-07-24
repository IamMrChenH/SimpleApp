package com.example.chenhao.simpleapp.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.bean.TrafficItem;



public class TraOneViewHolder extends BaseViewHolder {


    private ImageView mImager1, mImager2;
    private TextView topText1, topText2, belowText1, belowText2;
    private View back;

    public TraOneViewHolder(View itemView) {
        super(itemView);
        mImager1 = finView(R.id.item_line_imager);
        mImager2 = finView(R.id.item_line_imager2);

        topText1 = finView(R.id.item_line_topText);
        topText2 = finView(R.id.item_line_topText2);
        belowText1 = finView(R.id.item_line_belowText);
        belowText2 = finView(R.id.item_line_belowText2);


        back = finView(R.id.back);
    }

    @Override
    public void onBinView(TrafficItem data) {
        back.setBackgroundResource(data.mBackId);

        mImager1.setImageResource(data.mIcon1);
        mImager2.setImageResource(data.mIcon2);

        topText1.setText(data.topText);
        topText2.setText(data.topText2);
        belowText1.setText(data.belowText);
        belowText2.setText(data.belowText2);


    }

}
