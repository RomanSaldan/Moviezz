package com.example.lynx.moviezz.model.search_movie_by_title;

import java.io.Serializable;

/**
 * Created by Lynx on 09.12.2015.
 */
public class MovieByTitle implements Serializable {

    public String poster_path;
    public boolean adult;
    public String overview;
    public String release_date;
    public int[] genre_ids;
    public int id;
    public String original_title;
    public String original_language;
    public String title;
    public String backdrop_path;
    public float popularity;
    public int vote_count;
    public boolean video;
    public double vote_average;

}
