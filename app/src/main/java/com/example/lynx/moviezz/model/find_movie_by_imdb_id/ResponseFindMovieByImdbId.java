package com.example.lynx.moviezz.model.find_movie_by_imdb_id;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 10.12.2015.
 */
public class ResponseFindMovieByImdbId implements Serializable {
    List<ShortMovieInfo> movie_results;
}
