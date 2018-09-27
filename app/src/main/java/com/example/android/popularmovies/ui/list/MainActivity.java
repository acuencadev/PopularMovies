package com.example.android.popularmovies.ui.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.ui.settings.SettingsActivity;
import com.example.android.popularmovies.utility.InjectorUtils;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.android.popularmovies.MESSAGEMOVIEID";

    MoviesAdapter mMoviesAdapter;
    MainActivityViewModel mViewModel;

    @BindView(R.id.activity_main_movies_recyclerView)
    RecyclerView mMoviesRecyclerView;

    @BindString(R.string.pref_sort_key) String mPrefSortKey;
    @BindString(R.string.pref_sort_default) String mPrefSortDefault;

    @BindString(R.string.error_displaying_movies) String mErrorDisplayingMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mMoviesRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String sortBy = prefs.getString(mPrefSortKey, mPrefSortDefault);

        MainActivityViewModelFactory factory = InjectorUtils.provideMainActivityViewModelFactory(
                this.getApplicationContext());
        mViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);
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
}
