package com.example.kolin.movieapplication.presentation.di.modules;

import com.example.kolin.movieapplication.domain.Interactor;
import com.example.kolin.movieapplication.domain.InteractorImplement;
import com.example.kolin.movieapplication.presentation.Contract;
import com.example.kolin.movieapplication.presentation.di.PerActivity;
import com.example.kolin.movieapplication.presentation.favoriteFilms.PresenterFavoriteFilm;
import com.example.kolin.movieapplication.presentation.films.Presenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kolin on 30.06.2016.
 */
@Module
public class FilmModule {

    public FilmModule() {
    }

    @Provides
    @PerActivity
    Interactor providesInteractor(InteractorImplement interactorImplement){
        return interactorImplement;
    }

    @Provides
    @PerActivity
    Contract.PresenterInterface providesPresenter(Presenter presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    Contract.PresenterFavoriteInterface providesFavoriteInterface(PresenterFavoriteFilm presenter){
        return presenter;
    }
}
