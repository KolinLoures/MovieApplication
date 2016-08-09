package com.example.kolin.movieapplication.presentationTest;

import android.content.Context;

import com.example.kolin.movieapplication.domain.Interactor;
import com.example.kolin.movieapplication.domain.ResultFilm;
import com.example.kolin.movieapplication.presentation.favoriteFilms.PresenterFavoriteFilm;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by kolin on 09.08.2016.
 */

public class PresenterFavoriteFilmTest {

    private PresenterFavoriteFilm presenterFavoriteFilm;

    @Mock
    Interactor mockInteractor;
    @Mock
    Context mockContext;

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);

        presenterFavoriteFilm = new PresenterFavoriteFilm(mockInteractor);
    }

    @Test
    public void testRemoveFavoriteFilm(){
        ResultFilm resultFilm = new ResultFilm();
        presenterFavoriteFilm.removeFavoriteFilm(mockContext, resultFilm);

        verify(mockInteractor).deleteFavoriteFromRepositoriy(mockContext, resultFilm);
    }
}
