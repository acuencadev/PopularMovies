package com.example.android.popularmovies.ui.detail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.android.popularmovies.data.MoviesRepository;

public class MovieDescriptionViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MoviesRepository mRepository;
    private final int mMovieId;

    public MovieDescriptionViewModelFactory(MoviesRepository repository, int movieId) {
        this.mRepository = repository;
        this.mMovieId = movieId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieDescriptionViewModel(mRepository, mMovieId);
    }
}
