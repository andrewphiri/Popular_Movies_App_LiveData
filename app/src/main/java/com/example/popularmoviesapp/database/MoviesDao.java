package com.example.popularmoviesapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.popularmoviesapp.Movies;

import java.util.List;

@Dao
public interface MoviesDao {

    @Query("SELECT * FROM popular_movies")
    LiveData<List<Movies>> loadAllMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movies movies);

    @Delete
    void deleteMovie(Movies movies);

    @Query("SELECT * FROM popular_movies LIMIT 1")
    Movies [] getAnyWord();
}
