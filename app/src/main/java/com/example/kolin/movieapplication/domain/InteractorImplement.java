package com.example.kolin.movieapplication.domain;

import android.content.Context;

import com.example.kolin.movieapplication.App;
import com.example.kolin.movieapplication.data.Repository;

import javax.inject.Inject;

import io.realm.RealmQuery;
import retrofit2.Call;
import rx.Observable;

/**
 * Created by kolin on 07.06.2016.
 */
public class InteractorImplement implements Interactor {

    @Inject
    IRepository repository;

    public InteractorImplement() {
        App.getComponent().inject(this);
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
