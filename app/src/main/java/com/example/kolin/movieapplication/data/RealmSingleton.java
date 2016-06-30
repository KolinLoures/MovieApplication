package com.example.kolin.movieapplication.data;

import android.content.Context;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by kolin on 07.06.2016.
 */
@Singleton
public class RealmSingleton {

    private static Realm ourInstance = null;

    public static Realm getInstance(Context context) {
        if (ourInstance == null){
            RealmConfiguration realmConfiguration = new RealmConfiguration
                    .Builder(context)
                    .build();
            Realm.setDefaultConfiguration(realmConfiguration);
            ourInstance = Realm.getInstance(realmConfiguration);
        }
        return ourInstance;
    }

    private RealmSingleton() {
    }
}
