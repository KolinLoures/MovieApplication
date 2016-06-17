package com.example.kolin.movieapplication.presentation.presentationModule;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kolin.movieapplication.data.Repository;
import com.example.kolin.movieapplication.domain.ResultFilm;
import com.example.kolin.movieapplication.presentation.Contract;
import com.example.kolin.movieapplication.presentation.favoriteFilms.FavoriteFilmFragment;
import com.example.kolin.movieapplication.presentation.favoriteFilms.PresenterFavoriteFilm;
import com.example.kolin.movieapplication.presentation.films.FilmAdapter;
import com.example.kolin.movieapplication.presentation.films.PopularFilmFragment;
import com.example.kolin.movieapplication.presentation.films.Presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.http.POST;

/**
 * Created by n.kirilov on 10.06.2016.
 */
@Module
public class PresentationMod {

    @Provides
    public SharedPreferences providesSharedPreferences(Context context){
        return context.getSharedPreferences("SETTING", context.MODE_PRIVATE);
    }

    @Provides @Singleton
    public PopularFilmFragment providesPopularFilmFragment(){
        return new PopularFilmFragment();
    }

    @Provides @Singleton
    public FavoriteFilmFragment providesFavoriteFragment(){
        return new FavoriteFilmFragment();
    }

    @Provides
    public Contract.PresenterInterface providesPresenter(PopularFilmFragment p){
        return new Presenter(p);
    }

    @Provides
    public Contract.PresenterFavoriteInterface providesPresenterFavorite(FavoriteFilmFragment f){
        return new PresenterFavoriteFilm(f);
    }

}
