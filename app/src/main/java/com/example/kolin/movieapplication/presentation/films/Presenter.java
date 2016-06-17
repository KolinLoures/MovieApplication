package com.example.kolin.movieapplication.presentation.films;

import android.content.Context;

import com.example.kolin.movieapplication.App;
import com.example.kolin.movieapplication.domain.Films;
import com.example.kolin.movieapplication.domain.Interactor;
import com.example.kolin.movieapplication.domain.InteractorImplement;
import com.example.kolin.movieapplication.domain.ResultFilm;
import com.example.kolin.movieapplication.presentation.Contract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Presenter implements Contract.PresenterInterface {


    @Inject
    Interactor interactor;
    private Contract.View view;

    public Presenter(Contract.View view) {
        App.getComponent().inject(this);
        this.view = view;
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

}
