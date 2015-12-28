package com.example.lynx.moviezz.model.get_person_by_id;

import java.io.Serializable;

/**
 * Created by Lynx on 28.12.2015.
 */
public class TaggedImageObject implements Serializable {
    public float pect_ratio;
    public String file_path;
    public int height;
    public int width;
    public String id;
    public String iso_639_1;
    public float vote_average;
    public int vote_count;
    public String image_type;
    public TaggedImageObjectMedia media;
    public String media_type;
}
