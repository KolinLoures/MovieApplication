package com.example.kolin.movieapplication.domain;

import android.content.Context;

import io.realm.RealmQuery;
import retrofit2.Call;

/**
 * Created by kolin on 07.06.2016.
 */
public interface IRepository {

    Call<Films> loadFilms();
    Call<Films> loadDateFilms();

    void saveFavoriteFilmToDb(ResultFilm resultFilm, Context context);
    RealmQuery<ResultFilm> getFavoriteFilms(Context context);
    void deleteFavoriteFromDB(Context context, ResultFilm resultFilm);
}
