package com.example.lynx.moviezz.model.search_person_by_name;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 09.12.2015.
 */
public class PersonByName implements Serializable {
    public String profile_path;
    public boolean adult;
    public int id;
    public String name;
    public float popularity;
    public List<PersonKnownForMovie> known_for;
}
