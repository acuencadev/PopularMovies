package com.example.android.popularmovies.ui.list;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.network.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieGridViewHolder> {

    private final String POSTER_URL = "http://image.tmdb.org/t/p/w185";

    private List<Movie> movies;
    private OnItemClickListener listener;

    public MoviesAdapter(List<Movie> movies, OnItemClickListener listener) {
        this.movies = movies;
        this.listener = listener;
    }

    @Override
    public MovieGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_grid_item, parent, false);
        return new MovieGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieGridViewHolder holder, int position) {
        holder.bind(movies.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void swapMovies(final List<Movie> movieList) {
        if (movies == null) {
            movies = movieList;
            notifyDataSetChanged();
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return movies.size();
                }

                @Override
                public int getNewListSize() {
                    return movieList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return movies.get(oldItemPosition).getId() == movieList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Movie oldMovie = movies.get(oldItemPosition);
                    Movie newMovie = movieList.get(newItemPosition);

                    return oldMovie.getId() == newMovie.getId()
                            && oldMovie.getVoteCount() == newMovie.getVoteCount()
                            && oldMovie.getVoteAverage() == newMovie.getVoteAverage();
                }
            });

            movies = movieList;
            result.dispatchUpdatesTo(this);
        }
    }

    interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    class MovieGridViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_grid_item_poster_imageView)
        ImageView posterImageView;

        public MovieGridViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(final Movie movie, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie);
                }
            });

            Picasso.get().load(POSTER_URL + movie.getPosterPath()).into(posterImageView);
        }
    }

}
