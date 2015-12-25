package com.example.lynx.moviezz.model.get_collection_by_id;

import com.example.lynx.moviezz.model.find_movie_by_imdb_id.ShortMovieInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 10.12.2015.
 */
public class ResponseCollectionById implements Serializable {
    public int id;
    public String name;
    public String overview;
    public String poster_path;
    public String backdrop_path;
    public List<ShortMovieInfo> parts;
}
