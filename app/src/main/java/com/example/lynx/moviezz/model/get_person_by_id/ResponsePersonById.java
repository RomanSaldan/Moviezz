package com.example.lynx.moviezz.model.get_person_by_id;

import java.io.Serializable;

/**
 * Created by Lynx on 09.12.2015.
 */
public class ResponsePersonById implements Serializable {

    public boolean adult;
    public String[] also_known_as;
    public String biography;
    public String birthday;
    public String deathday;
    public String homepage;
    public int id;
    public String imdb_id;
    public String name;
    public String place_of_birth;
    public float popularity;
    public String profile_path;

    public MovieCredits movie_credits;
    public PersonImages images;
    public PersonTaggedImages tagged_images;
}
