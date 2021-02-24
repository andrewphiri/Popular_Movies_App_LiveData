package com.example.popularmoviesapp;

import android.app.Application;
import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.popularmoviesapp.database.MoviesViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MoviesViewModelTest {

    @Mock
    MoviesViewModel moviesViewModel;

    @Mock
    LiveData<List<Movies>> loadingLiveData;

    @Mock
    Observer<List<Movies>> observer;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void init() throws Exception{

        MockitoAnnotations.initMocks(this);
       // Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        moviesViewModel = spy(new MoviesViewModel(getApplicationContext()));
        loadingLiveData = moviesViewModel.loadAllMovies();
    }

    @Test
    public void Insert() {
       List<Movies> moviesList = spy(ArrayList.class);
        Movies movies = new Movies("wonder","211","Wonder is an imaginary movie",
                "R.image","2020-12-20","8/10", "1225", 1);
       moviesList.add(movies);
//        when(moviesList.get(0).getTitle()).thenReturn(movies.getTitle());
//        //doReturn(moviesViewModel.loadAllMovies()).when(moviesViewModel.loadAllMovies());
        moviesViewModel.insert(movies);
//        System.out.println(moviesList.get(0).getTitle());

        assertNotNull(moviesViewModel.loadAllMovies());
        moviesViewModel.loadAllMovies().observeForever(observer);
        verify(observer).onChanged(moviesList);
        moviesViewModel.loadAllMovies();
        verify(observer).onChanged(moviesList);
    }
}
