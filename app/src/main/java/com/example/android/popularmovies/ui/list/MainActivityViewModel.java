package com.example.android.popularmovies.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.popularmovies.data.MoviesRepository;
import com.example.android.popularmovies.data.database.MovieEntry;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final LiveData<List<MovieEntry>> mMovies;
    private final MoviesRepository mRepository;
    private final int mPage;

    public MainActivityViewModel(MoviesRepository repository, int page) {
        mRepository = repository;
        mMovies = new MutableLiveData<>();
        mPage = page;
    }

    public LiveData<List<MovieEntry>> getMovies() {
        return mMovies;
    }

    public LiveData<List<MovieEntry>> getTopRatedMovies(int page) {
        return mRepository.getTopRatedMovies(page);
    }

    public LiveData<List<MovieEntry>> getPopularMovies(int page) {
        return mRepository.getPopularMovies(page);
    }
}
