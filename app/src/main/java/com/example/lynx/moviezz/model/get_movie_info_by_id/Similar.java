package com.example.lynx.moviezz.model.get_movie_info_by_id;

import com.example.lynx.moviezz.model.find_movie_by_imdb_id.ShortMovieInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 21.12.2015.
 */
public class Similar implements Serializable {
    public int page;
    public int total_pages;
    public int total_results;
    public List<ShortMovieInfo> results;
}
