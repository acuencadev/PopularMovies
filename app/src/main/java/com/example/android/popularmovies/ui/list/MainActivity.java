package com.example.android.popularmovies.ui.list;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.network.models.Movie;
import com.example.android.popularmovies.databinding.ActivityMainBinding;
import com.example.android.popularmovies.ui.detail.DetailActivity;
import com.example.android.popularmovies.ui.settings.SettingsActivity;
import com.example.android.popularmovies.utility.InjectorUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements MoviesAdapter.OnItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String EXTRA_MESSAGE = "com.example.android.popularmovies.MESSAGEMOVIEID";

    private static final String CURRENT_PAGE = "current_page";

    MoviesAdapter mMoviesAdapter;
    MainActivityViewModel mViewModel;

    private int mCurrentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentPage = savedInstanceState.getInt(CURRENT_PAGE);
        }

        mMoviesAdapter = new MoviesAdapter(this, this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);

        binding.activityMainMoviesRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        binding.activityMainMoviesRecyclerView.setAdapter(mMoviesAdapter);
        binding.activityMainMoviesRecyclerView.setHasFixedSize(true);

        setSupportActionBar((android.support.v7.widget.Toolbar)binding.activityMainToolBar);

        setTitle(getString(R.string.app_name));

        observeMovieData();

        swapMoviesInView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_PAGE, mCurrentPage);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        swapMoviesInView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_preferences) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void swapMoviesInView() {
        String sortBy = PreferenceManager.getDefaultSharedPreferences(this)
                .getString(getString(R.string.pref_sort_key), getString(R.string.pref_sort_default));

        if (sortBy.equals(getString(R.string.pref_item_most_popular_key))) {
            mViewModel.pullPopularMovies(mCurrentPage);
        } else if (sortBy.equals(getString(R.string.pref_item_top_rated_key))){
            mViewModel.pullTopRatedMovies(mCurrentPage);
        } else {
            //TODO: Pull favorite movies only.
        }
    }

    private void observeMovieData() {
        MainActivityViewModelFactory factory = InjectorUtils.provideMainActivityViewModelFactory(
                this.getApplicationContext());
        mViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

        mViewModel.getMovies().observeForever(new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movieList) {
                mMoviesAdapter.swapMovies(movieList);
            }
        });
    }

    @Override
    public void onItemClick(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_MESSAGE, movie.getId());

        startActivity(intent);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_sort_key))) {
            swapMoviesInView();
        }
    }
}
