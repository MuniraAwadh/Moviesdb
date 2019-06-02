package com.example.android.moviesdb;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class MoviesLoader extends AsyncTaskLoader<List<MovieData>> {

    String url;

    public MoviesLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {

        forceLoad();
    }

    @Override
    public List<MovieData> loadInBackground() {
        Log.d("this","this is the loader : "+ url);
        String response = NetworkUtils.fetchResponse(url);
        Log.d("this","this is the loader : "+ response);
        return JSONUtils.extractFeatureFromJson(response);
    }
}
