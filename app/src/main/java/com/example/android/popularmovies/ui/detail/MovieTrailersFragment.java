package com.example.android.popularmovies.ui.detail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.network.models.Trailer;
import com.example.android.popularmovies.databinding.MovieTrailersFragmentBinding;
import com.example.android.popularmovies.utility.InjectorUtils;

import java.util.List;

public class MovieTrailersFragment extends Fragment
        implements TrailersAdapter.OnItemClickListener {

    private static final String MOVIE_ID = "movie_id";

    private int mMovieId;
    private MovieTrailersViewModel mViewModel;
    private TrailersAdapter mTrailersAdapter;
    private MovieTrailersFragmentBinding mBinding;

    public static MovieTrailersFragment newInstance(int movieId) {
        MovieTrailersFragment fragment = new MovieTrailersFragment();

        Bundle args = new Bundle();
        args.putInt(MOVIE_ID, movieId);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovieId = getArguments().getInt(MOVIE_ID);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.movie_trailers_fragment, container, false);
        View view = mBinding.getRoot();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTrailersAdapter = new TrailersAdapter(getActivity(), this);

        mBinding.movieReviewsFragmentTrailersRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity().getApplicationContext()));
        mBinding.movieReviewsFragmentTrailersRecyclerView.setAdapter(mTrailersAdapter);
        mBinding.movieReviewsFragmentTrailersRecyclerView.setHasFixedSize(true);

        MovieTrailersViewModelFactory factory = InjectorUtils.provideMovieTrailersViewModelFactory(
                getActivity().getApplicationContext(), mMovieId);

        mViewModel = ViewModelProviders.of(this, factory).get(MovieTrailersViewModel.class);

        mViewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(@Nullable List<Trailer> trailers) {
                mTrailersAdapter.swapTrailers(trailers);
            }
        });
    }

    @Override
    public void onItemClick(Trailer trailer) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailer.getYoutubeUrl()));

        startActivity(intent);
    }
}
