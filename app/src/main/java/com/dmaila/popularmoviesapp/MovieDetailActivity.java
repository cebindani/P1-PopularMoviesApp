package com.dmaila.popularmoviesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movieParcel");

        TextView originalTitleTextView = (TextView) findViewById(R.id.original_title);
        ImageView moviePosterImageView = (ImageView) findViewById(R.id.movie_poster);
        TextView synopsisTextView = (TextView) findViewById(R.id.synopsis_field);
        TextView ratingTextView = (TextView) findViewById(R.id.user_rating);
        TextView releaseDateTextView = (TextView) findViewById(R.id.release_date);

        if (intent.hasExtra("movieParcel")){
            originalTitleTextView.setText(movie.getOriginalTitle());
            Picasso.with(context)
                    .load(movie.getPosterUrl())
                    .into(moviePosterImageView);

            synopsisTextView.setText(movie.getOverview());
            ratingTextView.setText(String.format("%s", movie.getUserRating()));
            releaseDateTextView.setText(movie.getReleaseDate());
        }


    }
}
