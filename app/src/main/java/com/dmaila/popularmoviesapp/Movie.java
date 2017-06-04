package com.dmaila.popularmoviesapp;


import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable{


    private String posterPath;
    private String posterUrl;
    private String backdropPath;
    private String backdropUrl;
    private long movieId;
    private String originalTitle;
    private String releaseDate;
    private String overview; //synopsis
    private double userRating;
    public static final String BASE_URL = "image.tmdb.org/t/p";
    public static final String IMG_SIZE = "w342";

    public Movie(String posterPath, String backdropPath, long movieId, String originalTitle, String releaseDate, String overview, double userRating) {
        this.posterPath = posterPath;
        this.posterUrl = generateImageURL(posterPath);
        this.backdropPath = backdropPath;
        this.backdropUrl = generateImageURL(backdropPath);
        this.movieId = movieId;
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.userRating = userRating;
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

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
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
                ", userRating=" + userRating +
                '}';
    }

    private String generateImageURL(String imagePath) {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .encodedAuthority(BASE_URL)
                .path(IMG_SIZE)
                .appendEncodedPath(imagePath)
                .build();
        return builder.toString();

    }

    protected Movie(Parcel in) {
        originalTitle = in.readString();
        posterUrl = in.readString();
        backdropUrl = in.readString();
        overview = in.readString();
        userRating = in.readDouble();
        releaseDate = in.readString();
        movieId = in.readLong();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(originalTitle);
        out.writeString(posterUrl);
        out.writeString(backdropUrl);
        out.writeString(overview);
        out.writeDouble(userRating);
        out.writeString(releaseDate);
        out.writeLong(movieId);

    }
}
