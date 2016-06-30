package com.example.kolin.movieapplication.presentation.di.components;

import com.example.kolin.movieapplication.presentation.di.PerActivity;
import com.example.kolin.movieapplication.presentation.di.modules.ActivityModule;
import com.example.kolin.movieapplication.presentation.di.modules.FilmModule;
import com.example.kolin.movieapplication.presentation.favoriteFilms.FavoriteFilmFragment;
import com.example.kolin.movieapplication.presentation.films.PopularFilmFragment;

import dagger.Component;

/**
 * Created by kolin on 30.06.2016.
 */
@PerActivity
@Component(
        dependencies = AppComponent.class,
        modules = {
                ActivityModule.class,
                FilmModule.class,
        })

public interface FilmComponent extends ActivityComponent {
    void inject(FavoriteFilmFragment favoriteFilmFragment);
    void inject(PopularFilmFragment popularFilmFragment);
}
