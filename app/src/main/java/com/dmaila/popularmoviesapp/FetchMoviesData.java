package com.dmaila.popularmoviesapp;


import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.dmaila.popularmoviesapp.BuildConfig.MOVIE_DB_API_KEY;

class FetchMoviesData extends AsyncTask<String, String, List<Movie>> {

    private static final String LOG_TAG = FetchMoviesData.class.getSimpleName();

    private String moviesJsonStr = null;
//    private List<Movie> mMovieList;

    @Override
    protected List<Movie> doInBackground(String... strings) {
        try {
            return getMovies(strings[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Movie> moviesList) {
        if (moviesList != null) {

        }
        super.onPostExecute(moviesList);

    }


    private List<Movie> getMovies(String apiUrlString) throws JSONException {
        MovieDBAPIConnection(apiUrlString);
        return (moviesJsonStr != null) ? getMovieDataFromJSON(moviesJsonStr) : null;

    }


    private void MovieDBAPIConnection(String apiUrlString) {

        HttpURLConnection connection = null;
        try {
            URL url = new URL(apiUrlString);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(4000);
            connection.connect();

            int responseCode = connection.getResponseCode();
            Log.d(LOG_TAG, "Response code: " + responseCode);
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }

            InputStream inputStream = connection.getInputStream();
            if (inputStream != null) {
                moviesJsonStr = getStringFromStream(inputStream);
                Log.v(LOG_TAG, "Movie JSON string: " + moviesJsonStr);
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Error: ", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    private String getStringFromStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer buffer = new StringBuffer();
        buffer.append(reader.readLine());
        reader.close();

        return buffer.toString();
    }


    private List<Movie> getMovieDataFromJSON(String moviesJsonStr) throws JSONException {

        if (moviesJsonStr == null) {
            return null;
        }

        List<Movie> moviesList = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(moviesJsonStr);
        JSONArray results = jsonObject.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            Long movieId = results.getJSONObject(i).getLong("id");
            String posterPath = results.getJSONObject(i).getString("poster_path");
            String overview = results.getJSONObject(i).getString("overview");
            String releaseDate = results.getJSONObject(i).getString("release_date");
            String originalTitle = results.getJSONObject(i).getString("original_title");
            String backdropPath = results.getJSONObject(i).getString("backdrop_path");
            Double voteAverage = results.getJSONObject(i).getDouble("vote_average");

            Movie movie = new Movie(
                    Uri.parse(posterPath),
                    Uri.parse(backdropPath),
                    movieId,
                    originalTitle,
                    releaseDate,
                    overview,
                    voteAverage);

            moviesList.add(i, movie);
            Log.d(LOG_TAG, "getMovieDataFromJSON: " + movie.toString());

        }

        return moviesList;
    }


    private URL mountApiUrl(String path) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .encodedAuthority("api.themoviedb.org/3")
                .appendEncodedPath("movie")
                .appendPath(path)
                .appendQueryParameter("api_key", MOVIE_DB_API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(builder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Error to build URL: ", e);
        }
        return url;
    }
}


