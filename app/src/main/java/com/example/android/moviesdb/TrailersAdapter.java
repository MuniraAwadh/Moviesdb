package com.example.android.moviesdb;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder> {
    List<Trailer> trailers;
    Context context;
    private View item;
    public TrailersAdapter(Context context, List<Trailer> trailers) {
        this.context=context;
        this.trailers=trailers;
    }

    @NonNull
    @Override
    public TrailersViewHolder onCreateViewHolder(@NonNull ViewGroup parent,final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        item = inflater.inflate(R.layout.list_item_trailers, parent, false);
        return new TrailersViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersViewHolder trailersViewHolder, final int position) {
        trailersViewHolder.trailer_title.setText(trailers.get(position).getName());
        trailersViewHolder.trailer_title.setContentDescription(trailers.get(position).getName());
        Picasso.with(context)
                .load("https://img.youtube.com/vi/" + trailers.get(position).getKey()+ "/0.jpg")
                .into(trailersViewHolder.trailer_thumbnail);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public class TrailersViewHolder extends RecyclerView.ViewHolder  {
         private TextView trailer_title;
        private ImageView trailer_thumbnail;
        public TrailersViewHolder(View itemView) {
            super(itemView);
            trailer_title = (TextView) itemView.findViewById(R.id.trailer_title);
            trailer_thumbnail  = (ImageView) itemView.findViewById(R.id.trailer_thumbnail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse(trailers.get(getAdapterPosition()).getMovieVideo()));
                    context.startActivity(intent);
                }
            });
        }
    }}


