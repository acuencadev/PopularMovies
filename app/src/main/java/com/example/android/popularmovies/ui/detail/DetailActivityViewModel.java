package com.example.android.popularmovies.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.popularmovies.data.MoviesRepository;
import com.example.android.popularmovies.data.database.MovieEntry;
import com.example.android.popularmovies.data.database.MoviesDatabase;
import com.example.android.popularmovies.data.network.models.Movie;

public class DetailActivityViewModel extends ViewModel {

    private LiveData<Movie> movie;
    private final MoviesRepository repository;

    public DetailActivityViewModel(MoviesRepository repository, int movieId) {
        this.repository = repository;
        this.movie = repository.getMovie(movieId);
    }

    public LiveData<Movie> getMovie() {
        return this.movie;
    }
}
