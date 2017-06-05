package com.dmaila.popularmoviesapp;


import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {


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
    private static final String BASE_URL = "image.tmdb.org/t/p";
    private static final String IMG_SIZE = "w342";
    private String posterPath;
    private String posterUrl;
    private String backdropPath;
    private String backdropUrl;
    private long movieId;
    private String originalTitle;
    private String releaseDate;
    private String overview; //synopsis
    private double userRating;

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

    protected Movie(Parcel in) {
        originalTitle = in.readString();
        posterUrl = in.readString();
        backdropUrl = in.readString();
        overview = in.readString();
        userRating = in.readDouble();
        releaseDate = in.readString();
        movieId = in.readLong();
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getBackdropUrl() {
        return backdropUrl;
    }

    public long getMovieId() {
        return movieId;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public double getUserRating() {
        return userRating;
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
