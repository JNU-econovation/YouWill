package com.kangaroos.youwill;

import android.os.Parcel;
import android.os.Parcelable;

public class HealingItem implements Parcelable {
    String title;
    String content;
    String date;
    int number;

    public HealingItem() {
    }

    public HealingItem(String title, String content, String date, int number) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.number = number;
    }

    protected HealingItem(Parcel in) {
        title = in.readString();
        content = in.readString();
        date = in.readString();
        number = in.readInt();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public int getNumber() {
        return number;
    }

    public static final Creator<HealingItem> CREATOR = new Creator<HealingItem>() {
        @Override
        public HealingItem createFromParcel(Parcel in) {
            return new HealingItem(in);
        }

        @Override
        public HealingItem[] newArray(int size) {
            return new HealingItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(date);
        dest.writeInt(number);
    }
}
