package com.example.chenhao.simpleapp.bean;

/**
 * Created by chenhao on 17-3-21.
 * 这就是一个填充RecyclerView 适配器的一个简单实体类
 */
public class TrafficItem {

    /**
     * The M type.
     */
    public int mType;
    /**
     * The M icon 1.
     */
    public int mIcon1, /**
     * The M icon 2.
     */
    mIcon2;
    /**
     * The M back id.
     */
    public int mBackId;
    /**
     * The Top text.
     */
    public String topText, /**
     * The Below text.
     */
    belowText;
    /**
     * The Top text 2.
     */
    public String topText2, /**
     * The Below text 2.
     */
    belowText2;

    /**
     * Instantiates a new Traffic item.
     *
     * @param mType   the m type
     * @param mIcon1  the m icon 1
     * @param topText the top text
     */
    public TrafficItem(int mType, int mIcon1, String topText) {
        this.mType = mType;
        this.mIcon1 = mIcon1;
        this.topText = topText;
    }

    /**
     * Instantiates a new Traffic item.
     *
     * @param mType     the m type
     * @param mIcon1    the m icon 1
     * @param backId    the back id
     * @param topText   the top text
     * @param belowText the below text
     */
    public TrafficItem(int mType, int mIcon1, int backId, String topText, String belowText) {
        this.mType = mType;
        this.mIcon1 = mIcon1;
        mBackId = backId;
        this.topText = topText;
        this.belowText = belowText;
    }
}



