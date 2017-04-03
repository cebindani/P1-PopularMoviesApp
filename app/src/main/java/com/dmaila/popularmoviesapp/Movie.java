package com.dmaila.popularmoviesapp;


import android.net.Uri;

public class Movie {


    private Uri posterPath;
    private Uri backdropPath;
    private long movieId;
    private String originalTitle;
    private String releaseDate;
    private String overview; //synopsis
    private double voteAverage; //user rating

    public Movie(Uri posterPath, Uri backdropPath, long movieId, String originalTitle, String releaseDate, String overview, double voteAverage) {
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.movieId = movieId;
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.voteAverage = voteAverage;
    }

    public Movie() {
    }

    public Uri getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(Uri posterPath) {
        this.posterPath = posterPath;
    }

    public Uri getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(Uri backdropPath) {
        this.backdropPath = backdropPath;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "posterPath=" + posterPath +
                ", backdropPath=" + backdropPath +
                ", movieId=" + movieId +
                ", originalTitle='" + originalTitle + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", overview='" + overview + '\'' +
                ", voteAverage=" + voteAverage +
                '}';
    }
}
