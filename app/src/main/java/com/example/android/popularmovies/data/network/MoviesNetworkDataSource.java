package com.example.android.popularmovies.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.android.popularmovies.AppExecutors;
import com.example.android.popularmovies.data.database.MovieEntry;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesNetworkDataSource {

    private static final Object LOCK = new Object();
    private static MoviesNetworkDataSource sInstance;

    private final Context mContext;
    private final AppExecutors mExecutors;
    private final MoviesAPI mAPI;

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

        mAPI = retrofit.create(MoviesAPI.class);
    }
}
