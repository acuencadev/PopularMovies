package com.example.android.popularmovies.utility;

import android.content.Context;

import com.example.android.popularmovies.AppExecutors;
import com.example.android.popularmovies.data.MoviesRepository;
import com.example.android.popularmovies.data.database.MoviesDatabase;
import com.example.android.popularmovies.data.network.MoviesNetworkDataSource;
import com.example.android.popularmovies.ui.detail.description.MovieDescriptionViewModelFactory;
import com.example.android.popularmovies.ui.detail.reviews.MovieReviewsViewModelFactory;
import com.example.android.popularmovies.ui.detail.trailers.MovieTrailersViewModelFactory;
import com.example.android.popularmovies.ui.list.MainActivityViewModelFactory;

public class InjectorUtils {

    public static MoviesRepository provideRepository(Context context) {
        MoviesDatabase database = MoviesDatabase.getsInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        MoviesNetworkDataSource networkDataSource = MoviesNetworkDataSource.getInstance(
                context.getApplicationContext(),
                executors);

        return MoviesRepository.getInstance(database.moviesDao(), networkDataSource, executors);
    }

    public static MoviesNetworkDataSource provideNetworkDataSource(Context context) {
        provideRepository(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();

        return MoviesNetworkDataSource.getInstance(context.getApplicationContext(), executors);
    }

    public static MainActivityViewModelFactory provideMainActivityViewModelFactory(Context context) {
        MoviesRepository repository = provideRepository(context.getApplicationContext());

        return new MainActivityViewModelFactory(repository);
    }

    public static MovieTrailersViewModelFactory provideMovieTrailersViewModelFactory(Context context, int id) {
        MoviesRepository repository = provideRepository(context.getApplicationContext());

        return new MovieTrailersViewModelFactory(repository, id);
    }

    public static MovieReviewsViewModelFactory provideMovieReviewsViewModelFactory(Context context, int id, int page) {
        MoviesRepository repository = provideRepository(context.getApplicationContext());

        return new MovieReviewsViewModelFactory(repository, id, page);
    }

    public static MovieDescriptionViewModelFactory provideMovieDescriptionViewModelFactory(Context context, int id) {
        MoviesRepository repository = provideRepository(context.getApplicationContext());

        return new MovieDescriptionViewModelFactory(repository, id);
    }
}
