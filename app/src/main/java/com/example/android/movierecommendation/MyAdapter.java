package com.example.android.movierecommendation;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URL;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String Jsondata;
    JSONObject jObject;
    public static JSONArray results;
    private Listener listener;

    public void setListener (Listener listener) {
        this.listener = listener;
    }

    public static interface Listener {
        public void onClick(int position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
    public CardView mCardView;
    public ImageView myImageView;

        public ViewHolder(CardView v) {
            super(v);
            mCardView = v;
            myImageView = (ImageView) mCardView.findViewById(R.id.image_view);





    }
    }

    public MyAdapter(String JSON) {

        Jsondata = JSON;
        try {
            jObject = new JSONObject(Jsondata);
            results = jObject.getJSONArray("results");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
    ViewHolder vh = new ViewHolder(v);
    return vh;}

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        JSONObject ObjectOfResults;
        String imagePath = null;
        try {
             ObjectOfResults = results.getJSONObject(position);
             imagePath = ObjectOfResults.getString("poster_path");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        CardView tempCardView = holder.mCardView;
        Picasso.with(holder.myImageView.getContext()).load("https://image.tmdb.org/t/p/w500" + imagePath).into(holder.myImageView);
        tempCardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position);}
            }

    });}

    @Override
    public int getItemCount() {
    return results.length();
    }
    }
