package com.example.lynx.moviezz.model.search_collection_by_name;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 10.12.2015.
 */
public class ResponseCollectionByName implements Serializable {
    public int page;
    public int total_pages;
    public int total_results;
    public List<CollectionSearchResult> results;
}
