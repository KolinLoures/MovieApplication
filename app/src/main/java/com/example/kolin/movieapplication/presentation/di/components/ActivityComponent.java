package com.example.kolin.movieapplication.presentation.di.components;

import android.app.Activity;

import com.example.kolin.movieapplication.presentation.di.PerActivity;
import com.example.kolin.movieapplication.presentation.di.modules.ActivityModule;

import dagger.Component;

/**
 * Created by kolin on 29.06.2016.
 */
@PerActivity
@Component(
        dependencies = AppComponent.class,
        modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();
}
