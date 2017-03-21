package com.dmaila.popularmoviesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {


    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String baseUrl = "http://image.tmdb.org/t/p/";
        final String imageSize = "w185";
        final String posterPath = "/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg";

        String url = baseUrl + imageSize + posterPath;
        Log.d(LOG_TAG, "url = " + url);

        ImageView imageView = (ImageView) findViewById(R.id.banner_imageView);

        Picasso.with(this)
                .setIndicatorsEnabled(true);
        Picasso.with(this)
                .load(url)
                .into(imageView);
    }
}
