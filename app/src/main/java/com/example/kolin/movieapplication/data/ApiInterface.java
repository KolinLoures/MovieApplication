package com.example.kolin.movieapplication.data;

import com.example.kolin.movieapplication.domain.Films;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kolin on 07.06.2016.
 */
public interface ApiInterface {

    @GET("3/discover/movie/?")
    Call<Films> getPopularFilms(@Query("sort_by") String sort,
                                @Query("api_key") String api);
}
