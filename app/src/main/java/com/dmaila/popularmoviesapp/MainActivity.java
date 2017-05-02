package com.dmaila.popularmoviesapp;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.dmaila.popularmoviesapp.BuildConfig.MOVIE_DB_API_KEY;

public class MainActivity extends AppCompatActivity implements AsyncResponse {


    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiUrlString = "http://api.themoviedb.org/3/movie/popular?api_key=" + MOVIE_DB_API_KEY;

        NetworkInfo networkInfo = getNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new FetchMoviesData(this).execute(apiUrlString);
        } else {
            Toast.makeText(MainActivity.this, R.string.message_offline, Toast.LENGTH_SHORT).show();
        }


    }

    private NetworkInfo getNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

    @Override
    public void onSuccess(List<Movie> moviesList) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);

        RecyclerView myRecyclerView = (RecyclerView) findViewById(R.id.movies_recyclerview);
        myRecyclerView.setLayoutManager(gridLayoutManager);

        CustomAdapter customAdapter = new CustomAdapter(new ArrayList<Movie>(moviesList), MainActivity.this);
        myRecyclerView.setAdapter(customAdapter);

    }

    @Override
    public void onFailure() {

    }
}
