package com.example.android.moviesdb;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {

    private String rId;
    private String auther;
    private String contents;

    public Review(String rId, String auther, String contents){
        this.rId=rId;
        this.auther=auther;
        this.contents=contents;
    }

    protected Review(Parcel in) {
        rId = in.readString();
        auther = in.readString();
        contents = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getRId(){
        return rId;
    }
    public void setRId(String rId){
        this.rId=rId;
    }
    public String getAuther(){
        return auther;
    }
    public void setAuther(String auther){
        this.auther=auther;
    }
    public String getContents(){
        return contents;
    }
    public void setContents(String contents){
        this.contents=contents;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rId);
        dest.writeString(auther);
        dest.writeString(contents);
    }
}
