package com.example.android.popularmovies.ui.detail.reviews;

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
import com.example.android.popularmovies.data.models.Review;
import com.example.android.popularmovies.databinding.MovieReviewsFragmentBinding;
import com.example.android.popularmovies.utility.InjectorUtils;

import java.util.List;

public class MovieReviewsFragment extends Fragment
        implements ReviewsAdapter.OnItemClickListener {

    private static final String MOVIE_ID = "movie_id";

    private int mMovieId;
    private int mCurrentPage = 1;
    private MovieReviewsViewModel mViewModel;
    private ReviewsAdapter mReviewsdapter;
    private MovieReviewsFragmentBinding mBinding;

    public static MovieReviewsFragment newInstance(int movieId) {
        MovieReviewsFragment fragment = new MovieReviewsFragment();

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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.movie_reviews_fragment, container,
                false);
        View view = mBinding.getRoot();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mReviewsdapter = new ReviewsAdapter(getActivity(), this);

        mBinding.movieReviewsFragmentReviewsRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity().getApplicationContext()));
        mBinding.movieReviewsFragmentReviewsRecyclerView.setAdapter(mReviewsdapter);
        mBinding.movieReviewsFragmentReviewsRecyclerView.setHasFixedSize(true);

        MovieReviewsViewModelFactory factory = InjectorUtils.provideMovieReviewsViewModelFactory(
                getActivity().getApplicationContext(), mMovieId, mCurrentPage);

        mViewModel = ViewModelProviders.of(this, factory).get(MovieReviewsViewModel.class);

        mViewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(@Nullable List<Review> reviews) {
                mReviewsdapter.swapReviews(reviews);
            }
        });
    }

    @Override
    public void onItemClick(Review review) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(review.getUrl()));

        startActivity(intent);
    }
}
