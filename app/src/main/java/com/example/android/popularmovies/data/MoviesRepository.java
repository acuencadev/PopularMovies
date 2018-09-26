package com.example.android.popularmovies.data;

import com.example.android.popularmovies.data.database.MoviesDao;
import com.example.android.popularmovies.data.network.MoviesNetworkDataSource;

public class MoviesRepository {

    private static final Object LOCK = new Object();
    private static MoviesRepository sInstance;

    private final MoviesDao mMoviesDao;
    private final MoviesNetworkDataSource mMoviesNetworkDataSource;

    public synchronized static MoviesRepository getInstance(MoviesDao moviesDao,
                                                            MoviesNetworkDataSource moviesNetworkDataSource) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new MoviesRepository(moviesDao, moviesNetworkDataSource);
                }
            }
        }

        return sInstance;
    }

    private MoviesRepository(MoviesDao moviesDao, MoviesNetworkDataSource moviesNetworkDataSource) {
        this.mMoviesDao = moviesDao;
        this.mMoviesNetworkDataSource = moviesNetworkDataSource;
    }
}
