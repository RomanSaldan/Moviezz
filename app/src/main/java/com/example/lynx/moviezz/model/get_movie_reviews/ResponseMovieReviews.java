package com.example.lynx.moviezz.model.get_movie_reviews;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 10.12.2015.
 */
public class ResponseMovieReviews implements Serializable {
    public int id;
    public int page;
    public int total_pages;
    public int total_results;
    public List<MovieReview> results;
}
