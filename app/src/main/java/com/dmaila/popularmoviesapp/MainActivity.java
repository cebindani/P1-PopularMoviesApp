package com.dmaila.popularmoviesapp;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import static com.dmaila.popularmoviesapp.BuildConfig.MOVIE_DB_API_KEY;

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


        String apiUrlString = "http://api.themoviedb.org/3/movie/popular?api_key=" + MOVIE_DB_API_KEY;

        NetworkInfo networkInfo = getNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new FetchMoviesData().execute(apiUrlString);
        } else {
            Toast.makeText(MainActivity.this, R.string.message_offline, Toast.LENGTH_SHORT).show();
        }


    }

    private NetworkInfo getNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }
}
