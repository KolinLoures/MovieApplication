package com.example.kolin.movieapplication.domain;

import android.content.Context;

import io.realm.RealmQuery;
import retrofit2.Call;

/**
 * Created by kolin on 07.06.2016.
 */
public interface Interactor {
    Call<Films> showAllFilms();
    Call<Films> showAllDateFilms();
    void addToDataBase(ResultFilm resultFilm, Context context);
    RealmQuery<ResultFilm> getFavorite(Context context);
    void deleteFavoriteFromRepositoriy(Context context, ResultFilm resultFilm);
}
