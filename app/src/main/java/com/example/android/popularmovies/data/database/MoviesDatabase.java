package com.example.android.popularmovies.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = { MovieEntry.class }, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class MoviesDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "movies";

    private static final Object LOCK = new Object();
    private static volatile MoviesDatabase sInstance;

    public abstract MoviesDao moviesDao();

    public static MoviesDatabase getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            MoviesDatabase.class,
                            MoviesDatabase.DATABASE_NAME).build();
                }
            }
        }

        return sInstance;
    }
}
