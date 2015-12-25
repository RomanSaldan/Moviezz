package com.example.lynx.moviezz.model;

import com.example.lynx.moviezz.model.get_movie_images_by_id.BaseImage;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 23.12.2015.
 */
public class SerializedListContainer implements Serializable {
    public List<BaseImage> data;
}
