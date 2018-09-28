package com.example.android.popularmovies.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(MovieEntry... entries);

    @Query("SELECT * FROM movie WHERE page = :page")
    LiveData<List<MovieEntry>> getMoviesByPage(int page);

    @Query("SELECT * FROM movie")
    LiveData<List<MovieEntry>> getMovies();

    @Query("SELECT * FROM movie WHERE id = :id")
    LiveData<MovieEntry> getMovieById(String id);

    @Query("DELETE FROM movie")
    void deleteOldMovies();

}
