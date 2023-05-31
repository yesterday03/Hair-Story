package com.xiong.stockdiagnosticdiary.bean;

public class GonGao {
    private String Title;
    private String content;

    public GonGao(String title, String content) {
        Title = title;
        this.content = content;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
