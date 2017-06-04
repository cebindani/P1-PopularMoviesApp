package com.dmaila.popularmoviesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MovieDetailActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movieParcel");

        CollapsingToolbarLayout collapsingToolbarLayout;
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(movie.getOriginalTitle());


        TextView originalTitleTextView = (TextView) findViewById(R.id.original_title);
        ImageView moviePosterImageView = (ImageView) findViewById(R.id.movie_poster);
        ImageView movieBackdropImageView = (ImageView) findViewById(R.id.movie_backdrop_image);
        TextView synopsisTextView = (TextView) findViewById(R.id.synopsis_field);
        TextView ratingTextView = (TextView) findViewById(R.id.user_rating);
        TextView releaseDateTextView = (TextView) findViewById(R.id.release_date);

        if (intent.hasExtra("movieParcel")) {
            originalTitleTextView.setText(movie.getOriginalTitle());
            Picasso.with(context)
                    .load(movie.getBackdropUrl())
                    .into(movieBackdropImageView);

            Picasso.with(context)
                    .load(movie.getPosterUrl())
                    .into(moviePosterImageView);

            synopsisTextView.setText(movie.getOverview());
            ratingTextView.setText(String.format("%s", movie.getUserRating()));

            SimpleDateFormat stringToDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try {
                Date date = stringToDate.parse(movie.getReleaseDate());
                stringToDate = new SimpleDateFormat("dd/MM/yyyy");
                releaseDateTextView.setText(stringToDate.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }
}