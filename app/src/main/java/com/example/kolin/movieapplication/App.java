package com.example.kolin.movieapplication;

import android.app.Application;

import com.example.kolin.movieapplication.data.RealmSingleton;
import com.example.kolin.movieapplication.data.RetrofitSingleton;


public class App extends Application {

    private static AppComponent component;

    public static AppComponent getComponent(){
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitSingleton.getInstance();
        RealmSingleton.getInstance(getApplicationContext());
        component = buildComponent();
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
