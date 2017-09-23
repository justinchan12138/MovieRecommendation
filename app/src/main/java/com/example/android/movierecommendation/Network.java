package com.example.android.movierecommendation;


import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the weather servers.
 */
public final class Network {


    private static final String MOVIE_URL =
            "https://api.themoviedb.org/3/discover/movie?api_key=8ec38526869ee4cfd0daa37e1c9852e5";

    /* The format we want our API to return */
    private static final String format = "json";
    /* The units we want our API to return */

    final static String SORT_PARAM = "sort_by";

    public static URL buildUrl(String sort) {
        Uri builtUri = Uri.parse(MOVIE_URL).buildUpon()
                .appendQueryParameter(SORT_PARAM, sort)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
