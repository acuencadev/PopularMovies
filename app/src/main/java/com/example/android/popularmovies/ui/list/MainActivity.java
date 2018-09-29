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
import com.example.android.popularmovies.ui.settings.SettingsActivity;
import com.example.android.popularmovies.utility.InjectorUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements MoviesAdapter.OnItemClickListener {

    public static final String EXTRA_MESSAGE = "com.example.android.popularmovies.MESSAGEMOVIEID";

    MoviesAdapter mMoviesAdapter;
    MainActivityViewModel mViewModel;

    private int mCurrentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoviesAdapter = new MoviesAdapter(this, this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.activityMainMoviesRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        binding.activityMainMoviesRecyclerView.setAdapter(mMoviesAdapter);
        binding.activityMainMoviesRecyclerView.setHasFixedSize(true);

        MainActivityViewModelFactory factory = InjectorUtils.provideMainActivityViewModelFactory(
                this.getApplicationContext());
        mViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

        mViewModel.getMovies().observeForever(new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movieList) {
                mMoviesAdapter.swapMovies(movieList);
            }
        });

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
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String sortBy = prefs.getString(getString(R.string.pref_sort_key), getString(R.string.pref_sort_default));

        if (sortBy.equals(getString(R.string.pref_sort_default))) {
            mViewModel.pullPopularMovies(mCurrentPage);
        } else {
            mViewModel.pullTopRatedMovies(mCurrentPage);
        }
    }

    @Override
    public void onItemClick(Movie movie) {

    }
}
