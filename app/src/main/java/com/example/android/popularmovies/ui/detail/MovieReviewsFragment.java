package com.example.android.popularmovies.ui.detail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.network.models.Review;
import com.example.android.popularmovies.databinding.MovieReviewsFragmentBinding;
import com.example.android.popularmovies.utility.InjectorUtils;

import java.util.List;

public class MovieReviewsFragment extends Fragment
        implements ReviewsAdapter.OnItemClickListener {

    private MovieReviewsViewModel mViewModel;
    private ReviewsAdapter mReviewsdapter;
    private MovieReviewsFragmentBinding mBinding;

    public static MovieReviewsFragment newInstance() {
        return new MovieReviewsFragment();
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
                getActivity().getApplicationContext(), 550, 1);

        mViewModel = ViewModelProviders.of(this, factory).get(MovieReviewsViewModel.class);

        mViewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(@Nullable List<Review> reviews) {
                mReviewsdapter.swapTrailers(reviews);
            }
        });
    }

    @Override
    public void onItemClick(Review review) {
        //TODO: Open review in The Movies DB site.
    }
}
