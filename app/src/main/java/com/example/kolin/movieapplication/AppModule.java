package com.example.kolin.movieapplication;

import android.content.Context;

import com.example.kolin.movieapplication.data.Repository;
import com.example.kolin.movieapplication.domain.IRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by n.kirilov on 10.06.2016.
 */
@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return context;
    }


}
