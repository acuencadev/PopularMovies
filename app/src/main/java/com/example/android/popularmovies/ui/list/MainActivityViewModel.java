package com.example.android.popularmovies.ui.list;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.popularmovies.data.database.MovieEntry;
import com.example.android.popularmovies.data.database.MoviesDatabase;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private LiveData<List<MovieEntry>> movies;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        //Load all movies.
        MoviesDatabase database = MoviesDatabase.getsInstance(this.getApplication());
        movies = database.moviesDao().getMoviesByPage(1);
    }

    public LiveData<List<MovieEntry>> getMovies() {
        return movies;
    }
}
