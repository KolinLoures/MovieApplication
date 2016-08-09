package com.example.kolin.movieapplication.presentationTest;

import android.content.Context;

import com.example.kolin.movieapplication.domain.Films;
import com.example.kolin.movieapplication.domain.Interactor;
import com.example.kolin.movieapplication.domain.ResultFilm;
import com.example.kolin.movieapplication.presentation.films.Presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.verifyNoMoreInteractions;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by kolin on 21.07.2016.
 */
public class PresenterTest {

    private Presenter presenter;

    @Mock
    private Interactor interactor;
    @Mock
    private Context mockContext;

    private ResultFilm resultFilm;


    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);

        presenter = new Presenter(interactor);
        resultFilm = new ResultFilm();
    }

    @Test
    public void testShowAllDateFilms() {
        TestSubscriber<Films> testSubscriber = new TestSubscriber<>();
        List<Films> list = new ArrayList<>();
        list.add(new Films());
        Observable<Films> observable = Observable.from(list);
        when(interactor.showAllDateFilms()).thenReturn(observable);

        interactor.showAllDateFilms().subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
    }

    @Test
    public void testShowAllFilm() {
        TestSubscriber<Films> testSubscriber = new TestSubscriber<>();
        List<Films> list = new ArrayList<>();
        list.add(new Films());
        Observable<Films> observable = Observable.from(list);
        when(interactor.showAllFilms()).thenReturn(observable);

        interactor.showAllFilms().subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
    }

    @Test
    public void testAddToFavorite() {

        presenter.addToFavorite(resultFilm, mockContext);

        verify(interactor).addToDataBase(resultFilm, mockContext);
        verifyNoMoreInteractions(interactor);
    }




}
