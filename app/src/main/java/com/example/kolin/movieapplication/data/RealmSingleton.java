package com.example.kolin.movieapplication.data;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RxObservableFactory;

/**
 * Created by kolin on 07.06.2016.
 */
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
