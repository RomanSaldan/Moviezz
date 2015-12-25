package com.example.lynx.moviezz.model.search_person_by_name;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 09.12.2015.
 */
public class ResponseSearchPersonByName implements Serializable {
    public int page;
    public List<PersonByName> results;
    public int total_results;
    public int total_pages;
}
