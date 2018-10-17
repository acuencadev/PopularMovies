package com.example.android.popularmovies.utility;

import com.example.android.popularmovies.data.database.MovieEntry;
import com.example.android.popularmovies.data.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieToMovieEntryConverter {

    public static List<MovieEntry> moviesToMovieEntries(List<Movie> movies, int page) {
        List<MovieEntry> movieEntries = new ArrayList<>();

        for (Movie movie : movies) {
            movieEntries.add(moviesToMovieEntries(movie, page));
        }

        return movieEntries;
    }

    public static MovieEntry moviesToMovieEntries(Movie movie, int page) {
        MovieEntry movieEntry = new MovieEntry(
                Integer.toString(movie.getId()),
                movie.getPosterPath(),
                movie.getBackdropPath(),
                movie.getReleaseDate().substring(0, 4),
                movie.getRuntime(),
                movie.getTitle(),
                movie.getGenresString(),
                movie.getStatus(),
                movie.getReleaseDate(),
                movie.getVoteCount(),
                page);

        return movieEntry;
    }
}
