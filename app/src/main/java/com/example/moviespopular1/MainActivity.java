package com.example.moviespopular1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.moviespopular1.module.MovieModule;
import com.example.moviespopular1.utilities.JSONUtilities;
import com.example.moviespopular1.utilities.NetworkUtilities;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
// TODO (56) Implement MovieAdapter.MovieAdapterOnClickHandler from the MainActivity

public class MainActivity extends AppCompatActivity
        implements MovieAdapter.MovieAdapterOnClickHandler
{
    // todo (1) Add a private RecyclerView variable called movieRecyclerView
    private RecyclerView movieRecyclerView;
    // todo (2) Add a private movieAdapter variable called MovieAdapter
    private MovieAdapter movieAdapter;

    private List<MovieModule> movies = new ArrayList<>();
    String theQuery = "popular";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // todo (3) Use findViewById to get a reference to the RecyclerView

        movieRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movies);
        movieRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        // todo(4) Set the layoutManager on movieRecyclerView
        movieRecyclerView.setHasFixedSize(true);
        // TODO (58) Pass in this as the ListItemClickListener to the movieAdapter constructor

        movieAdapter = new MovieAdapter(this);
        movieRecyclerView.setAdapter(movieAdapter);
        showMovieData();

    }
    private void showMovieData() {
        String movieQuery = theQuery;
        DoSomeTask task = new DoSomeTask();
        task.execute(movieQuery);    }
    // TODO (59) Override ListMoviePosterClickListener's onMoviePosterClick method

    @Override
    public void onMoviePosterClick(int itemInList) {
        launchDetailsMovie(itemInList);
    }
    void launchDetailsMovie(int position){
        Context context = MainActivity.this;
        Class destinationActivity = DetailsMovie.class;
        Intent startDetailsMoviIntent = new Intent(context, destinationActivity);
        startDetailsMoviIntent.putExtra(Intent.EXTRA_TEXT, position);
        startDetailsMoviIntent.putExtra("title", movies.get(position).getTitle());
        startDetailsMoviIntent.putExtra("vote",movies.get(position).getVote());
        startDetailsMoviIntent.putExtra("release",movies.get(position).getRelease());
        startDetailsMoviIntent.putExtra("overview", movies.get(position).getOverview());
        startDetailsMoviIntent.putExtra("poster", movies.get(position).getPosterbig());
        startActivity(startDetailsMoviIntent);
    }



    class DoSomeTask extends AsyncTask<String, Void, List<MovieModule>> {
        @Override
        protected void onPreExecute(){
            movies.clear();
        }
        @Override
        protected List<MovieModule> doInBackground(String... strings) {
            if (strings.length == 0) {
                return null;
            }
            String queryTobulid = strings[0];
            URL movieRequestUrl = NetworkUtilities.buildUrl(queryTobulid);

            try {
                String jsonMovieResponse = NetworkUtilities.getResponseFromHttpUrl(movieRequestUrl);
                movies.addAll(JSONUtilities.parseMovieJson(jsonMovieResponse));
                return movies;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(List<MovieModule> movieData) {
            movieAdapter.setMoviesData(movieData);
        }

    }
    //get help from sunshine example
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_movie,menu);
        return true;}
    // TODO (6) Return true to display the menu

    // TODO (7) Override onOptionsItemSelected to handle clicks on the refresh button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.popular_movie) {
            theQuery="popular";
            movieAdapter.clearMovieData();
            showMovieData();
            return true;
        }
        if (id == R.id.top_rate) {
            theQuery = "top_rated";
            movieAdapter.clearMovieData();
            showMovieData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
