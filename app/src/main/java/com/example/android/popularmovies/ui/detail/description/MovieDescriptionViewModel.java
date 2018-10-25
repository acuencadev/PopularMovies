package com.example.android.popularmovies.ui.detail.description;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.popularmovies.data.MoviesRepository;
import com.example.android.popularmovies.data.models.Movie;

public class MovieDescriptionViewModel extends ViewModel {

    private final MoviesRepository mRepository;

    private LiveData<Movie> mMovie;
    private LiveData<Movie> mLocalMovie;

    public MovieDescriptionViewModel(MoviesRepository mRepository, int movieId) {
        this.mRepository = mRepository;
        this.mLocalMovie = this.mRepository.getFavoriteMovie(movieId);
        this.mMovie = this.mRepository.getMovie(movieId);
    }

    public LiveData<Movie> getMovie() {
        return this.mMovie;
    }

    public LiveData<Movie> getLocalMovie() {
        return this.mLocalMovie;
    }

    public void addToFavorites() {
        this.mRepository.addToFavorites(mMovie.getValue());
    }

    public void removeFromFavorites() {
        this.mRepository.removeFromFavorites(mMovie.getValue());
    }
}
