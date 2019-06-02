package com.example.android.moviesdb;

import android.content.Context;
import android.os.AsyncTask;

public class FavoriteMovie {
    private boolean setFavorite = true;
    private Database database;


    public FavoriteMovie(Context context) {
        database = Database.getDatabase(context);
    }
    public void insertFavoriteMovie(MovieData movieData){
        setFavorite=true;
        new FavoriteMovieAsyncTask ().execute(movieData);
    }

    public void deleteFavoriteMovie(MovieData movieData){
        setFavorite=false;
        new FavoriteMovieAsyncTask().execute(movieData);
    }


    public class FavoriteMovieAsyncTask extends AsyncTask<MovieData, Void, Void> {

        @Override
        protected Void doInBackground(MovieData... movieData) {

            if (setFavorite) {
                database.moviesDAO().insertMovie(movieData[0]);
            } else {
                database.moviesDAO().deleteMovie(movieData[0].getImageId());
            }
          return null;
        }

    }
}