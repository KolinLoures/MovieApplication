package com.example.kolin.movieapplication.presentation;

import android.content.Context;

import com.example.kolin.movieapplication.domain.ResultFilm;

import java.util.List;

/**
 * Created by kolin on 07.06.2016.
 */
public interface Contract {

    interface View{
        void showFilms(List<ResultFilm> list);

        void setAdapterListener();

        void setSharedPreferencesListener();
    }

    interface ViewFavorite{
        void showFavoriteFilms(List<ResultFilm> list);

        void setAdapterListener();
    }

    interface PresenterInterface{

        void showAllFilm();

        void showAllDateFilms();

        void addToFavorite(ResultFilm resultFilm, Context context);

        void attachView(Contract.View view);

        List<ResultFilm> getList();

    }

    interface PresenterFavoriteInterface{

        void getFavorite(Context context);

        void removeFavoriteFilm(Context context, ResultFilm resultFilm);

        void attachView(Contract.ViewFavorite view);

    }
}
