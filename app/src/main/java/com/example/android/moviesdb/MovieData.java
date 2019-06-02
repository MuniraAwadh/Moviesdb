package com.example.android.moviesdb;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class MovieData implements Parcelable{

    @PrimaryKey (autoGenerate = true)
    private int movieId;
    private String imageId;
    private String title;
    private String overview;
    private String releasedate;
    private String voteaverage;



    public MovieData(String imageId,String title,String overview,String releasedate,String voteaverage) {
        this.imageId = imageId;
        this.title=title;
        this.overview=overview;
        this.releasedate=releasedate;
        this.voteaverage=voteaverage;

    }

    protected MovieData(Parcel in) {
        imageId = in.readString();
        title = in.readString();
        overview = in.readString();
        releasedate = in.readString();
        voteaverage = in.readString();

    }

    public static final Creator<MovieData> CREATOR = new Creator<MovieData>() {
        @Override
        public MovieData createFromParcel(Parcel in) {
            return new MovieData(in);
        }

        @Override
        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }
    };

    public String getMoviePoster() {

        return MoviePosterPath();
    }
    public String getImageId(){
        return imageId;
    }
    public String getTitle() {

        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getOverview() {

        return overview;
    }
    public void setOverview(String overview){
        this.overview=overview;
    }
    public String getReleasedate() {

        return releasedate;
    }
    public void setReleasedate(String releasedate){
        this.releasedate=releasedate;
    }
    public String getVoteaverage() {

        return voteaverage;
    }

    public int getMovieId(){
        return movieId;
    }
    public void setMovieId(int id){
        this.movieId=id;
    }
    public void setVoteaverage(String voteaverage){

        this.voteaverage=voteaverage;
    }
    private String MoviePosterPath() {
        String POSTER_URL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/";
        return POSTER_URL +imageId;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageId);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(releasedate);
        dest.writeString(voteaverage);

    }
}
