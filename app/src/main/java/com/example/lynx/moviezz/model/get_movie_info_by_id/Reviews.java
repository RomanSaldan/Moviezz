package com.example.lynx.moviezz.model.get_movie_info_by_id;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 18.12.2015.
 */
public class Reviews implements Serializable {
    public int page;
    public int total_pages;
    public int total_results;
    public List<Review> results;
}
