package com.dmaila.popularmoviesapp;


import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.dmaila.popularmoviesapp.BuildConfig.MOVIE_DB_API_KEY;

class FetchMoviesData extends AsyncTask<String, String, String> {

    private static final String LOG_TAG = FetchMoviesData.class.getSimpleName();

    private String moviesJsonStr = null;

    @Override
    protected String doInBackground(String... strings) {
        return MovieDBAPIConnection(strings[0]);
    }

    @Override
    protected void onPostExecute(String moviesString) {
        Log.d(LOG_TAG, "onPostExecute: " + moviesString);

    }

    private String MovieDBAPIConnection(String apiUrlString) {

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
                Log.v(LOG_TAG, "Movies JSON string: " + moviesJsonStr);
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Error: ", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return moviesJsonStr;
    }

    private String getStringFromStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer buffer = new StringBuffer();
        buffer.append(reader.readLine());
        reader.close();

        return buffer.toString();
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


