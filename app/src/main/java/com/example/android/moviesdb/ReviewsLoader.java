package com.example.android.moviesdb;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class ReviewsLoader extends AsyncTaskLoader<List<Review>> {

    String url;

    public ReviewsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {

        forceLoad();
    }

    @Override
    public List<Review> loadInBackground() {
        Log.d("this","this is the loader : "+ url);
        String response = NetworkUtils.fetchResponse(url);
        Log.d("this","this is the loader of review: "+ response);
        return JSONUtils.extractReviewFromJson(response);
    }
}
