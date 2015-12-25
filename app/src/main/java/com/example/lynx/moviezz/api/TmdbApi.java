package com.example.lynx.moviezz.api;

import com.example.lynx.moviezz.global.Constants;
import com.example.lynx.moviezz.model.get_certifications.ResponseCertifications;
import com.example.lynx.moviezz.model.find_movie_by_imdb_id.ResponseFindMovieByImdbId;
import com.example.lynx.moviezz.model.get_collection_by_id.ResponseCollectionById;
import com.example.lynx.moviezz.model.get_collection_images.ResponseCollectionImages;
import com.example.lynx.moviezz.model.search_collection_by_name.ResponseCollectionByName;
import com.example.lynx.moviezz.model.get_genres.ResponseGenres;
import com.example.lynx.moviezz.model.get_movie_images_by_id.ResponseMovieImages;
import com.example.lynx.moviezz.model.get_movie_info_by_id.ResponseDetailMovieInfo;
import com.example.lynx.moviezz.model.get_movie_reviews.ResponseMovieReviews;
import com.example.lynx.moviezz.model.get_movie_videos.ResponseMovieVideos;
import com.example.lynx.moviezz.model.get_now_playing.ResponseNowPlaying;
import com.example.lynx.moviezz.model.get_person_by_id.ResponsePersonById;
import com.example.lynx.moviezz.model.get_person_credits.ResponsePersonCredits;
import com.example.lynx.moviezz.model.get_person_images.ResponsePersonImages;
import com.example.lynx.moviezz.model.get_popular_actors.ResponsePopularActors;
import com.example.lynx.moviezz.model.get_popular_movies.ResponsePopularMovies;
import com.example.lynx.moviezz.model.get_popular_movies_by_genre.ResponsePopularMoviesByGenre;
import com.example.lynx.moviezz.model.get_similar_movies.ResponseSimilarMovies;
import com.example.lynx.moviezz.model.get_top_rated_movies.ResponseTopRatedMovies;
import com.example.lynx.moviezz.model.get_upcoming_movies.ResponseUpcomingMovies;
import com.example.lynx.moviezz.model.search_movie_by_title.ResponseSearchMovieByTitle;
import com.example.lynx.moviezz.model.search_person_by_name.ResponseSearchPersonByName;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Lynx on 09.12.2015.
 */
public interface TmdbApi {

    //region Movie API
    @GET("/search/movie?api_key=" + Constants.API_KEY)
    void searchMovieByTitle(@Query("query") String query, @Query("page") int page, Callback<ResponseSearchMovieByTitle> callback);

    @GET("/movie/{id}?append_to_response=trailers,releases,reviews,similar,casts,images&include_image_language=en,null&api_key=" + Constants.API_KEY)
    void getMovieInfoByID(@Path("id") int id, Callback<ResponseDetailMovieInfo> callback);

    @GET("/find/{imdbID}?external_source=&imdb_id&api_key=" + Constants.API_KEY)
    void getMovieByImdbID(@Path("imdbID") String imdbID, Callback<ResponseFindMovieByImdbId> callback);

    @GET("/movie/{id}/images?include_image_language=en,null&api_key=" + Constants.API_KEY)
    void getMovieImagesByID(@Path("id") int id, Callback<ResponseMovieImages> callback);

    @GET("/movie/{id}/videos?api_key=" + Constants.API_KEY)
    void getMovieVideos(@Path("id") int id, Callback<ResponseMovieVideos> callback);

    @GET("/movie/{id}/reviews?api_key=" + Constants.API_KEY)
    void getMovieReviews(@Path("id") int id, @Query("page") int page, Callback<ResponseMovieReviews> callback);

    @GET("/movie/{id}/similar?append_to_response=popular&api_key=" + Constants.API_KEY)
    void getSimilarMovies(@Path("id") int id, @Query("page") int page, Callback<ResponseSimilarMovies> callback);    // not recommended, but similar :(
    //endregion

    //region Person API
    @GET("/search/person?api_key=" + Constants.API_KEY)
    void searchPersonByName(@Query("query") String query, @Query("page") int page, Callback<ResponseSearchPersonByName> callback);

    @GET("/person/{id}?api_key=" + Constants.API_KEY)
    void getPersonByID(@Path("id") int id, Callback<ResponsePersonById> callback);

    @GET("/person/{id}/movie_credits?api_key=" + Constants.API_KEY)
    void getPersonCredits(@Path("id") int id, Callback<ResponsePersonCredits> callback);

    @GET("/person/{id}/images?api_key=" + Constants.API_KEY)
    void getPersonImages(@Path("id") int id, Callback<ResponsePersonImages> callback);
    //endregion

    //region Collections API
    @GET("/search/collection?api_key=" + Constants.API_KEY)
    void searchCollectionByName(@Query("query") String query, @Query("page") int page, Callback<ResponseCollectionByName> callback);

    @GET("/collection/{id}?api_key=" + Constants.API_KEY)
    void getCollectionByID(@Path("id") int id, Callback<ResponseCollectionById> callback);

    @GET("/collection/{id}/images?include_image_language=en,null&api_key=" + Constants.API_KEY)
    void getCollectionImages(@Path("id") int id, Callback<ResponseCollectionImages> callback);
    //endregion

    //region Calendar API
    @GET("/movie/now_playing?api_key=" + Constants.API_KEY)
    void getNowPlayng(@Query("page") int page, Callback<ResponseNowPlaying> callback);

    @GET("/movie/upcoming?api_key=" + Constants.API_KEY)
    void getUpcomingMovies(@Query("page") int page, Callback<ResponseUpcomingMovies> callback);
    //endregion

    //region Top API
    @GET("/movie/top_rated?api_key=" + Constants.API_KEY)
    void getTopRatedMovies(@Query("page") int page, Callback<ResponseTopRatedMovies> callback);

    @GET("/movie/popular?api_key=" + Constants.API_KEY)
    void getPopularMovies(@Query("page") int page, Callback<ResponsePopularMovies> callback);

    @GET("/genre/{id}/movies?api_key=" + Constants.API_KEY)
    void getPopularMoviesByGenre(@Path("id") int id, @Query("page") int page, Callback<ResponsePopularMoviesByGenre> callback);

    @GET("/person/popular?api_key=" + Constants.API_KEY)
    void getPopularActors(@Query("'page") int page, Callback<ResponsePopularActors> callback);
    //endregion

    //region Other API
    @GET("/certification/movie/list?api_key=" + Constants.API_KEY)
    void getCertifications(Callback<ResponseCertifications> callback);

    @GET("/genre/movie/list?api_key=" + Constants.API_KEY)
    void getListOfGenres(Callback<ResponseGenres> callback);
    //endregion

}
