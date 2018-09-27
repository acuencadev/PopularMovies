package com.example.android.popularmovies.ui.list;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.popularmovies.BuildConfig;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.database.MovieEntry;
import com.example.android.popularmovies.data.database.MoviesDatabase;
import com.example.android.popularmovies.data.network.MoviesAPI;
import com.example.android.popularmovies.data.network.models.Movie;
import com.example.android.popularmovies.data.network.MoviesResponse;
import com.example.android.popularmovies.ui.detail.DetailActivity;
import com.example.android.popularmovies.ui.settings.SettingsActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.android.popularmovies.MESSAGEMOVIEID";

    private final String API_URL = "https://api.themoviedb.org/3/";

    MoviesAdapter mMoviesAdapter;

    @BindView(R.id.activity_main_movies_recyclerView)
    RecyclerView mMoviesRecyclerView;

    @BindString(R.string.pref_sort_key) String mPrefSortKey;
    @BindString(R.string.pref_sort_default) String mPrefSortDefault;

    @BindString(R.string.error_displaying_movies) String mErrorDisplayingMovies;

    Call<MoviesResponse> moviesResponseCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mMoviesRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String sortBy = prefs.getString(mPrefSortKey, mPrefSortDefault);

        /*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoviesAPI api = retrofit.create(MoviesAPI.class);

        moviesResponseCall = sortBy.equals(mPrefSortDefault) ? api.getPopularMovies(BuildConfig.MOVIES_API, 1) : api.getTopRatedMovies(BuildConfig.MOVIES_API, 1);

        moviesResponseCall.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    mMoviesAdapter = new MoviesAdapter(response.body().getMovies(), new MoviesAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Movie movie) {
                            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                            intent.putExtra(EXTRA_MESSAGE, movie.getId());

                            startActivity(intent);
                        }
                    });
                    mMoviesRecyclerView.setAdapter(mMoviesAdapter);
                    mMoviesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                t.printStackTrace();

                Toast.makeText(
                        MainActivity.this,
                        mErrorDisplayingMovies,
                        Toast.LENGTH_SHORT).show();
            }
        });
        */
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
