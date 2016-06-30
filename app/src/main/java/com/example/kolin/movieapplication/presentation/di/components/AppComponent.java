package com.example.kolin.movieapplication.presentation.di.components;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kolin.movieapplication.domain.IRepository;
import com.example.kolin.movieapplication.presentation.MainActivity;
import com.example.kolin.movieapplication.presentation.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by n.kirilov on 10.06.2016.
 */


@Singleton
@Component(modules =
        {
                AppModule.class,
        })
public interface AppComponent {
    void inject(MainActivity mainActivity);

    Context context();
    IRepository repository();
    SharedPreferences sharedPreferences();
}
