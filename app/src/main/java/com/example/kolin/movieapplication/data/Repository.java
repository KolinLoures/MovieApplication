package com.example.kolin.movieapplication.data;

import android.content.Context;

import com.example.kolin.movieapplication.App;
import com.example.kolin.movieapplication.domain.Films;
import com.example.kolin.movieapplication.domain.IRepository;
import com.example.kolin.movieapplication.domain.ResultFilm;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmQuery;
import rx.Observable;

/**
 * Created by kolin on 07.06.2016.
 */

public class Repository implements IRepository {

    private static final String API_KEY = "f9d35cafe47dc95f1389ae4b3c80b09d";
    private static final String SORT_BY_POPULARITY = "popularity.desc";
    private static final String SORT_BY_DATE = "release_date.desc";

    @Inject
    Realm realm;
    @Inject
    ApiInterface api;


    public Repository() {
        App.getComponent().inject(this);
    }

    @Override
    public Observable<Films> loadFilms() {
        Observable<Films> call = api.getPopularFilms(SORT_BY_POPULARITY, API_KEY);
        return call;
    }

    @Override
    public Observable<Films> loadDateFilms() {
        Observable<Films> call = api.getPopularFilms(SORT_BY_DATE, API_KEY);
        return call;
    }

    @Override
    public void saveFavoriteFilmToDb(final ResultFilm resultFilm, Context context) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(resultFilm);
            }
        });

    }

    @Override
    public RealmQuery<ResultFilm> getFavoriteFilms(Context context) {
        RealmQuery<ResultFilm> result = realm.where(ResultFilm.class);
        return result;
    }

    @Override
    public void deleteFavoriteFromDB(Context context, final ResultFilm resultFilm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                resultFilm.deleteFromRealm();
            }
        });
    }
}
