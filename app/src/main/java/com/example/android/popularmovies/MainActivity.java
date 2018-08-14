package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.popularmovies.http.MoviesAPI;
import com.example.android.popularmovies.http.model.Movie;
import com.example.android.popularmovies.http.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.android.popularmovies.MESSAGEMOVIEID";

    private final String API_URL = "https://api.themoviedb.org/3/";

    MoviesAdapter mMoviesAdapter;
    RecyclerView mMoviesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoviesRecyclerView = findViewById(R.id.activity_main_movies_recyclerView);
        mMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoviesAPI api = retrofit.create(MoviesAPI.class);

        api.getMovies(BuildConfig.MOVIES_API).enqueue(new Callback<MoviesResponse>() {
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
                        "Couldn't retrieve movies list. Please try again.",
                        Toast.LENGTH_SHORT).show();
            }
        });
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
