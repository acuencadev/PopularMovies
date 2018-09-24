package com.example.android.popularmovies.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(MovieEntry... entries);

    @Query("SELECT * FROM movie")
    MovieEntry getMovies();

    @Query("DELETE FROM movie")
    void deleteOldMovies();

}
