package com.example.android.popularmovies.ui.detail.description;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.popularmovies.data.MoviesRepository;
import com.example.android.popularmovies.data.models.Movie;

public class MovieDescriptionViewModel extends ViewModel {

    private final MoviesRepository mRepository;

    private LiveData<Movie> mMovie;

    public MovieDescriptionViewModel(MoviesRepository mRepository, int movieId) {
        this.mRepository = mRepository;
        this.mMovie = this.mRepository.getMovie(movieId);
    }

    public LiveData<Movie> getMovie() {
        return this.mMovie;
    }
}
