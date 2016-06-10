package com.example.kolin.movieapplication.presentation.presentationModule;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

/**
 * Created by n.kirilov on 10.06.2016.
 */
@Module
public class PresentationMod {
    @Provides
    public SharedPreferences providesSharedPreferences(Context context){
        return context.getSharedPreferences("SETTING", context.MODE_PRIVATE);
    }
}
