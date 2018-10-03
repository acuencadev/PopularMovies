package com.example.android.popularmovies.ui.detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.example.android.popularmovies.databinding.ActivityDetailBinding;
import com.example.android.popularmovies.ui.detail.description.MovieDescriptionFragment;
import com.example.android.popularmovies.ui.detail.reviews.MovieReviewsFragment;
import com.example.android.popularmovies.ui.detail.trailers.MovieTrailersFragment;
import com.example.android.popularmovies.ui.list.MainActivity;
import com.example.android.popularmovies.R;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private int mMovieId;

    ActivityDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        mMovieId = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);

        mBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_detail);

        setSupportActionBar((android.support.v7.widget.Toolbar) mBinding.activityDetailToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewPager(mBinding.activityDetailViewpager);

        mBinding.activityDetailTabLayout.setupWithViewPager(mBinding.activityDetailViewpager);
        mBinding.activityDetailTabLayout.getTabAt(0).setIcon(R.drawable.description);
        mBinding.activityDetailTabLayout.getTabAt(1).setIcon(R.drawable.movie);
        mBinding.activityDetailTabLayout.getTabAt(2).setIcon(R.drawable.review);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(MovieDescriptionFragment.newInstance(mMovieId), getString(R.string.tab_movies_description));
        adapter.addFragment(MovieTrailersFragment.newInstance(mMovieId), getString(R.string.tab_movies_trailers));
        adapter.addFragment(MovieReviewsFragment.newInstance(mMovieId), getString(R.string.tab_movies_reviews));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
