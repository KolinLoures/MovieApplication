package com.example.kolin.movieapplication.presentation.presentationModule;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kolin.movieapplication.data.Repository;
import com.example.kolin.movieapplication.domain.ResultFilm;
import com.example.kolin.movieapplication.presentation.Contract;
import com.example.kolin.movieapplication.presentation.films.FilmAdapter;
import com.example.kolin.movieapplication.presentation.films.PopularFilmFragment;
import com.example.kolin.movieapplication.presentation.films.Presenter;

import java.util.ArrayList;
import java.util.List;

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


}
