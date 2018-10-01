package com.example.android.popularmovies.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.popularmovies.data.MoviesRepository;
import com.example.android.popularmovies.data.network.models.Movie;
import com.example.android.popularmovies.data.network.models.Trailer;

import java.util.List;

public class DetailActivityViewModel extends ViewModel {

    private LiveData<Movie> movie;
    private LiveData<List<Trailer>> trailers;
    private final MoviesRepository repository;

    public DetailActivityViewModel(MoviesRepository repository, int movieId) {
        this.repository = repository;
        this.movie = repository.getMovie(movieId);
        this.trailers = repository.getTrailers(movieId);
    }

    public LiveData<Movie> getMovie() {
        return this.movie;
    }


    public LiveData<List<Trailer>> getTrailers() {
        return this.trailers;
    }
}
