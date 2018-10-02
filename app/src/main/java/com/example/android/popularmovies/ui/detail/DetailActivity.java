package com.example.android.popularmovies.ui.detail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.android.popularmovies.data.network.models.Trailer;
import com.example.android.popularmovies.databinding.ActivityDetailBinding;
import com.example.android.popularmovies.ui.list.MainActivity;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.network.models.Movie;
import com.example.android.popularmovies.utility.InjectorUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity
        implements TrailersAdapter.OnItemClickListener {

    private final String POSTER_URL = "http://image.tmdb.org/t/p/w185";

    ActivityDetailBinding mBinding;
    DetailActivityViewModel mViewModel;
    TrailersAdapter mTrailersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_detail);

        mTrailersAdapter = new TrailersAdapter(this, this);

        Intent intent = getIntent();
        int id = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);

        mBinding.activityDetailLoadingProgressBar.setVisibility(View.VISIBLE);
        mBinding.activityDetailMainConstraintLayout.setVisibility(View.INVISIBLE);
        mBinding.activityDetailFavoriteFloatingActionButton.setVisibility(View.INVISIBLE);

        mBinding.activityDetailTrailersRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mBinding.activityDetailTrailersRecyclerView.setAdapter(mTrailersAdapter);
        mBinding.activityDetailTrailersRecyclerView.setHasFixedSize(true);

        observeMovieData(id);
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

    private void observeMovieData(int id) {
        DetailActivityViewModelFactory factory = InjectorUtils.provideDetailActivityViewModelFactory(
                this.getApplicationContext(),
                id);
        mViewModel = ViewModelProviders.of(this, factory).get(DetailActivityViewModel.class);
        mViewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie movie) {
                populateViews(movie);

                mBinding.activityDetailLoadingProgressBar.setVisibility(View.INVISIBLE);
                mBinding.activityDetailMainConstraintLayout.setVisibility(View.VISIBLE);
                mBinding.activityDetailFavoriteFloatingActionButton.setVisibility(View.VISIBLE);
            }
        });
        mViewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(@Nullable List<Trailer> trailers) {
                mTrailersAdapter.swapTrailers(trailers);
            }
        });
    }

    public void toggleFavorite(View view) {
        //TODO: Implement toggle favorite logic
    }

    @Override
    public void onItemClick(Trailer trailer) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailer.getYoutubeUrl()));

        startActivity(intent);
    }
}
