package com.example.lynx.moviezz.model.get_popular_actors;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 09.12.2015.
 */
public class PopularActorInfo implements Serializable {
    public String profile_path;
    public boolean adult;
    public int id;
    public String name;
    public float popularity;
    public List<PopularActorBestMovie> known_for;
}
