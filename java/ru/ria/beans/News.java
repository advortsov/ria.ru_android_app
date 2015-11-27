package ru.ria.beans;

import java.io.Serializable;

public class News implements Serializable {
    private String imgRef;
    private String title;
    private String text;
    private String date;

    private static final long serialVersionUID = -7367289796391099264L;

    public News(String imgRef, String title, String text, String date) {
        this.imgRef = imgRef;
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public String getImgRef() {
        return imgRef;
    }

    public void setImgRef(String imgRef) {
        this.imgRef = imgRef;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "News{" +
                "imgRef='" + imgRef + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
