package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovies.http.MoviesAPI;
import com.example.android.popularmovies.http.model.Movie;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    private final String POSTER_URL = "http://image.tmdb.org/t/p/w185";
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
                    populateViews(movie);
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

    private void populateViews(Movie movie) {
        DetailActivity.this.setTitle(movie.getTitle());
        Picasso.get().load(POSTER_URL + movie.getPosterPath()).into(mPosterImageView);

        mDescriptionTextView.setText(movie.getOverview());
        mStatusTextView.setText(movie.getStatus());

        String releaseYear = movie.getReleaseDate().substring(0, 4);
        mYearTextView.setText(releaseYear);

        mVotesTextView.setText(movie.getVoteString());
        mGenresTextView.setText(movie.getGenresString());

        mLengthTextView.setText(movie.getRuntimeString());
    }
}
