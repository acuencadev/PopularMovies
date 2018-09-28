package com.example.android.popularmovies.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.popularmovies.data.MoviesRepository;
import com.example.android.popularmovies.data.network.models.Movie;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final LiveData<List<Movie>> mMovies;
    private final MoviesRepository mRepository;

    public MainActivityViewModel(MoviesRepository repository) {
        mRepository = repository;
        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMovies() {
        return mMovies;
    }

    public LiveData<List<Movie>> getTopRatedMovies(int page) {
        return mRepository.getTopRatedMovies(page);
    }

    public LiveData<List<Movie>> getPopularMovies(int page) {
        return mRepository.getPopularMovies(page);
    }
}
