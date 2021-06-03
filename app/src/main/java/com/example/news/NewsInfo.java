package com.example.news;

public class NewsInfo {
    private String icon;        //图片路径
    private String title;       //新闻标题
    private String content;     //新闻描述
    private int type;           //新闻类型

    public NewsInfo(String icon, String title, String content, int type) {
        this.icon = icon;
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

