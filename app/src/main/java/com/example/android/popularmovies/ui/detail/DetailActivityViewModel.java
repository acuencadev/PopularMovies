package com.example.android.popularmovies.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.popularmovies.data.database.MovieEntry;
import com.example.android.popularmovies.data.database.MoviesDatabase;

public class DetailActivityViewModel extends ViewModel {

    private LiveData<MovieEntry> movie;

    public DetailActivityViewModel(MoviesDatabase database, String movieId) {
        movie = database.moviesDao().getMovieById(movieId);
    }

    public LiveData<MovieEntry> getMovie() {
        return movie;
    }
}
