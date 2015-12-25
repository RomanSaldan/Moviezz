package com.example.lynx.moviezz.model.get_movie_info_by_id;

import com.example.lynx.moviezz.model.get_genres.Genre;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 10.12.2015.
 */
public class ResponseDetailMovieInfo implements Serializable {
    public boolean adult;
    public String backdrop_path;
    public long budget;
    public List<Genre> genres;
    public String homepage;
    public int id;
    public String imdb_id;
    public String original_language;
    public String original_title;
    public String overview;
    public float popularity;
    public String poster_path;
    public List<ProductionCompany> production_companies;
    public List<ProductionCountry> production_countries;
    public String release_date;
    public long revenue;
    public int runtime;
    public List<SpokenLanguage> spoken_languages;
    public String status;
    public String tagline;
    public String title;
    public boolean video;
    public float vote_average;
    public int vote_count;
    public Trailers trailers;
    public MovieReleases releases;
    public Reviews reviews;
    public Similar similar;
    public Casts casts;
    public Images images;
}
