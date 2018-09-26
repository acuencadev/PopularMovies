package com.example.android.popularmovies.ui.list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.android.popularmovies.data.MoviesRepository;

public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MoviesRepository mRepository;
    private final int mPage;

    public MainActivityViewModelFactory(MoviesRepository repository, int page) {
        this.mRepository = repository;
        this.mPage = page;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(mRepository, mPage);
    }
}
