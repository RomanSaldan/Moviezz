package com.example.lynx.moviezz.model.get_person_by_id;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 28.12.2015.
 */
public class PersonTaggedImages implements Serializable {
    public int page;
    public int total_pages;
    public int total_results;
    public List<TaggedImageObject> results;
}
