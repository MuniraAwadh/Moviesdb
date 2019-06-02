package com.example.android.moviesdb;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Trailer implements Parcelable {
   private String movieId;
   // @SerializedName("key")
    private String key;
  //  @SerializedName("name")
    private String name;
  //  @SerializedName("id")
    private String id;
  //  @SerializedName("size")
    private String size;
  //  @SerializedName("site")
    private String site;
  //  @SerializedName("type")
    private String type;
   // @SerializedName("iso_639_1")
    private String iso_639_1; //language
   // @SerializedName("iso_3166_1")
    private String iso_3166_1; // country
    public Trailer(String id,String key, String name){
        this.id=id;
        this.movieId=movieId;
        this.key=key;
        this.name=name;
    }

    protected Trailer(Parcel in) {
        movieId = in.readString();
        key = in.readString();
        name = in.readString();
        id = in.readString();
        size = in.readString();
        site = in.readString();
        type = in.readString();
        iso_639_1 = in.readString();
        iso_3166_1 = in.readString();
    }

    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };

    public String getMovieVideo(){
        return videoPath();
    }
    public String getMovieId(){
        return movieId;
    }
    public void setMovieId(String movieId){
       this.movieId=movieId;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getKey(){
        return key;
    }
    public void setKey(String key){
        this.key=key;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getSize(){
        return size;
    }
    public void setSize(String size){
        this.size=size;
    }
    public String getSite(){
        return site;
    }
    public void setSite(String site){
        this.site=site;
    }
    public String videoPath(){
        String VIDEO_URL= "https://www.youtube.com/watch?v=";
        return VIDEO_URL + key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieId);
        dest.writeString(key);
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(size);
        dest.writeString(site);
        dest.writeString(type);
        dest.writeString(iso_639_1);
        dest.writeString(iso_3166_1);
    }
}
