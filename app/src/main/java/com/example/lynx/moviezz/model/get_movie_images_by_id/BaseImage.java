package com.example.lynx.moviezz.model.get_movie_images_by_id;

import java.io.Serializable;

/**
 * Created by Lynx on 10.12.2015.
 */
public class BaseImage implements Serializable {
    public float aspect_ratio;
    public String file_path;
    public int height;
    public String iso_639_1;
    public float vote_average;
    public int vote_count;
    public int width;
}
