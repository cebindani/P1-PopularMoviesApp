package com.dmaila.popularmoviesapp;


import java.util.List;

public interface AsyncResponse {

    void onSuccess(List<Movie> moviesList);
    void onFailure();
}
