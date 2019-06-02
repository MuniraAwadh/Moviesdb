package com.example.android.moviesdb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
        List<MovieData> movies;
    Context context;
    private View item;
    List<Trailer>results;

    public MoviesAdapter(Context context, List<MovieData> listmovies) {
        this.movies = listmovies;
        this.context = context;
    }
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        item = LayoutInflater.from(context).inflate(R.layout.grid_posters, parent, false);
        return new MovieViewHolder(item);
    }
    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        Picasso.with(context).load(movies.get(position).getMoviePoster()).into(holder.mimagePoster);
       Log.i("msg", "link is " + movies.get(position).getMoviePoster());

        item.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                movies.get(position);

                Intent intentToStartDetailActivity=new Intent(context, DetailActivity.class);
                intentToStartDetailActivity.putExtra("id" , position);
                intentToStartDetailActivity.putExtra("movie" , movies.get(position));
                intentToStartDetailActivity.putExtra("title" , movies.get(position).getTitle());
                intentToStartDetailActivity.putExtra("release_date" , movies.get(position).getReleasedate());
                intentToStartDetailActivity.putExtra("overview" , movies.get(position).getOverview());
                intentToStartDetailActivity.putExtra("vote_average" , movies.get(position).getVoteaverage());
                intentToStartDetailActivity.putExtra("poster_path" , movies.get(position).getMoviePoster());

                context.startActivity(intentToStartDetailActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView mimagePoster;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mimagePoster = itemView.findViewById(R.id.image_poster);

        }
    }
}
