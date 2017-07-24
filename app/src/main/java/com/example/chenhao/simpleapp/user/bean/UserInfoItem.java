package com.example.chenhao.simpleapp.user.bean;

public class UserInfoItem {
    /**
     * 0 有头像
     * 1 文字
     * 2 文字变红
     */
    public int mType;
    public String mTitle;
    public int mIconId;
    public String mContent;

    public UserInfoItem(int mType, String mTitle, int mIconId) {
        this.mType = mType;
        this.mTitle = mTitle;
        this.mIconId = mIconId;
    }

    public UserInfoItem(int mType, String mTitle, String mContent) {
        this.mType = mType;
        this.mTitle = mTitle;
        this.
                mContent = mContent;
    }
}