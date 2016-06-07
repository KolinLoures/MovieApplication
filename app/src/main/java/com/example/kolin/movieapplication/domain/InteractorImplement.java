package com.example.kolin.movieapplication.domain;

import android.content.Context;

import com.example.kolin.movieapplication.data.Repository;

import io.realm.RealmQuery;
import retrofit2.Call;

/**
 * Created by kolin on 07.06.2016.
 */
public class InteractorImplement implements Interactor {

    private IRepository repository;

    public InteractorImplement() {
        this.repository = new Repository();
    }

    @Override
    public Call<Films> showAllFilms() {
        return repository.loadFilms();
    }

    @Override
    public Call<Films> showAllDateFilms() {
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
