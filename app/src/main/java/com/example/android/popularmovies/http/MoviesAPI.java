package com.example.android.popularmovies.http;

import com.example.android.popularmovies.http.model.Movie;
import com.example.android.popularmovies.http.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesAPI {

    @GET("movie/{id}")
    Call<Movie> getMovie(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("discover/movie")
    Call<MoviesResponse> getMovies(@Query("api_key") String apiKey, @Query("sort_by") String sort);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

}
