package com.example.android.popularmovies.ui.detail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.android.popularmovies.data.database.MoviesDatabase;

public class DetailActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MoviesDatabase mDb;
    private final String mMovieId;

    public DetailActivityViewModelFactory(MoviesDatabase db, String movieId) {
        this.mDb = db;
        this.mMovieId = movieId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DetailActivityViewModel(mDb, mMovieId);
    }
}
