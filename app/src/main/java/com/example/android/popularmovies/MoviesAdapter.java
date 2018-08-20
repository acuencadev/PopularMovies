package com.example.android.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.http.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

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

    interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    class MovieListViewHolder extends RecyclerView.ViewHolder {

        ImageView posterImageView;
        TextView titleTextView;
        TextView releaseDateTextView;
        TextView votesTextView;

        public MovieListViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.movie_list_item_title_textView);
            releaseDateTextView = itemView.findViewById(R.id.movie_list_item_release_date_textView);
            votesTextView = itemView.findViewById(R.id.movie_list_item__votes_textView);
            posterImageView = itemView.findViewById(R.id.movie_list_item_poster_imageView);
        }

        public void bind(final Movie movie, final OnItemClickListener listener) {
            titleTextView.setText(movie.getTitle());
            releaseDateTextView.setText(movie.getReleaseDate());
            votesTextView.setText(movie.getVoteString());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie);
                }
            });

            Picasso.get().load(POSTER_URL + movie.getPosterPath()).into(posterImageView);
        }
    }

    class MovieGridViewHolder extends RecyclerView.ViewHolder {

        ImageView posterImageView;

        public MovieGridViewHolder(View itemView) {
            super(itemView);

            posterImageView = itemView.findViewById(R.id.movie_list_item_poster_imageView);
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
