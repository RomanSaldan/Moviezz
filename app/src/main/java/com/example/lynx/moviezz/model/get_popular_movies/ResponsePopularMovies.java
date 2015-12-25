package com.example.lynx.moviezz.model.get_popular_movies;

import com.example.lynx.moviezz.model.find_movie_by_imdb_id.ShortMovieInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 10.12.2015.
 */
public class ResponsePopularMovies implements Serializable {
    public int page;
    public int total_results;
    public int total_pages;
    public List<ShortMovieInfo> results;
}
