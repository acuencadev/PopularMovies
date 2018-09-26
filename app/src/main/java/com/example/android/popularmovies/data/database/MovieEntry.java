package com.example.android.popularmovies.data.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "movie")
public class MovieEntry {

    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "image_path")
    private String imagePath;

    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    private String year;
    private String description;
    private String genres;
    private String status;

    private int length;
    private int votes;
    private int page;


    public MovieEntry(String id, String imagePath, String backdropPath, String year, int length, String description,
                      String genres, String status, int votes, int page) {
        this.id = id;
        this.imagePath = imagePath;
        this.backdropPath = backdropPath;
        this.year = year;
        this.length = length;
        this.description = description;
        this.genres = genres;
        this.status = status;
        this.votes = votes;
        this.page = page;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
