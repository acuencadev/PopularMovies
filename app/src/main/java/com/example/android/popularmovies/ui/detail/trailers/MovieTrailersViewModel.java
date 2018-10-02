package com.example.android.popularmovies.ui.detail.trailers;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.popularmovies.data.MoviesRepository;
import com.example.android.popularmovies.data.network.models.Trailer;

import java.util.List;

public class MovieTrailersViewModel extends ViewModel {

    private final MoviesRepository mRepository;

    private LiveData<List<Trailer>> mTrailers;

    public MovieTrailersViewModel(MoviesRepository repository, int movieId) {
        this.mRepository = repository;
        this.mTrailers = this.mRepository.getTrailers(movieId);
    }

    public LiveData<List<Trailer>> getTrailers() {
        return this.mTrailers;
    }
}
