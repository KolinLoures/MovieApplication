package com.example.kolin.movieapplication.domain;

import android.content.Context;

import javax.inject.Inject;

import io.realm.RealmQuery;
import rx.Observable;

/**
 * Created by kolin on 07.06.2016.
 */
public class InteractorImplement implements Interactor {


    IRepository repository;

    @Inject
    public InteractorImplement(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Films> showAllFilms() {
        return repository.loadFilms();
    }

    @Override
    public Observable<Films> showAllDateFilms() {
        return repository.loadDateFilms();
        //un useful commit
    }

    @Override
    public void addToDataBase(ResultFilm resultFilm, Context context) {
        repository.saveFavoriteFilmToDb(resultFilm, context);
    }

    @Override
    public RealmQuery<ResultFilm> getFavorite(Context context) {
        return repository.getFavoriteFilms(context);
    }

    @Override
    public void deleteFavoriteFromRepositoriy(Context context, ResultFilm resultFilm) {
        repository.deleteFavoriteFromDB(context, resultFilm);
    }
}
