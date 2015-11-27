package ru.ria.beans;

import java.io.Serializable;

public class EntryNews implements Serializable {
    private String imgRef;
    private String newsRef;
    private String title;
    private String announce;
    private String date;
    private Integer number;

    private static final long serialVersionUID = -7327289796391032618L;

    public EntryNews(String imgRef, String newsRef, String title, String announce, String date) {
        this.imgRef = imgRef;
        this.newsRef = newsRef;
        this.title = title;
        this.announce = announce;
        this.date = date;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getImgRef() {
        return imgRef;
    }

    public void setImgRef(String imgRef) {
        this.imgRef = imgRef;
    }

    public String getNewsRef() {
        return newsRef;
    }

    public void setNewsRef(String newsRef) {
        this.newsRef = newsRef;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnounce() {
        return announce;
    }

    public void setAnnounce(String announce) {
        this.announce = announce;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "EntryNews{" +
                "imgRef='" + imgRef + '\'' +
                ", newsRef='" + newsRef + '\'' +
                ", title='" + title + '\'' +
                ", announce='" + announce + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
