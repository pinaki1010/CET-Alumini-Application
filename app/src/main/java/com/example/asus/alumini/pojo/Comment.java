package com.example.asus.alumini.pojo;

public class Comment {
    private String user;
    private String comment;
    private long time;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Comment() {

    }

    public Comment(String user, String comment, long time) {
        this.user = user;
        this.comment = comment;
        this.time = time;
    }
}
