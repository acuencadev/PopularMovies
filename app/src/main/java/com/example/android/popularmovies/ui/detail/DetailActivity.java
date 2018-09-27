package com.example.android.popularmovies.ui.detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovies.BuildConfig;
import com.example.android.popularmovies.databinding.ActivityDetailBinding;
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

    ActivityDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_detail);

        Intent intent = getIntent();
        int id = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoviesAPI api = retrofit.create(MoviesAPI.class);

        mBinding.activityDetailLoadingProgressBar.setVisibility(View.VISIBLE);
        mBinding.activityDetailMainConstraintLayout.setVisibility(View.INVISIBLE);

        api.getMovie(id, BuildConfig.MOVIES_API).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    movie = response.body();
                    populateViews(movie);

                    mBinding.activityDetailLoadingProgressBar.setVisibility(View.INVISIBLE);
                    mBinding.activityDetailMainConstraintLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DetailActivity.this,
                        getString(R.string.error_displaying_movie), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateViews(Movie movie) {
        DetailActivity.this.setTitle(movie.getTitle());
        Picasso.get().load(POSTER_URL + movie.getPosterPath()).into(mBinding.activityDetailPosterImageView);

        mBinding.activityDetailDescriptionTextView.setText(movie.getOverview());
        mBinding.activityDetailStatusTextView.setText(movie.getStatus());

        String releaseYear = movie.getReleaseDate().substring(0, 4);
        mBinding.activityDetailYearTextView.setText(releaseYear);

        mBinding.activityDetailVotesTextView.setText(movie.getVoteString());
        mBinding.activityDetailGenresTextView.setText(movie.getGenresString());

        mBinding.activityDetailLengthTextView.setText(movie.getRuntimeString());
    }
}
