package com.example.kolin.movieapplication.presentation;

import android.content.Context;

import com.example.kolin.movieapplication.domain.Films;
import com.example.kolin.movieapplication.domain.Interactor;
import com.example.kolin.movieapplication.domain.InteractorImplement;
import com.example.kolin.movieapplication.domain.ResultFilm;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements Contract.PresenterInterface {

    private Interactor interactor;
    private Contract.View view;
    private RealmResults<ResultFilm> result;


    public Presenter(Contract.View view) {
        this.interactor = new InteractorImplement();
        this.view = view;
    }

    @Override
    public void showAllFilm() {
        interactor.showAllFilms().enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                List<ResultFilm> list = new ArrayList<ResultFilm>(response.body().getResults());
                view.showFilms(list);
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {

            }
        });
    }

    @Override
    public void showAllDateFilms() {
        interactor.showAllDateFilms().enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                List<ResultFilm> list = new ArrayList<ResultFilm>(response.body().getResults());
                view.showFilms(list);
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {

            }
        });
    }

    @Override
    public void addToFavorite(ResultFilm resultFilm, Context context) {
        interactor.addToDataBase(resultFilm, context);
    }

    @Override
    public void getFavorite(Context context) {
        final RealmQuery<ResultFilm> query = interactor.getFavorite(context);
        result = query.findAllAsync();
        result.addChangeListener(new RealmChangeListener<RealmResults<ResultFilm>>() {
            @Override
            public void onChange(RealmResults<ResultFilm> element) {
                if (element.isLoaded()) {
                    List<ResultFilm> list = new ArrayList<>();
                    for (ResultFilm r : element) {
                        list.add(r);
                    }
                    view.showFilms(list);
                }
            }
        });
    }

    @Override
    public void removeListeners() {
        result.removeChangeListeners();
    }

    @Override
    public void removeFavoriteFilm(Context context, ResultFilm resultFilm) {
        interactor.deleteFavoriteFromRepositoriy(context, resultFilm);
    }
}
