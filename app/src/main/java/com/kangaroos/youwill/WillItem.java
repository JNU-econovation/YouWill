package com.kangaroos.youwill;

import android.os.Parcel;
import android.os.Parcelable;

public class WillItem {
    String date;
    String content;
    String id;

    public WillItem() {
    }

    public WillItem(String date, String content, String id) {
        this.date = date;
        this.content = content;
        this.id = id;
    }

    protected WillItem(Parcel in) {
        date = in.readString();
        content = in.readString();
        id = in.readString();
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }
}
