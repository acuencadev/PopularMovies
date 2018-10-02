package com.example.android.popularmovies.ui.detail.trailers;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.android.popularmovies.data.MoviesRepository;

public class MovieTrailersViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MoviesRepository mRepository;
    private final int mMovieId;


    public MovieTrailersViewModelFactory(MoviesRepository mRepository, int movieId) {
        this.mMovieId = movieId;
        this.mRepository = mRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieTrailersViewModel(mRepository, mMovieId);
    }
}
