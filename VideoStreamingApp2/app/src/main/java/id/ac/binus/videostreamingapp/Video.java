package id.ac.binus.videostreamingapp;

import java.io.Serializable;

public class Video implements Serializable {
    private String title;
    private String desc;
    private String author;
    private String vidURL;
    private String imgURL;
    private String views;
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVidURL() {
        return vidURL;
    }

    public void setVidURL(String vidURL) {
        this.vidURL = vidURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getViews() { return views; }

    public void setViews(String views) { this.views = views; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }
}
