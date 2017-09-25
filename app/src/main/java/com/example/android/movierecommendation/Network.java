package com.example.android.movierecommendation;


import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class Network {


    private static final String MOVIE_URL =
            "https://api.themoviedb.org/3/discover/movie?api_key=<<Insert your API Key Here>>";



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
