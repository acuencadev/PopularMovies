package com.example.android.popularmovies.data;

import android.arch.lifecycle.LiveData;

import com.example.android.popularmovies.AppExecutors;
import com.example.android.popularmovies.data.database.MovieEntry;
import com.example.android.popularmovies.data.database.MoviesDao;
import com.example.android.popularmovies.data.network.MoviesNetworkDataSource;

import java.util.List;

public class MoviesRepository {

    private static final Object LOCK = new Object();
    private static MoviesRepository sInstance;

    private final MoviesDao mMoviesDao;
    private final AppExecutors mExecutors;
    private final MoviesNetworkDataSource mMoviesNetworkDataSource;

    public synchronized static MoviesRepository getInstance(MoviesDao moviesDao,
                                                            MoviesNetworkDataSource moviesNetworkDataSource,
                                                            AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new MoviesRepository(moviesDao, moviesNetworkDataSource, executors);
                }
            }
        }

        return sInstance;
    }

    private MoviesRepository(MoviesDao moviesDao, MoviesNetworkDataSource moviesNetworkDataSource,
                             AppExecutors executors) {
        this.mMoviesDao = moviesDao;
        this.mExecutors = executors;
        this.mMoviesNetworkDataSource = moviesNetworkDataSource;
    }

    public LiveData<List<MovieEntry>> getTopRatedMovies(int page) {
        //TODO: Implement getTopRatedMovies() logic
        return null;
    }

    public LiveData<List<MovieEntry>> getPopularMovies(int page) {
        //TODO: Implement getPopularMovies() logic
        return null;
    }
}
