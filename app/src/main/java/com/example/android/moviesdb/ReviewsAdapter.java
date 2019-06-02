package com.example.android.moviesdb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {
    List<Review> reviews;
    Context context;
    private View item;
    public ReviewsAdapter(Context context, List<Review> reviews) {
        this.context=context;
        this.reviews=reviews;
    }

    @NonNull
    @Override
    public ReviewsAdapter.ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        item = inflater.inflate(R.layout.list_item_reviews, parent, false);
        return new ReviewsAdapter.ReviewsViewHolder(item);

    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder reviewsViewHolder, int position) {

        reviewsViewHolder.review_author.setText(reviews.get(position).getAuther());
        reviewsViewHolder.review_author.setContentDescription(reviews.get(position).getAuther());
        reviewsViewHolder.review_content.setText(reviews.get(position).getContents());
        reviewsViewHolder.review_content.setContentDescription(reviews.get(position).getContents());
    }


    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder  {
        private TextView review_author ;
        private TextView review_content;
        public ReviewsViewHolder(View itemView) {
            super(itemView);
            review_author = (TextView) itemView.findViewById(R.id.review_author);
            review_content = (TextView) itemView.findViewById(R.id.review_content);


        }
    }
}
