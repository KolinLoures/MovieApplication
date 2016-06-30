package com.example.kolin.movieapplication.presentation.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.kolin.movieapplication.data.ApiInterface;
import com.example.kolin.movieapplication.data.RealmSingleton;
import com.example.kolin.movieapplication.data.Repository;
import com.example.kolin.movieapplication.data.RetrofitSingleton;
import com.example.kolin.movieapplication.domain.IRepository;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by n.kirilov on 10.06.2016.
 */
@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return this.application;
    }

    @Provides
    @Singleton
    public SharedPreferences providesSharedPreferences(Context context){
        return context.getSharedPreferences("SETTING", context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public ApiInterface providesApiInterface(){
        return RetrofitSingleton.getApi();
    }


    @Provides
    @Singleton
    public Realm providesRealm(Context context){
        return RealmSingleton.getInstance(context);
    }

    @Provides
    @Singleton
    public IRepository providesRepository(Repository repository){
        return repository;
    }

    @Provides
    @Singleton
    public Picasso providesPicasso(Context context){
        return Picasso.with(context);
    }

}
