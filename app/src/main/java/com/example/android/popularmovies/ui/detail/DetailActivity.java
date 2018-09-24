package com.example.android.popularmovies.ui.detail;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovies.BuildConfig;
import com.example.android.popularmovies.ui.list.MainActivity;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.network.MoviesAPI;
import com.example.android.popularmovies.data.network.models.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    private final String POSTER_URL = "http://image.tmdb.org/t/p/w185";
    private final String API_URL = "https://api.themoviedb.org/3/";

    Movie movie;

    @BindView(R.id.activity_detail_poster_imageView)
    ImageView mPosterImageView;

    @BindView(R.id.activity_detail_year_textView)
    TextView mYearTextView;

    @BindView(R.id.activity_detail_status_textView)
    TextView mStatusTextView;

    @BindView(R.id.activity_detail_length_textView)
    TextView mLengthTextView;

    @BindView(R.id.activity_detail_votes_textView)
    TextView mVotesTextView;

    @BindView(R.id.activity_detail_genres_textView)
    TextView mGenresTextView;

    @BindView(R.id.activity_detail_description_textView)
    TextView mDescriptionTextView;

    @BindView(R.id.activity_detail_loading_progressBar)
    ProgressBar mLoadingProgressBar;

    @BindView(R.id.activity_detail_main_constraintLayout)
    ConstraintLayout mMainConstraintLayout;

    @BindString(R.string.error_displaying_movie)
    String mErrorDisplayingMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int id = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);

        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoviesAPI api = retrofit.create(MoviesAPI.class);

        mLoadingProgressBar.setVisibility(View.VISIBLE);
        mMainConstraintLayout.setVisibility(View.INVISIBLE);

        api.getMovie(id, BuildConfig.MOVIES_API).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    movie = response.body();
                    populateViews(movie);

                    mLoadingProgressBar.setVisibility(View.INVISIBLE);
                    mMainConstraintLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DetailActivity.this,
                        mErrorDisplayingMovie, Toast.LENGTH_LONG).show();
            }
        });
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
