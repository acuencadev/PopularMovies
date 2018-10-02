package com.example.android.popularmovies.ui.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.databinding.MovieDescriptionFragmentBinding;
import com.example.android.popularmovies.utility.InjectorUtils;

public class MovieDescriptionFragment extends Fragment {

    private static final String MOVIE_ID = "movie_id";

    private int mMovieId;
    private MovieDescriptionViewModel mViewModel;
    private MovieDescriptionFragmentBinding mBinding;

    public static MovieDescriptionFragment newInstance(int movieId) {
        MovieDescriptionFragment fragment = new MovieDescriptionFragment();

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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.movie_description_fragment, container,
                false);
        View view = mBinding.getRoot();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MovieDescriptionViewModelFactory factory = InjectorUtils.provideMovieDescriptionViewModelFactory(
                getActivity().getApplicationContext(), mMovieId);
        mViewModel = ViewModelProviders.of(this, factory).get(MovieDescriptionViewModel.class);
    }

}
