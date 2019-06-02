package com.example.android.moviesdb;

import android.app.LoaderManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{
private RecyclerView mrecyclerViewitem;
    private List<MovieData> movies;
    private TextView noInternetMasseage;
    private MoviesAdapter adapter;
    private final int LOADER_ID = 1;
    Context context;
    MovieViewData viewmoviedata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int NUMBER_OF_COLUMNS = 2;

        movies = new ArrayList<>();
        mrecyclerViewitem = findViewById(R.id.recyclerview_items);
        mrecyclerViewitem.setHasFixedSize(true);
        adapter = new MoviesAdapter(this, movies);
        mrecyclerViewitem.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, NUMBER_OF_COLUMNS);
        mrecyclerViewitem.setLayoutManager(layoutManager);
      //  getLoaderManager().initLoader(LOADER_ID, null, this);
        noInternetMasseage = (TextView) findViewById(R.id.no_internet_tv);
        viewmoviedata=ViewModelProviders.of(this).get(MovieViewData.class);
        if (getSortValue().equals(getString(R.string.favorite_value))) {
            viewmoviedata.getMovies(true, "").observe(this, new Observer<List<MovieData>>() {
                @Override
                public void onChanged(@Nullable List<MovieData> movieDatas) {
                    movies.clear();

                    if (movieDatas != null) {
                        movies.addAll(movieDatas);
                        adapter.notifyDataSetChanged();
                    }
                }
            });

        }
        else {
            if (isConnected()) {
                noInternetMasseage.setVisibility(View.GONE);
                viewmoviedata.getMovies(false, buildURL()).observe(this, new Observer<List<MovieData>>() {
                    @Override
                    public void onChanged(@Nullable List<MovieData> movieDatas) {
                        movies.clear();
                        movies.addAll(movieDatas);
                        adapter.notifyDataSetChanged();
                    }
                });

            } else {
                noInternetMasseage.setVisibility(View.VISIBLE);
            }

        }

            }
    @Override
    protected void onRestart() {
        super.onRestart();
        movies.clear();

        if (getSortValue().equals(getString(R.string.favorite_value))) {

            viewmoviedata.getMovies(true, "").observe(this, new Observer<List<MovieData>>() {
                @Override
                public void onChanged(@Nullable List<MovieData> movieDatas) {
                   movies.clear();

                    if (movieDatas != null) {
                        movies.addAll(movieDatas);
                        adapter.notifyDataSetChanged();
                    }
                }
            });

        } else {
            if (isConnected()) {
                noInternetMasseage.setVisibility(View.GONE);
                viewmoviedata.getMovies(false, buildURL()).observe(this, new Observer<List<MovieData>>() {
                    @Override
                    public void onChanged(@Nullable List<MovieData> movieDatas) {
                        movies.clear();
                        movies.addAll(movieDatas);
                        adapter.notifyDataSetChanged();
                    }
                });

            } else {
                noInternetMasseage.setVisibility(View.VISIBLE);
            }
        }}


    private String buildURL() {
        final String API_KEY = "4b858f5d8d9b6e164f884ef4949a32e3";
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
       String sortByValue = sharedPrefs.getString(getString(R.string.sort_by_key),getString(R.string.popular));
        Uri builder ;

        if (!sortByValue.equals(getString(R.string.popular_value))){
            builder =Uri.parse("https://api.themoviedb.org/3/movie/top_rated").buildUpon()
                    .appendQueryParameter("api_key", API_KEY).build();

        }
        else {

            builder =Uri.parse("https://api.themoviedb.org/3/movie/popular").buildUpon()
                    .appendQueryParameter("api_key", API_KEY).build();
        }


        return builder.toString();
    }
    private Boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menusetting) {
           Intent intent = new Intent(MainActivity.this, SettingActivity.class);
           startActivity(intent);
        }
        return true;


}
    private String getSortValue() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPrefs.getString(getString(R.string.sort_by_key), getString(R.string.popular_value));
    }
    }
