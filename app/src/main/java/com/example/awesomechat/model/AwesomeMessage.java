package com.example.awesomechat.model;

public class AwesomeMessage {
    private String text;
    private String name;
    private String imgUrl;

    public AwesomeMessage() {
    }

    public AwesomeMessage(String text, String name, String imgUrl) {
        this.text = text;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "AwesomeMessage{" +
                "text='" + text + '\'' +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
