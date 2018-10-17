package com.example.android.popularmovies.data.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movie")
public class Movie {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    @NonNull
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("release_date")
    @Expose
    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @SerializedName("vote_count")
    @Expose
    @ColumnInfo(name = "vote_count")
    private Integer voteCount;

    @SerializedName("runtime")
    @Expose
    private Integer runtime;

    @SerializedName("poster_path")
    @Expose
    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    @Expose
    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    public Movie(Integer id, String title, String overview, String status, String releaseDate,
                 Integer voteCount, Integer runtime, String posterPath, String backdropPath) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.status = status;
        this.releaseDate = releaseDate;
        this.voteCount = voteCount;
        this.runtime = runtime;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
    }

    //Unused fields

    @SerializedName("adult")
    @Expose
    @Ignore
    private Boolean adult;

    @SerializedName("belongs_to_collection")
    @Expose
    @Ignore
    private Object belongsToCollection;

    @SerializedName("budget")
    @Expose
    @Ignore
    private Integer budget;

    @SerializedName("homepage")
    @Expose
    @Ignore
    private String homepage;

    @SerializedName("genres")
    @Expose
    @Ignore
    private List<Genre> genres = null;

    @SerializedName("imdb_id")
    @Expose
    @Ignore
    private String imdbId;

    @SerializedName("original_language")
    @Expose
    @Ignore
    private String originalLanguage;

    @SerializedName("original_title")
    @Expose
    @Ignore
    private String originalTitle;

    @SerializedName("popularity")
    @Expose
    @Ignore
    private Double popularity;

    @SerializedName("production_companies")
    @Expose
    @Ignore
    private List<ProductionCompany> productionCompanies = null;

    @SerializedName("production_countries")
    @Expose
    @Ignore
    private List<ProductionCountry> productionCountries = null;

    @SerializedName("revenue")
    @Expose
    @Ignore
    private Integer revenue;

    @SerializedName("spoken_languages")
    @Expose
    @Ignore
    private List<SpokenLanguage> spokenLanguages = null;

    @SerializedName("tagline")
    @Expose
    @Ignore
    private String tagline;

    @SerializedName("video")
    @Expose
    @Ignore
    private Boolean video;

    @SerializedName("vote_average")
    @Expose
    @Ignore
    private Double voteAverage;

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Object getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(Object belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getGenresString() {
        if (genres == null || genres.size() == 0) {
            return "";
        }

        return TextUtils.join(", ", genres);
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public String getRuntimeString() {
        if (runtime != null) {
            return runtime.toString() + " mins";
        }

        return "Unknown";
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getVoteString() {
        String votesWord = voteCount == 1 ? "vote" : "votes";

        return Double.toString(voteAverage) + "(" + voteCount +  " " + votesWord + ")";
    }

}
