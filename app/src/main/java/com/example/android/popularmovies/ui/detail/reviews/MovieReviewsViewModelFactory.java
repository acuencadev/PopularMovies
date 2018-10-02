package com.example.android.popularmovies.ui.detail.reviews;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.android.popularmovies.data.MoviesRepository;

public class MovieReviewsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MoviesRepository mRepository;
    private final int mMovieId;
    private final int mPage;

    public MovieReviewsViewModelFactory(MoviesRepository mRepository, int movieId, int page) {
        this.mPage = page;
        this.mMovieId = movieId;
        this.mRepository = mRepository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieReviewsViewModel(mRepository, mMovieId, mPage);
    }
}
