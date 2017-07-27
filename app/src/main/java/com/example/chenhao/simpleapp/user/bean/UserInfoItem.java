package com.example.chenhao.simpleapp.user.bean;

/**
 * The type User info item.
 * 用户信息的一个实体类
 */
public class UserInfoItem {
    /**
     * 0 有头像
     * 1 文字
     * 2 文字变红
     */
    public int mType;
    /**
     * The M title.
     */
    public String mTitle;
    /**
     * The M icon id.
     */
    public int mIconId;
    /**
     * The M content.
     */
    public String mContent;

    /**
     * Instantiates a new User info item.
     *
     * @param mType   the m type
     * @param mTitle  the m title
     * @param mIconId the m icon id
     */
    public UserInfoItem(int mType, String mTitle, int mIconId) {
        this.mType = mType;
        this.mTitle = mTitle;
        this.mIconId = mIconId;
    }

    /**
     * Instantiates a new User info item.
     *
     * @param mType    the m type
     * @param mTitle   the m title
     * @param mContent the m content
     */
    public UserInfoItem(int mType, String mTitle, String mContent) {
        this.mType = mType;
        this.mTitle = mTitle;
        this.
                mContent = mContent;
    }
}