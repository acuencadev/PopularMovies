package com.example.android.popularmovies.ui.detail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.android.popularmovies.data.MoviesRepository;
import com.example.android.popularmovies.data.database.MoviesDatabase;

public class DetailActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MoviesRepository mRepository;
    private final int mMovieId;

    public DetailActivityViewModelFactory(MoviesRepository repository, int movieId) {
        this.mRepository = repository;
        this.mMovieId = movieId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DetailActivityViewModel(mRepository, mMovieId);
    }
}
