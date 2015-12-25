package com.example.lynx.moviezz.model.get_popular_actors;

import java.io.Serializable;

/**
 * Created by Lynx on 09.12.2015.
 */
public class PopularActorBestMovie implements Serializable {

    public String poster_path;
    public boolean adult;
    public String overview;
    public String release_date;
    public String original_title;
    public int[] genre_ids;
    public int id;
    public String media_type;
    public String original_language;
    public String title;
    public String backdrop_path;
    public float popularity;
    public int vote_count;
    public boolean video;
    public double vote_average;

}
