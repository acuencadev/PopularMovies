package com.example.android.popularmovies.ui.detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.popularmovies.databinding.ActivityDetailBinding;
import com.example.android.popularmovies.ui.list.MainActivity;
import com.example.android.popularmovies.R;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int id = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);

        mBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_detail);
    }
}
