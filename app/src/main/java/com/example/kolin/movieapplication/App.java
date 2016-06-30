package com.example.kolin.movieapplication;

import android.app.Application;

import com.example.kolin.movieapplication.presentation.di.components.AppComponent;
import com.example.kolin.movieapplication.presentation.di.components.DaggerAppComponent;
import com.example.kolin.movieapplication.presentation.di.modules.AppModule;


public class App extends Application {

    private static AppComponent component;

    public AppComponent getAppComponent(){
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
