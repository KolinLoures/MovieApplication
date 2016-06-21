package com.example.kolin.movieapplication.presentation.films;

import android.content.Context;

import com.example.kolin.movieapplication.App;
import com.example.kolin.movieapplication.domain.Films;
import com.example.kolin.movieapplication.domain.Interactor;
import com.example.kolin.movieapplication.domain.InteractorImplement;
import com.example.kolin.movieapplication.domain.ResultFilm;
import com.example.kolin.movieapplication.presentation.Contract;

import java.lang.ref.WeakReference;
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


    private WeakReference<Contract.View> viewWeakReference;


    public Presenter() {
        App.getComponent().inject(this);
    }

    @Override
    public void showAllFilm() {
        if (isViewAttached()) {
            Observable<Films> observable = interactor.showAllFilms();
            observable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Films>() {
                        @Override
                        public void call(Films films) {
                            getView().showFilms(films.getResults());
                        }
                    });
        }
    }

    @Override
    public void showAllDateFilms() {
        if (isViewAttached()) {
            Observable<Films> observable = interactor.showAllDateFilms();
            observable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Films>() {
                        @Override
                        public void call(Films films) {
                            getView().showFilms(films.getResults());
                        }
                    });
        }
    }

    @Override
    public void addToFavorite(ResultFilm resultFilm, Context context) {
        interactor.addToDataBase(resultFilm, context);
    }


    @Override
    public void attachView(Contract.View view) {
        this.viewWeakReference = new WeakReference<Contract.View>(view);
    }

    @Override
    public void detachView() {
        if (viewWeakReference != null){
            viewWeakReference.clear();
            viewWeakReference = null;
        }
    }

    @Override
    public boolean isViewAttached() {
        return viewWeakReference != null && viewWeakReference.get() != null;
    }

    @Override
    public Contract.View getView() {
        return viewWeakReference == null ? null : viewWeakReference.get();
    }


}
