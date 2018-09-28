package com.example.android.popularmovies.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.popularmovies.data.MoviesRepository;
import com.example.android.popularmovies.data.network.models.Movie;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private LiveData<List<Movie>> mMovies;
    private final MoviesRepository mRepository;

    public MainActivityViewModel(MoviesRepository repository) {
        mRepository = repository;
        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMovies() {
        return mMovies;
    }

    public void pullTopRatedMovies(int page) {
        mMovies = mRepository.getTopRatedMovies(page);
    }

    public void pullPopularMovies(int page) {
        mMovies = mRepository.getPopularMovies(page);
    }
}
