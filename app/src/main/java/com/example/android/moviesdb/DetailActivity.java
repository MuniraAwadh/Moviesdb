package com.example.android.moviesdb;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.Loader;
import android.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks{
    private TextView mtitle;
    private TextView mdate;
    private TextView mvote;
    private TextView moverview;
    private ImageView mPoster;
    private ImageView mStar;
    Boolean fromClick=false;
    List<Trailer>trailers;
    List<Review>reviews;
    private RecyclerView mrecyclerViewitem;
    private RecyclerView r_mrecyclerViewitem;
    private TrailersAdapter adapter;
    private ReviewsAdapter radapter;
    MovieData MoviefromIntent;
    private int id;
    Context context;
    private static final int LODER_ID_ONE=1;
    private static final int LODER_ID_TWO=2;
    FavoriteMovie Fa_worker = new FavoriteMovie(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mtitle =(TextView) findViewById(R.id.titleofMovie);
        mPoster=(ImageView) findViewById(R.id.poster_display) ;
        mdate =(TextView) findViewById(R.id.dateofMovie);
        mvote =(TextView) findViewById(R.id.voteMovie);
        moverview =(TextView) findViewById(R.id.overviewOfMovie);
        mStar= (ImageView) findViewById(R.id.star);
        MoviefromIntent = getIntent().getParcelableExtra("movie");
        String title= MoviefromIntent.getTitle();
        String vote= MoviefromIntent.getVoteaverage();
        String overview= MoviefromIntent.getOverview();
        String date= MoviefromIntent.getReleasedate();
        id= getIntent().getIntExtra("id",0);
        mdate.setText(date);
        mtitle.setText(title);
        mvote.setText(vote);
        moverview.setText(overview);
        Picasso.with(this).load(MoviefromIntent.getMoviePoster()).into(mPoster);
        //------------trailers-----------------
        trailers = new ArrayList<>();
        mrecyclerViewitem = findViewById(R.id.recyclerview_items);
        mrecyclerViewitem.setHasFixedSize(true);
        adapter = new TrailersAdapter(this, trailers);
        mrecyclerViewitem.setAdapter(adapter);
        LinearLayoutManager layoutManager = new  LinearLayoutManager(this);
        getLoaderManager().initLoader(LODER_ID_ONE, null, this);
        mrecyclerViewitem.setLayoutManager(layoutManager);
        //--------------reviews-----------------
        reviews = new ArrayList<>();
        r_mrecyclerViewitem = findViewById(R.id.recyclerview_reviews);
        mrecyclerViewitem.setHasFixedSize(true);
        radapter = new ReviewsAdapter(this, reviews);
        r_mrecyclerViewitem.setAdapter(radapter);
        LinearLayoutManager rlayoutManager = new  LinearLayoutManager(this);
        getLoaderManager().initLoader(LODER_ID_TWO, null, this);
        r_mrecyclerViewitem.setLayoutManager(rlayoutManager);
        //-----------------------------------------

        new SingleMovieAsyncTask().execute(MoviefromIntent);

        mStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new SingleMovieAsyncTask().execute(MoviefromIntent);
                fromClick=true;
            }
        });

    }
    private String buildURL() {
        final String API_KEY = "4b858f5d8d9b6e164f884ef4949a32e3";
        Uri builder ;
        builder =Uri.parse("https://api.themoviedb.org/3/movie/"+id+"/videos").buildUpon()
                .appendQueryParameter("api_key", API_KEY).build();
        Log.i("build url", "Problem building the URL of videos "+builder.toString());
        return builder.toString();

    }
    private String r_buildURL() {
        final String API_KEY = "4b858f5d8d9b6e164f884ef4949a32e3";
        Uri builder ;
        builder =Uri.parse("https://api.themoviedb.org/3/movie/"+id+"/reviews").buildUpon()
                .appendQueryParameter("api_key", API_KEY).build();
        Log.i("build url", "Problem building the URL of reviews "+builder.toString());
        return builder.toString();

    }

    private void markAsFavorite(MovieData movie) {
        Fa_worker.insertFavoriteMovie(movie);
    }

    private void unfavoriteMovie(MovieData movie) {
        Fa_worker.deleteFavoriteMovie(movie);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        if( id== LODER_ID_ONE){
            return new TrailersLoader(this,buildURL());
        }
        else
            return new ReviewsLoader(this,r_buildURL());
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        int id = loader.getId();
        if (id == LODER_ID_ONE) {
            trailers.clear();
            if (data != null){
                trailers.addAll((List<Trailer>) data);
                adapter.notifyDataSetChanged();
            }}

         else if (id == LODER_ID_TWO){
                reviews.clear();
                if (data != null){
                    reviews.addAll((List<Review>) data);
                    radapter.notifyDataSetChanged();
                }


        }
}

    @Override
    public void onLoaderReset(Loader loader) {
        int id = loader.getId();
        if (id == LODER_ID_ONE) {
            trailers.clear();
            adapter.notifyDataSetChanged();

        }

        else if (id == LODER_ID_TWO){
            reviews.clear();
            radapter.notifyDataSetChanged();

        }
    }


    private class SingleMovieAsyncTask extends AsyncTask<MovieData, Void, MovieData> {

        @Override
        protected MovieData doInBackground(MovieData... movie) {

            Database database = Database.getDatabase(DetailActivity.this);
            MovieData singleMovie = database.moviesDAO().getSingleMovie(movie[0].getImageId());


            return singleMovie;
        }

        @Override
        protected void onPostExecute(MovieData list) {
            super.onPostExecute(list);
            if(fromClick){
                if (list != null) {
                    unfavoriteMovie(MoviefromIntent);
                    mStar.setImageResource(R.drawable.unfav_star);
                } else {
                    markAsFavorite(MoviefromIntent);
                    mStar.setImageResource(R.drawable.fav_star);
                }
            }
            else {
                if (list != null) {
                    mStar.setImageResource(R.drawable.fav_star);
                } else {
                    mStar.setImageResource(R.drawable.unfav_star);
                }
            }
        }
    }

}


