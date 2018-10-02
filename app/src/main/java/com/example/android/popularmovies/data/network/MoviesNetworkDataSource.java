package com.example.android.popularmovies.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.android.popularmovies.AppExecutors;
import com.example.android.popularmovies.BuildConfig;
import com.example.android.popularmovies.data.network.models.Movie;
import com.example.android.popularmovies.data.network.models.Trailer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesNetworkDataSource {

    private static final Object LOCK = new Object();
    private static MoviesNetworkDataSource sInstance;

    private final Context mContext;
    private final AppExecutors mExecutors;
    private final MoviesApi mAPI;

    private final String API_URL = "https://api.themoviedb.org/3/";

    public synchronized static MoviesNetworkDataSource getInstance(Context context,
                                                                   AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new MoviesNetworkDataSource(context, executors);
                }
            }
        }

        return sInstance;
    }

    private MoviesNetworkDataSource(Context context, AppExecutors executors) {
        mContext = context;
        mExecutors = executors;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mAPI = retrofit.create(MoviesApi.class);
    }

    public LiveData<List<Movie>> getPopularMovies(int page) {
        final MutableLiveData<List<Movie>> data = new MutableLiveData<>();

        mAPI.getPopularMovies(BuildConfig.MOVIES_API, page).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                data.setValue(response.body().getMovies());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });

        return data;
    }

    public LiveData<List<Movie>> getTopRatedMovies(int page) {
        final MutableLiveData<List<Movie>> data = new MutableLiveData<>();

        mAPI.getTopRatedMovies(BuildConfig.MOVIES_API, page).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                data.setValue(response.body().getMovies());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });

        return data;
    }

    public LiveData<Movie> getMovie(int id) {
        final MutableLiveData<Movie> data = new MutableLiveData<>();

        mAPI.getMovie(id, BuildConfig.MOVIES_API).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });

        return data;
    }

    public LiveData<List<Trailer>> getTrailers(int id) {
        final MutableLiveData<List<Trailer>> data = new MutableLiveData<>();

        mAPI.getMovieTrailers(id, BuildConfig.MOVIES_API).enqueue(new Callback<TrailersResponse>() {
            @Override
            public void onResponse(Call<TrailersResponse> call, Response<TrailersResponse> response) {
                data.setValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<TrailersResponse> call, Throwable t) {

            }
        });

        return data;
    }

}
