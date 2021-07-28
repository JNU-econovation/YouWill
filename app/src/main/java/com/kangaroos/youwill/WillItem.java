package com.kangaroos.youwill;

import android.os.Parcel;
import android.os.Parcelable;

public class WillItem {
    String date;
    String content;

    public WillItem() {
    }

    public WillItem(String date, String content) {
        this.date = date;
        this.content = content;
    }

    protected WillItem(Parcel in) {
        date = in.readString();
        content = in.readString();

    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

}
