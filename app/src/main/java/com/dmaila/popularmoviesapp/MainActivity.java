package com.dmaila.popularmoviesapp;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncResponse {


    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String DEFAULT_API_URL_PATH = "popular";
    private static final String TOP_RATED_API_URL_PATH = "top_rated";
    Toolbar toolbar;
    private String sortedBy = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getMoviesData(DEFAULT_API_URL_PATH);
    }

    private void getMoviesData(final String apiPath) {
        NetworkInfo networkInfo = getNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            sortedBy = apiPath;
            new FetchMoviesData(this).execute(apiPath);
        } else {
            Snackbar snackbar = Snackbar.make(toolbar, R.string.message_offline, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.retry, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getMoviesData(apiPath);
                        }
                    });
            snackbar.show();

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
        if (item.getItemId() == R.id.sort_menu_item) {
            if (sortedBy != null) {
                switch (sortedBy) {
                    case DEFAULT_API_URL_PATH:
                        getMoviesData(TOP_RATED_API_URL_PATH);
                        break;
                    case TOP_RATED_API_URL_PATH:
                        getMoviesData(DEFAULT_API_URL_PATH);
                        break;
                    default:
                        return true;
                }
            }
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
        CustomAdapter customAdapter = new CustomAdapter(new ArrayList<>(moviesList), MainActivity.this);
        myRecyclerView.setAdapter(customAdapter);
        myRecyclerView.setLayoutManager(gridLayoutManager);

    }

    @Override
    public void onFailure() {
        Intent intent = new Intent(this, ErrorActivity.class);
        startActivity(intent);
    }
}
