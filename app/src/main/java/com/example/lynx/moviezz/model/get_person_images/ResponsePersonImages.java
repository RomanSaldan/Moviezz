package com.example.lynx.moviezz.model.get_person_images;

import com.example.lynx.moviezz.model.get_movie_images_by_id.BaseImage;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 10.12.2015.
 */
public class ResponsePersonImages implements Serializable {

    public int id;
    public List<BaseImage> profiles;

}
