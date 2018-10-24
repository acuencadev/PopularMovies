package com.example.android.popularmovies.ui.detail.description;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.models.Movie;
import com.example.android.popularmovies.databinding.MovieDescriptionFragmentBinding;
import com.example.android.popularmovies.utility.InjectorUtils;
import com.squareup.picasso.Picasso;

public class MovieDescriptionFragment extends Fragment {

    private static final String MOVIE_ID = "movie_id";
    private static final String NETWORK_DATA = "network_data";


    private final String POSTER_URL = "http://image.tmdb.org/t/p/w185";

    private int mMovieId;
    private boolean mNetworkData;
    private boolean mIsFavorite;
    private MovieDescriptionViewModel mViewModel;
    private MovieDescriptionFragmentBinding mBinding;

    public static MovieDescriptionFragment newInstance(int movieId, boolean networkData) {
        MovieDescriptionFragment fragment = new MovieDescriptionFragment();

        Bundle args = new Bundle();
        args.putInt(MOVIE_ID, movieId);
        args.putBoolean(NETWORK_DATA, networkData);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovieId = getArguments().getInt(MOVIE_ID);
        mNetworkData = getArguments().getBoolean(NETWORK_DATA);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.movie_description_fragment, container,
                false);
        View view = mBinding.getRoot();

        mBinding.movieDescriptionFragmentFavoriteFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Handle favorite onClick
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mNetworkData) {
            mIsFavorite = false;
        } else {
            mIsFavorite = true;
        }

        toggleFavoriteIcon(mIsFavorite);

        observeMovieData(mMovieId);
    }

    private void toggleFavoriteIcon(boolean isFavorite) {
        int resourceIcon;

        if (isFavorite) {
            resourceIcon = R.drawable.baseline_favorite_border;
        } else {
            resourceIcon = R.drawable.baseline_favorite_white;
        }

        mBinding.movieDescriptionFragmentFavoriteFloatingActionButton.setImageResource(resourceIcon);
    }

    private void observeMovieData(int id) {
        MovieDescriptionViewModelFactory factory = InjectorUtils.provideMovieDescriptionViewModelFactory(
                getActivity().getApplicationContext(), id);
        mViewModel = ViewModelProviders.of(this, factory).get(MovieDescriptionViewModel.class);

        mViewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie movie) {
                populateViews(movie);
            }
        });
    }

    private void populateViews(Movie movie) {
        getActivity().setTitle(movie.getTitle());

        mBinding.movieDescriptionFragmentDescriptionTextView.setText(movie.getOverview());
        mBinding.movieDescriptionFragmentStatusTextView.setText(movie.getStatus());

        String releaseYear = movie.getReleaseDate().substring(0, 4);
        mBinding.movieDescriptionFragmentYearTextView.setText(releaseYear);

        mBinding.movieDescriptionFragmentVotesTextView.setText(movie.getVoteString());
        mBinding.movieDescriptionFragmentGenresTextView.setText(movie.getGenresString());
        mBinding.movieDescriptionFragmentLengthTextView.setText(movie.getRuntimeString());

        Picasso.get()
                .load(POSTER_URL + movie.getPosterPath())
                .into(mBinding.movieDescriptionFragmentPosterImageView);

        Picasso.get()
                .load(POSTER_URL + movie.getBackdropPath())
                .into(mBinding.movieDescriptionFragmentBackdropImageView);
    }
}
