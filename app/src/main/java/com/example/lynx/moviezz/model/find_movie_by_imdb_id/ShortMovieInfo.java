package com.example.lynx.moviezz.model.find_movie_by_imdb_id;

import java.io.Serializable;

/**
 * Created by Lynx on 10.12.2015.
 */
public class ShortMovieInfo implements Serializable {

    public boolean adult;
    public String backdrop_path;
    public int[] genre_ids;
    public int id;
    public String original_language;
    public String original_title;
    public String overview;
    public String release_date;
    public String poster_path;
    public float popularity;
    public String title;
    public boolean video;
    public double vote_average;
    public int vote_count;

}
