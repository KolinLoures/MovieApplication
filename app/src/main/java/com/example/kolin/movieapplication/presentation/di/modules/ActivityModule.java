package com.example.kolin.movieapplication.presentation.di.modules;


import android.app.Activity;

import com.example.kolin.movieapplication.presentation.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kolin on 30.06.2016.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity(){
        return this.activity;
    }
}
