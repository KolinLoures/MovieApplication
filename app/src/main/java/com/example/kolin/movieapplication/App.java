package com.example.kolin.movieapplication;

import android.app.Application;

import com.example.kolin.movieapplication.data.Repository;

/**
 * Created by n.kirilov on 10.06.2016.
 */
public class App extends Application {

    private static AppComponent component;

    public static AppComponent getComponent(){
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
