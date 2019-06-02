package com.example.android.moviesdb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MoviesDAO {
    @Insert

    public void insertMovie(MovieData movie);

    @Insert

    public void insertMultipleMovies(List<MovieData> movies);

   @Query("DELETE FROM MovieData WHERE imageId= :id")
   void deleteMovie(String id);

    @Update
    public void updateMovie(MovieData movie);

   @Query("SELECT * FROM MovieData")
   public List<MovieData> getAllMovies();

   @Query("SELECT * FROM MovieData WHERE imageId= :id")
    public MovieData getSingleMovie (String id);
}
