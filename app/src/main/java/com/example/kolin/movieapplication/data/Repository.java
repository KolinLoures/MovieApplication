package com.example.kolin.movieapplication.data;

import android.content.Context;

import com.example.kolin.movieapplication.domain.Films;
import com.example.kolin.movieapplication.domain.IRepository;
import com.example.kolin.movieapplication.domain.ResultFilm;

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
    private static Realm realm;
    private ApiInterface api;



    @Override
    public Observable<Films> loadFilms() {
        api = RetrofitSingleton.getApi();
        Observable<Films> call = api.getPopularFilms(SORT_BY_POPULARITY, API_KEY);
        return call;
    }

    @Override
    public Observable<Films> loadDateFilms() {
        api = RetrofitSingleton.getApi();
        Observable<Films> call = api.getPopularFilms(SORT_BY_DATE, API_KEY);
        return call;
    }

    @Override
    public void saveFavoriteFilmToDb(final ResultFilm resultFilm, Context context) {
        realm = RealmSingleton.getInstance(context);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(resultFilm);
            }
        });

    }

    @Override
    public RealmQuery<ResultFilm> getFavoriteFilms(Context context) {
        realm = RealmSingleton.getInstance(context);
        RealmQuery<ResultFilm> result = realm.where(ResultFilm.class);
        return result;
    }

    @Override
    public void deleteFavoriteFromDB(Context context, final ResultFilm resultFilm) {
        realm = RealmSingleton.getInstance(context);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                resultFilm.deleteFromRealm();
            }
        });
    }
}
