package com.example.lynx.moviezz.model.get_movie_info_by_id;

import com.example.lynx.moviezz.model.get_movie_images_by_id.BaseImage;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 22.12.2015.
 */
public class Images implements Serializable {
    public List<BaseImage> backdrops;
    public List<BaseImage> posters;
}
