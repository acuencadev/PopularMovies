package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovies.http.MoviesAPI;
import com.example.android.popularmovies.http.model.Movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    private final String API_URL = "https://api.themoviedb.org/3/";

    Movie movie;

    private ImageView mPosterImageView;
    private TextView mYearTextView;
    private TextView mStatusTextView;
    private TextView mLengthTextView;
    private TextView mVotesTextView;
    private TextView mGenresTextView;
    private TextView mDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int id = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);

        initializeViews();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoviesAPI api = retrofit.create(MoviesAPI.class);

        api.getMovie(id, BuildConfig.MOVIES_API).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    movie = response.body();

                    DetailActivity.this.setTitle(movie.getTitle());

                    //TODO: Update UI views with Movie info.
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DetailActivity.this,
                        "Couldn't load movie info.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initializeViews() {
        mPosterImageView = findViewById(R.id.activity_detail_poster_imageView);
        mDescriptionTextView = findViewById(R.id.activity_detail_description_textView);
        mGenresTextView = findViewById(R.id.activity_detail_genres_textView);
        mLengthTextView = findViewById(R.id.activity_detail_length_textView);
        mStatusTextView = findViewById(R.id.activity_detail_status_textView);
        mVotesTextView = findViewById(R.id.activity_detail_votes_textView);
        mYearTextView = findViewById(R.id.activity_detail_year_textView);
    }
}
