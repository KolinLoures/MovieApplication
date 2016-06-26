package com.example.kolin.movieapplication.presentation.presentationModule;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kolin.movieapplication.presentation.Contract;
import com.example.kolin.movieapplication.presentation.favoriteFilms.FavoriteFilmFragment;
import com.example.kolin.movieapplication.presentation.favoriteFilms.PresenterFavoriteFilm;
import com.example.kolin.movieapplication.presentation.films.PopularFilmFragment;
import com.example.kolin.movieapplication.presentation.films.Presenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by n.kirilov on 10.06.2016.
 */
@Module
public class presentationMod {

    @Provides
    @Singleton
    public SharedPreferences providesSharedPreferences(Context context){
        return context.getSharedPreferences("SETTING", context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public PopularFilmFragment providesPopularFilmFragment(){
        return new PopularFilmFragment();
    }

    @Provides
    @Singleton
    public FavoriteFilmFragment providesFavoriteFragment(){
        return new FavoriteFilmFragment();
    }

    @Provides
    @Singleton
    public Contract.PresenterInterface providesPresenter(){
        return new Presenter();
    }

    @Provides
    @Singleton
    public Contract.PresenterFavoriteInterface providesPresenterFavorite(){
        return new PresenterFavoriteFilm();
    }

}
