package com.example.android.popularmovies.data.network;

import com.example.android.popularmovies.data.network.models.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesApi {

    @GET("movie/{id}")
    Call<Movie> getMovie(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/{id}/videos")
    Call<TrailersResponse> getMovieTrailers(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("/movie/{id}/reviews")
    Call<ReviewsResponse> getMovieReviews(@Path("id") int id, @Query("api_key") String apiKey, @Query("page") int page);
}
