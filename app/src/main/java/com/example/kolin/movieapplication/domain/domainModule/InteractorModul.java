package com.example.kolin.movieapplication.domain.domainModule;

import com.example.kolin.movieapplication.domain.Interactor;
import com.example.kolin.movieapplication.domain.InteractorImplement;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by n.kirilov on 10.06.2016.
 */
@Module
public class InteractorModul {

    @Singleton
    @Provides
    public Interactor providesInteractor(){
        return new InteractorImplement();
    }
}
