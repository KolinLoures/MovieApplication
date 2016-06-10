package com.example.kolin.movieapplication;

import android.content.Context;

import com.example.kolin.movieapplication.data.RealmSingleton;
import com.example.kolin.movieapplication.data.Repository;
import com.example.kolin.movieapplication.data.RetrofitSingleton;
import com.example.kolin.movieapplication.data.dataModule.RepositoryModel;
import com.example.kolin.movieapplication.domain.InteractorImplement;
import com.example.kolin.movieapplication.domain.domainModule.InteractorModul;
import com.example.kolin.movieapplication.presentation.Presenter;
import com.example.kolin.movieapplication.presentation.presentationModule.PresentationMod;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by n.kirilov on 10.06.2016.
 */

@Singleton
@Component(modules =
        {
                RetrofitSingleton.class,
                RealmSingleton.class,
                AppModule.class,
                RepositoryModel.class,
                InteractorModul.class,
                PresentationMod.class
        })
public interface AppComponent {

    Context context();

    void inject(Repository repository);

    void inject(InteractorImplement interactorImplement);

    void inject(Presenter presenter);
}
