package com.example.chenhao.simpleapp.user.bean;

public class HomeMenuItem {
    public String name;
    public String content;
    public int contentColor;
    public int contentSize = -1;

    public HomeMenuItem() {
    }

    public HomeMenuItem(String name, String content, int contentColor) {
        this.name = name;
        this.content = content;
        this.contentColor = contentColor;
    }

    public HomeMenuItem(String name, String content, int contentColor, int contentSize) {
        this.name = name;
        this.content = content;
        this.contentColor = contentColor;
        this.contentSize = contentSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContentColor() {
        return contentColor;
    }

    public void setContentColor(int contentColor) {
        this.contentColor = contentColor;
    }

    public int getContentSize() {
        return contentSize;
    }

    public void setContentSize(int contentSize) {
        this.contentSize = contentSize;
    }
}