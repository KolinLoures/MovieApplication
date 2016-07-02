package com.example.kolin.movieapplication.presentation.films;

import android.content.Context;

import com.example.kolin.movieapplication.domain.Films;
import com.example.kolin.movieapplication.domain.Interactor;
import com.example.kolin.movieapplication.domain.ResultFilm;
import com.example.kolin.movieapplication.presentation.Contract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Presenter implements Contract.PresenterInterface {



    Interactor interactor;


    private Contract.View view;
    private List<ResultFilm> listPopular;


    @Inject
    public Presenter(Interactor interactor) {
        this.interactor = interactor;
        listPopular = new ArrayList<>();
    }

    @Override
    public void showAllFilm() {

            Observable<Films> observable = interactor.showAllFilms();
            observable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Films>() {
                        @Override
                        public void call(Films films) {
                            listPopular.clear();
                            listPopular.addAll(films.getResults());
                            view.showFilms(listPopular);
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
                            listPopular.clear();
                            listPopular.addAll(films.getResults());
                            view.showFilms(listPopular);
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

    @Override
    public List<ResultFilm> getList() {
        return listPopular;
    }


}
