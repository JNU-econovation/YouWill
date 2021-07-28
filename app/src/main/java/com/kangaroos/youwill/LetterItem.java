package com.kangaroos.youwill;

import android.os.Parcel;
import android.os.Parcelable;

public class LetterItem  {
    String recipient_number;
    String write_date;
    String send_date;
    int paper_type;
    String content;


    public LetterItem() {
    }

    public LetterItem(String recipient_number, String write_date, String send_date, int paper_type, String content) {
        this.recipient_number = recipient_number;
        this.write_date = write_date;
        this.send_date = send_date;
        this.paper_type = paper_type;
        this.content = content;
    }

    protected LetterItem(Parcel in) {
        recipient_number = in.readString();
        write_date = in.readString();
        send_date = in.readString();
        paper_type = in.readInt();
        content = in.readString();
    }


    public String getRecipient_number() { return recipient_number; }
    public String getWrite_date() { return write_date; }
    public String getSend_date() { return send_date; }
    public int getPaper_type() { return paper_type; }
    public String getContent() { return content; }
}
