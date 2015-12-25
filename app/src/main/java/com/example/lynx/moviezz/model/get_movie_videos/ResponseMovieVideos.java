package com.example.lynx.moviezz.model.get_movie_videos;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 10.12.2015.
 */
public class ResponseMovieVideos implements Serializable {

    public int id;
    public int page;
    public int total_pages;
    public int total_results;
    public List<MovieVideo> results;

}
