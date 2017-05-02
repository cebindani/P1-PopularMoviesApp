package com.dmaila.popularmoviesapp;


import android.net.Uri;

public class Movie {


    private String posterPath;
    private String posterUrl;
    private String backdropPath;
    private String backdropUrl;
    private long movieId;
    private String originalTitle;
    private String releaseDate;
    private String overview; //synopsis
    private double voteAverage; //user rating
    public static final String BASE_URL = "image.tmdb.org/t/p";
    public static final String IMG_SIZE = "w342";

    public Movie(String posterPath, String backdropPath, long movieId, String originalTitle, String releaseDate, String overview, double voteAverage) {
        this.posterPath = posterPath;
        this.posterUrl = generateImageURL(posterPath);
        this.backdropPath = backdropPath;
        this.backdropUrl = generateImageURL(backdropPath);
        this.movieId = movieId;
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.voteAverage = voteAverage;
    }

    public Movie() {
    }

    private String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
        setPosterUrl(generateImageURL(posterPath));
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    private void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getBackdropUrl() {
        return backdropUrl;
    }

    private void setBackdropUrl(String backdropUrl) {
        this.backdropUrl = backdropUrl;
        setBackdropUrl(generateImageURL(backdropPath));
    }

    private String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
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

    private String generateImageURL(String imagePath) {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .encodedAuthority(BASE_URL)
                .path(IMG_SIZE)
                .appendEncodedPath(imagePath)
                .build();
        String imageUrl = builder.toString();
        return imageUrl;

    }

}
