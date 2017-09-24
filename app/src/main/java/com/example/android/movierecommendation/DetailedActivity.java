package com.example.android.movierecommendation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailedActivity extends AppCompatActivity {
    int positionFromMain;
    String JSON;
    String imagePath;
    String title;
    String overview;
    double rating;
    String releaseDate;
    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        positionFromMain = getIntent().getIntExtra("position", 0);
        JSON = getIntent().getStringExtra("JSON");
        try {
            JSONObject temp = new JSONObject(JSON);
            JSONArray jArray = temp.getJSONArray("results");
            JSONObject specificResult = jArray.getJSONObject(positionFromMain);
            imagePath = "https://image.tmdb.org/t/p/w500"+ specificResult.getString("poster_path");
            title = specificResult.getString("title");
            overview = specificResult.getString("overview");
            rating = specificResult.getInt("vote_average");
            releaseDate = specificResult.getString("release_date");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Picasso.with(imageView.getContext()).load(imagePath).into(imageView);
        textView.setText("Title: " + title + "\n"+ "Rating: " +
                rating + "\n" + "Overview: " + overview + "\n" + "Release Date: " + releaseDate );
    }
}
