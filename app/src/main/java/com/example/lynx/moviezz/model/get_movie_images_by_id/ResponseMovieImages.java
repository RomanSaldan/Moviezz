package com.example.lynx.moviezz.model.get_movie_images_by_id;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 10.12.2015.
 */
public class ResponseMovieImages implements Serializable {
    public int id;
    public List<BaseImage> backdrops;
    public List<BaseImage> posters;
}
