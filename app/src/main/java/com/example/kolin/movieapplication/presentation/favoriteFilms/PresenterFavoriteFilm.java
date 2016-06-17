package com.example.kolin.movieapplication.presentation.favoriteFilms;

import android.content.Context;

import com.example.kolin.movieapplication.App;
import com.example.kolin.movieapplication.domain.Interactor;
import com.example.kolin.movieapplication.domain.InteractorImplement;
import com.example.kolin.movieapplication.domain.ResultFilm;
import com.example.kolin.movieapplication.presentation.Contract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.RealmQuery;
import io.realm.RealmResults;
import rx.functions.Action1;

/**
 * Created by n.kirilov on 17.06.2016.
 */
public class PresenterFavoriteFilm implements Contract.PresenterFavoriteInterface {

    @Inject
    Interactor interactor;

    private Contract.ViewFavorite view;

    public PresenterFavoriteFilm(Contract.ViewFavorite view) {
        App.getComponent().inject(this);
        this.view = view;
    }

    @Override
    public void getFavorite(Context context) {
        final RealmQuery<ResultFilm> query = interactor.getFavorite(context);
        query.findAllAsync().asObservable()
                .subscribe(new Action1<RealmResults<ResultFilm>>() {
                    @Override
                    public void call(RealmResults<ResultFilm> resultFilms) {
                        List<ResultFilm> list = new ArrayList<>();
                        for (ResultFilm r : resultFilms) {
                            list.add(r);
                        }
                        view.showFavoriteFilms(list);
                    }
                });
    }

    @Override
    public void removeFavoriteFilm(Context context, ResultFilm resultFilm) {
        interactor.deleteFavoriteFromRepositoriy(context, resultFilm);
    }
}
