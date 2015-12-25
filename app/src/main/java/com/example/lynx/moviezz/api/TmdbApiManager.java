package com.example.lynx.moviezz.api;

import com.example.lynx.moviezz.global.Constants;

import retrofit.RestAdapter;

/**
 * Created by Lynx on 09.12.2015.
 */
public class TmdbApiManager {

    private static TmdbApiManager ourInstance = new TmdbApiManager();

    public static TmdbApiManager getInstance() {
        return ourInstance;
    }

    private TmdbApiManager() {
    }

    private RestAdapter retrofitAdapter = new RestAdapter.Builder().setEndpoint(Constants.BASE_URL).build();
    private TmdbApi tmdbApi = retrofitAdapter.create(TmdbApi.class);

    public TmdbApi getTmdbApi() {
        return tmdbApi;
    }

}
