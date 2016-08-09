package com.example.kolin.movieapplication.domainTest;

import android.content.Context;

import com.example.kolin.movieapplication.domain.Films;
import com.example.kolin.movieapplication.domain.IRepository;
import com.example.kolin.movieapplication.domain.InteractorImplement;
import com.example.kolin.movieapplication.domain.ResultFilm;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.realm.RealmQuery;
import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by kolin on 18.07.2016.
 */
public class InteractorTest {

    private InteractorImplement interactorImplement;

    @Mock
    IRepository mockRepository;
    @Mock
    Context mockContext;

    private Films films;
    private ResultFilm resultFilm;
    RealmQuery<ResultFilm> realmQuery;

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);

        interactorImplement = new InteractorImplement(mockRepository);
        films = new Films();
        resultFilm = new ResultFilm();
        realmQuery = null;
    }

    @Test
    public void testShowAllFilms(){
        when(mockRepository.loadFilms()).thenReturn(Observable.just(films));

        interactorImplement.showAllFilms();

        verify(mockRepository).loadFilms();
    }

    @Test
    public void testShowAllDateFilms(){
        when(mockRepository.loadDateFilms()).thenReturn(Observable.just(films));

        interactorImplement.showAllDateFilms();

        verify(mockRepository).loadDateFilms();
        verifyNoMoreInteractions(mockRepository);

    }

    @Test
    public void testAddToDataBase(){
        interactorImplement.addToDataBase(resultFilm, mockContext);

        verify(mockRepository).saveFavoriteFilmToDb(resultFilm, mockContext);
        verifyNoMoreInteractions(mockRepository);
    }

    @Test
    public void testGetFavorite(){
        when(mockRepository.getFavoriteFilms(mockContext)).thenReturn(realmQuery);

        interactorImplement.getFavorite(mockContext);

        verify(mockRepository).getFavoriteFilms(mockContext);
        verifyNoMoreInteractions(mockRepository);
    }

    @Test
    public void testDeleteFavoriteFromRepository(){
        interactorImplement.deleteFavoriteFromRepositoriy(mockContext, resultFilm);

        verify(mockRepository).deleteFavoriteFromDB(mockContext, resultFilm);
        verifyNoMoreInteractions(mockRepository);
    }


}
