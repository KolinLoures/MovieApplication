package com.example.kolin.movieapplication.data;


import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
@Singleton
public class RetrofitSingleton {

    private static final String URL = "http://api.themoviedb.org/";
    private static ApiInterface api;
    private static Retrofit retrofit = null;
    private static OkHttpClient client;

    public static Retrofit getInstance(){
        if (retrofit == null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .client(client)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private RetrofitSingleton(){
    }


    public static void initApi(){
        if (api == null){
            synchronized (RetrofitSingleton.class){
                api = getInstance().create(ApiInterface.class);
            }
        }
    }


    public static ApiInterface getApi(){
        initApi();
        return api;
    }
}
