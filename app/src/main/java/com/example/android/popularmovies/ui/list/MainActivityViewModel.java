package com.example.android.popularmovies.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.example.android.popularmovies.data.MoviesRepository;
import com.example.android.popularmovies.data.models.Movie;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final MediatorLiveData<List<Movie>> mMoviesObserver;
    private final MoviesRepository mRepository;

    public MainActivityViewModel(MoviesRepository repository) {
        mRepository = repository;
        mMoviesObserver = new MediatorLiveData<>();
        mMoviesObserver.setValue(null);
    }

    public LiveData<List<Movie>> getMovies() {
        return mMoviesObserver;
    }

    public void pullTopRatedMovies(int page) {
        LiveData<List<Movie>> movies = mRepository.getTopRatedMovies(page);

        mMoviesObserver.addSource(movies, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movieList) {
                mMoviesObserver.setValue(movieList);
            }
        });
    }

    public void pullPopularMovies(int page) {
        LiveData<List<Movie>> movies = mRepository.getPopularMovies(page);

        mMoviesObserver.addSource(movies, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movieList) {
                mMoviesObserver.setValue(movieList);
            }
        });
    }

    public void pullFavoriteMovies() {
        LiveData<List<Movie>> movies = mRepository.getFavoriteMovies();

        mMoviesObserver.addSource(movies, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movieList) {
                mMoviesObserver.setValue(movieList);
            }
        });
    }
}
