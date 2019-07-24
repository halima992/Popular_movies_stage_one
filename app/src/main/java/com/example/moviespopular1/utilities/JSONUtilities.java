package com.example.moviespopular1.utilities;
import com.example.moviespopular1.module.MovieModule;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class JSONUtilities {
    //final static String USERS_BASE_URL =
    //"https://api.themoviedb.org/3/movie/popular?api_key=16c6e216d7ecb60f9173adda634dadac&language=en-US&page=1";
    public static List<MovieModule> parseMovieJson( String json){
        List<MovieModule> movies = new ArrayList<>();
        try{
            JSONObject movieJson = new JSONObject(json);
            JSONArray movieArray = movieJson.getJSONArray("results");
            for (int i = 0; i < movieArray.length(); i++){
                String title,poster,vote,overview,release;
                title    = movieArray.getJSONObject(i).optString("title");
                poster   = movieArray.getJSONObject(i).optString("poster_path");
                vote     = movieArray.getJSONObject(i).optString("vote_average");
                overview = movieArray.getJSONObject(i).optString("overview");
                release  = movieArray.getJSONObject(i).optString("release_date");
                //setter
                movies.add(new MovieModule(title , poster,vote,overview,release));

            }
            return movies;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

}
