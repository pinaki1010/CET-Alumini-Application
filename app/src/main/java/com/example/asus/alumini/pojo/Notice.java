package com.example.asus.alumini.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Notice implements Parcelable {
    private String title;
    private String body;
    private String link;

    protected Notice(Parcel in) {
        title = in.readString();
        body = in.readString();
        link = in.readString();
    }

    public static final Creator<Notice> CREATOR = new Creator<Notice>() {
        @Override
        public Notice createFromParcel(Parcel in) {
            return new Notice(in);
        }

        @Override
        public Notice[] newArray(int size) {
            return new Notice[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Notice() {
    }

    public Notice(String title, String body, String link) {
        this.title = title;
        this.body = body;
        this.link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(body);
        parcel.writeString(link);
    }
}
