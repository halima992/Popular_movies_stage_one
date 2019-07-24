package com.example.moviespopular1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsMovie extends AppCompatActivity {
    TextView movieTitle;
    TextView movieRate;
    TextView movieOverview;
    TextView movieDateRelease;
    ImageView moviePoster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);
        movieTitle = (TextView)findViewById(R.id.tv_title);
        String title = getIntent().getStringExtra("title");
        movieTitle.setText(title);
        //---------------------------------------------------
        moviePoster = (ImageView) findViewById(R.id.image_iv);
        String Poster=getIntent().getStringExtra("poster");
        Picasso.get()
                .load(Poster)
                .into(moviePoster);
        //-----------------------------------------------------
        movieRate = (TextView)findViewById(R.id.tv_vote);
        String vote = getIntent().getStringExtra("vote");
        movieRate.setText(vote);
        //-----------------------------------------------------
        movieDateRelease = (TextView)findViewById(R.id.tv_date_release);
        String release = getIntent().getStringExtra("release");
        movieDateRelease.setText(release);
        //----------------------------------------------------------
        movieOverview = (TextView)findViewById(R.id.tv_synopsis);
        String overview = getIntent().getStringExtra("overview");
        movieOverview.setText(overview);





    }
}
