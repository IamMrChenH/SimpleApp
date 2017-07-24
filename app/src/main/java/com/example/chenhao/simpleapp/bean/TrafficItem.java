package com.example.chenhao.simpleapp.bean;

/**
 * Created by chenhao on 17-3-21.
 */

public class TrafficItem {
    public int mType;
    public int mIcon1, mIcon2;
    public int mBackId;
    public String topText, belowText;
    public String topText2, belowText2;

    public TrafficItem(int mType, int mIcon1, String topText) {
        this.mType = mType;
        this.mIcon1 = mIcon1;
        this.topText = topText;
    }

    public TrafficItem(int mType, int mIcon1, int backId, String topText, String belowText) {
        this.mType = mType;
        this.mIcon1 = mIcon1;
        mBackId = backId;
        this.topText = topText;
        this.belowText = belowText;
    }
}



