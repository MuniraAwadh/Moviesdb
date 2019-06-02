package com.example.android.moviesdb;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

public class MovieViewData extends AndroidViewModel {
    Database database;
    MutableLiveData<List<MovieData>> movieLivedata= new MutableLiveData<>();


    public MovieViewData(@NonNull Application application) {
        super(application);
        database =Database.getDatabase(application);
    }
    public LiveData<List <MovieData>> getMovies(boolean fav,String url){
        if (fav){
            //load it from room
            new FavoriteAsyncTask().execute();
            return movieLivedata;

        } else {
            new MoviesFromServerAsyncTask().execute(url);
            return movieLivedata;
        } }

    public class MoviesFromServerAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... url) {
            String response = NetworkUtils.fetchResponse(url[0]);
            List <MovieData> movies =  JSONUtils.extractFeatureFromJson(response);
            movieLivedata.postValue(movies);
            return null;
        }

    }

    public class FavoriteAsyncTask extends AsyncTask <Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            movieLivedata.postValue(database.moviesDAO().getAllMovies());
            return null;
        }

    }
}
