package com.example.chenhao.simpleapp.user.bean;

/**
 * The type Home menu item.
 * 显示用户界面,ListView 适配器的实体类
 */
public class HomeMenuItem {
    /**
     * The Name.
     */
    public String name;
    /**
     * The Content.
     */
    public String content;
    /**
     * The Content color.
     */
    public int contentColor;
    /**
     * The Content size.
     */
    public int contentSize = -1;

    /**
     * Instantiates a new Home menu item.
     */
    public HomeMenuItem() {
    }

    /**
     * Instantiates a new Home menu item.
     *
     * @param name         the name
     * @param content      the content
     * @param contentColor the content color
     */
    public HomeMenuItem(String name, String content, int contentColor) {
        this.name = name;
        this.content = content;
        this.contentColor = contentColor;
    }

    /**
     * Instantiates a new Home menu item.
     *
     * @param name         the name
     * @param content      the content
     * @param contentColor the content color
     * @param contentSize  the content size
     */
    public HomeMenuItem(String name, String content, int contentColor, int contentSize) {
        this.name = name;
        this.content = content;
        this.contentColor = contentColor;
        this.contentSize = contentSize;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets content color.
     *
     * @return the content color
     */
    public int getContentColor() {
        return contentColor;
    }

    /**
     * Sets content color.
     *
     * @param contentColor the content color
     */
    public void setContentColor(int contentColor) {
        this.contentColor = contentColor;
    }

    /**
     * Gets content size.
     *
     * @return the content size
     */
    public int getContentSize() {
        return contentSize;
    }

    /**
     * Sets content size.
     *
     * @param contentSize the content size
     */
    public void setContentSize(int contentSize) {
        this.contentSize = contentSize;
    }
}