package com.example.android.popularmovies.ui.detail.reviews;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.popularmovies.data.MoviesRepository;
import com.example.android.popularmovies.data.models.Review;

import java.util.List;

public class MovieReviewsViewModel extends ViewModel {

    private final MoviesRepository mRepository;

    private LiveData<List<Review>> mReviews;

    public MovieReviewsViewModel(MoviesRepository repository, int movieId, int page) {
        this.mRepository = repository;
        this.mReviews = this.mRepository.getReviews(movieId, page);
    }

    public LiveData<List<Review>> getReviews() {
        return this.mReviews;
    }
}
