package com.example.android.popularmovies.utility;

import android.content.Context;

import com.example.android.popularmovies.AppExecutors;
import com.example.android.popularmovies.data.MoviesRepository;
import com.example.android.popularmovies.data.database.MoviesDatabase;
import com.example.android.popularmovies.data.network.MoviesNetworkDataSource;
import com.example.android.popularmovies.ui.list.MainActivityViewModelFactory;

public class InjectorUtils {

    public static MoviesRepository provideRepository(Context context) {
        MoviesDatabase database = MoviesDatabase.getsInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        MoviesNetworkDataSource networkDataSource = MoviesNetworkDataSource.getInstance(
                context.getApplicationContext(),
                executors);

        return MoviesRepository.getInstance(database.moviesDao(), networkDataSource);
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

}
