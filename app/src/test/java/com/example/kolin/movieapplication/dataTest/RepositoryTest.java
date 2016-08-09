package com.example.kolin.movieapplication.dataTest;

import android.content.Context;

import com.example.kolin.movieapplication.data.ApiInterface;
import com.example.kolin.movieapplication.data.Repository;
import com.example.kolin.movieapplication.domain.Films;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by kolin on 17.07.2016.
 */
public class RepositoryTest {

    private static final String API_KEY = "f9d35cafe47dc95f1389ae4b3c80b09d";
    private static final String SORT_BY_POPULARITY = "popularity.desc";
    private static final String SORT_BY_DATE = "release_date.desc";

    private Repository mockRepository;

    @Mock
    private Context context;
    @Mock
    private ApiInterface mockApi;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);


        mockRepository = new Repository(mockApi, null);
    }



    @Test
    public void testLoadFilms() {
        Films films = new Films();

        when(mockApi.getPopularFilms(SORT_BY_POPULARITY, API_KEY)).thenReturn(rx.Observable.just(films));

        mockRepository.loadFilms();

        verify(mockApi).getPopularFilms(SORT_BY_POPULARITY, API_KEY);
    }

    @Test
    public void testLoadDateFilms() {
        Films films = new Films();

        when(mockApi.getPopularFilms(SORT_BY_DATE, API_KEY)).thenReturn(rx.Observable.just(films));

        mockRepository.loadDateFilms();

        verify(mockApi).getPopularFilms(SORT_BY_DATE, API_KEY);
    }
}
