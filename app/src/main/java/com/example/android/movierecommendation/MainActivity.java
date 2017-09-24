package com.example.android.movierecommendation;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String JSON = null;
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(this.getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        if (isOnline()){
            new FetchData().execute("popularity.desc");
        } else {
            Toast.makeText(MainActivity.this, "There is no internet connection",Toast.LENGTH_LONG).show();
        }

    }


    public class FetchData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }
            URL url = Network.buildUrl(params[0]);
            try {
                JSON = Network.getResponseFromHttpUrl(url);
                return JSON;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPostExecute(String weatherData) {
            mAdapter = new MyAdapter(JSON);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setListener(new MyAdapter.Listener() {
                                     @Override
                                     public void onClick(int position) {
                                         Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
                                         intent.putExtra("position",position);
                                         intent.putExtra("JSON", JSON);
                                         startActivity(intent);
                                     }
                                 }
            );

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.menu, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.popularity) {
            if (isOnline()){
                new FetchData().execute("popularity.desc");
            } else {
                Toast.makeText(MainActivity.this, "There is no internet connection",Toast.LENGTH_LONG).show();
            }
            return true;
        }
        if (id == R.id.rating) {
            if (isOnline()){
                new FetchData().execute("popularity.desc");
            } else {
                Toast.makeText(MainActivity.this, "There is no internet connection",Toast.LENGTH_LONG).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }





}



