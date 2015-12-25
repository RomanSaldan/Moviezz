package com.example.lynx.moviezz.model.get_movie_info_by_id;

import java.io.Serializable;

/**
 * Created by Lynx on 22.12.2015.
 */
public class CrewInfo implements Serializable {
    public String credit_id;
    public String department;
    public int id;
    public String job;
    public String name;
    public String profile_path; //null if absent
}
