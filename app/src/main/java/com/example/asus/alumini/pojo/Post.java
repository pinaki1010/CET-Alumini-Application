package com.example.asus.alumini.pojo;

public class Post {
    String caption;
    String photo;
    long time;
    String user;

    public Post(String caption, String photo, long time, String user) {
        this.caption = caption;
        this.photo = photo;
        this.time = time;
        this.user = user;
    }

    public Post() {
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
