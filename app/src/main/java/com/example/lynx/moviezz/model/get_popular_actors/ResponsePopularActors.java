package com.example.lynx.moviezz.model.get_popular_actors;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 09.12.2015.
 */
public class ResponsePopularActors implements Serializable {
    public int page;
    public int total_results;
    public int total_pages;
    public List<PopularActorInfo> results;
}
