package com.example.kolin.movieapplication.presentation.films;

import android.content.Context;

import com.example.kolin.movieapplication.App;
import com.example.kolin.movieapplication.domain.Films;
import com.example.kolin.movieapplication.domain.Interactor;
import com.example.kolin.movieapplication.domain.ResultFilm;
import com.example.kolin.movieapplication.presentation.Contract;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Presenter implements Contract.PresenterInterface {


    @Inject
    Interactor interactor;


    private Contract.View view;


    public Presenter() {
        App.getComponent().inject(this);
    }

    @Override
    public void showAllFilm() {

            Observable<Films> observable = interactor.showAllFilms();
            observable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Films>() {
                        @Override
                        public void call(Films films) {
                            view.showFilms(films.getResults());
                        }
                    });
    }

    @Override
    public void showAllDateFilms() {
            Observable<Films> observable = interactor.showAllDateFilms();
            observable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Films>() {
                        @Override
                        public void call(Films films) {
                            view.showFilms(films.getResults());
                        }
                    });
    }

    @Override
    public void addToFavorite(ResultFilm resultFilm, Context context) {
        interactor.addToDataBase(resultFilm, context);
    }


    @Override
    public void attachView(Contract.View view) {
        this.view = view;
    }


}
