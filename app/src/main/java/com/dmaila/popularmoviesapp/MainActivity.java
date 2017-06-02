package com.dmaila.popularmoviesapp;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncResponse {


    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String DEFAULT_API_URL_PATH = "popular";
    public static final String TOP_RATED_API_URL_PATH = "top_rated";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getMoviesData(DEFAULT_API_URL_PATH);
    }

    private void getMoviesData(String apiPath) {
        NetworkInfo networkInfo = getNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new FetchMoviesData(this).execute(apiPath);
        } else {
            Toast.makeText(MainActivity.this, R.string.message_offline, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.top_rated_menu) {
            getMoviesData(TOP_RATED_API_URL_PATH);
            Toast.makeText(this, "Sorting by top rated movies", Toast.LENGTH_SHORT).show();
        } else {
            getMoviesData(DEFAULT_API_URL_PATH);
            Toast.makeText(this, "Sorting by popular movies", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    private NetworkInfo getNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

    @Override
    public void onSuccess(List<Movie> moviesList) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);

        RecyclerView myRecyclerView = (RecyclerView) findViewById(R.id.movies_recyclerview);
        CustomAdapter customAdapter = new CustomAdapter(new ArrayList<Movie>(moviesList), MainActivity.this);
        myRecyclerView.setAdapter(customAdapter);
        myRecyclerView.setLayoutManager(gridLayoutManager);

    }

    @Override
    public void onFailure() {
        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();

    }
}
