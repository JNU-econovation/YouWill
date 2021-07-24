package com.kangaroos.youwill;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class HealingItem implements Comparable<HealingItem> {
    String title;
    String content;
    String date;
    HashMap<String, Boolean> likes = new HashMap<>();
    int likesCount;
    String id;
    String uid;


    public HealingItem() {
    }

    public HealingItem(String title, String content, String date, HashMap likes, int count, String id, String uid) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.likes = likes;
        this.likesCount = count;
        this.id = id;
        this.uid = uid;
    }

    protected HealingItem(Parcel in) {
        title = in.readString();
        content = in.readString();
        date = in.readString();
        likesCount = in.readInt();
        likes = in.readHashMap(ClassLoader.getSystemClassLoader());
        id = in.readString();
        uid = in.readString();
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public HashMap<String, Boolean> getLikes() {
        return likes;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public String getUid() {
        return uid;
    }

    @Override
    public int compareTo(HealingItem o) {
        if (this.likesCount < o.likesCount) {
            return -1;
        }
        if (likesCount == o.likesCount) {
            return 0;
        }
        if (likesCount > o.likesCount) {
            return -1;
        }
        return 0;
    }

}
