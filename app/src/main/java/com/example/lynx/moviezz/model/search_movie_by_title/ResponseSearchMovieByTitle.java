package com.example.lynx.moviezz.model.search_movie_by_title;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 09.12.2015.
 */
public class ResponseSearchMovieByTitle implements Serializable {
    public int page;
    public int total_results;
    public int total_pages;
    public List<MovieByTitle> results;
}
