package com.example.android.moviesdb;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtils {
    private static final String LOG_TAG = "JsonUtils";
    private static final String RESULTS_KEY = "results";
    private static final String POSTER_KEY = "poster_path";
    private static final String POSTER_TITLE = "title";
    private static final String POSTER_Date = "release_date";
    private static final String POSTER_VOTE = "vote_average";
    private static final String POSTER_OVERVIEW = "overview";
    private static final String TRAILER_KEY = "key";
    private static final String TRAILER_NAME = "name";
    private static final String TRAILER_ID = "id";
    private static final String REVIEW_ID = "id";
    private static final String REVIEW_AUTHOR = "author";
    private static final String REVIEW_CONTENT = "content";
       public static List<MovieData> extractFeatureFromJson(String movieJSON) {
        if (TextUtils.isEmpty(movieJSON)) {
            return null;
        }
        List<MovieData> movies = new ArrayList<>();
        try {
            JSONObject baseJsonResponse = new JSONObject(movieJSON);
            if (baseJsonResponse.has(RESULTS_KEY)) {
                JSONArray moviesArray = baseJsonResponse.getJSONArray(RESULTS_KEY);
                for (int i = 0; i < moviesArray.length(); i++) {
                    JSONObject currentmovie = moviesArray.getJSONObject(i);
                    String poster = currentmovie.optString(POSTER_KEY);
                    String title = currentmovie.optString(POSTER_TITLE);
                    String vote = currentmovie.optString(POSTER_VOTE);
                    String date = currentmovie.optString(POSTER_Date);
                    String overview = currentmovie.optString(POSTER_OVERVIEW);

                    if (!poster.equals("null")) {
                        MovieData movie = new MovieData(poster, title, overview, date, vote);
                        movies.add(movie);
                    }
                }
            } else {
                movies = null;
            }
        } catch (JSONException e) {

            Log.e(LOG_TAG, "Problem parsing the movies JSON results", e);
        }

        if (movies == null){
            Log.i(LOG_TAG, "movies is null");
        } else {
            Log.i(LOG_TAG, "movies is NOT null");
        }
        return movies;
    }
    public static List<Trailer> extractTrailerFromJson(String trailerJSON) {
        if (TextUtils.isEmpty( trailerJSON)) {
            return null;
        }
        List<Trailer> trailers = new ArrayList<>();
        try {
            JSONObject JsonResponse = new JSONObject( trailerJSON);
            if (JsonResponse.has(RESULTS_KEY)) {
                JSONArray moviesArray = JsonResponse.getJSONArray(RESULTS_KEY);
                for (int i = 0; i < moviesArray.length(); i++) {
                    JSONObject currentTrailer = moviesArray.getJSONObject(i);
                    String name_video =currentTrailer.optString(TRAILER_NAME);
                    String key_video = currentTrailer.optString(TRAILER_KEY);
                    String id_video = currentTrailer.optString(TRAILER_ID);

                  //  if (!name_video.equals("null")) {
                        Trailer trailer = new Trailer(id_video,key_video,name_video);
                        trailers.add(trailer);

                }
            } else {
                trailers = null;
            }
        } catch (JSONException e) {

            Log.e(LOG_TAG, "Problem parsing the movies trailer JSON results", e);
        }

        if (trailers == null){
            Log.i(LOG_TAG, "Trailer is null");
        } else {
            Log.i(LOG_TAG, "Trailer is NOT null");
        }
        return trailers;
    }
    public static List<Review> extractReviewFromJson(String reviewJSON) {
        if (TextUtils.isEmpty( reviewJSON)) {
            return null;
        }
        List<Review> reviews = new ArrayList<>();
        try {
            JSONObject Response = new JSONObject( reviewJSON);
            if (Response.has(RESULTS_KEY)) {
                JSONArray moviesArray = Response.getJSONArray(RESULTS_KEY);
                for (int i = 0; i < moviesArray.length(); i++) {
                    JSONObject currentReview = moviesArray.getJSONObject(i);
                    String author_review =currentReview.optString(REVIEW_AUTHOR);
                    String content_review = currentReview.optString(REVIEW_CONTENT);
                    String id_review = currentReview.optString(REVIEW_ID);

                    if (!author_review.equals("null")) {
                        Review review = new Review(id_review,content_review,author_review);
                        reviews.add(review);
                    }
                }
            } else {
                reviews = null;
            }
        } catch (JSONException e) {

            Log.e(LOG_TAG, "Problem parsing the movies Review JSON results", e);
        }

        if (reviews == null){
            Log.i(LOG_TAG, "Review is null");
        } else {
            Log.i(LOG_TAG, "Review is NOT null");
        }
        return reviews;

}}
