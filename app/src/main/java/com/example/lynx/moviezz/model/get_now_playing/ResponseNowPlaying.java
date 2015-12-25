package com.example.lynx.moviezz.model.get_now_playing;

import com.example.lynx.moviezz.model.find_movie_by_imdb_id.ShortMovieInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 10.12.2015.
 */
public class ResponseNowPlaying implements Serializable {
    public int page;
    public int total_pages;
    public int total_results;
    public Dates dates;
    public List<ShortMovieInfo> results;
}
