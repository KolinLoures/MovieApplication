package com.example.kolin.movieapplication.presentation.films;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kolin.movieapplication.App;
import com.example.kolin.movieapplication.R;
import com.example.kolin.movieapplication.domain.ResultFilm;
import com.example.kolin.movieapplication.presentation.Contract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class PopularFilmFragment extends Fragment implements Contract.View {

    private List<ResultFilm> resultFilms;
    private FilmAdapter adapter;

    private SharedPreferences.OnSharedPreferenceChangeListener listenerPreference;

    @Inject
    Contract.PresenterInterface presenter;
    @Inject
    SharedPreferences sharedPreferences;


    public PopularFilmFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        resultFilms = new ArrayList<>();
        adapter = new FilmAdapter(resultFilms, getContext(), presenter);
        loadPreferences(sharedPreferences, "KEY");
        listenerPreference = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                loadPreferences(sharedPreferences, key);
            }
        };

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_popular_film, container, false);

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
        return v;
    }



    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences.registerOnSharedPreferenceChangeListener(listenerPreference);
    }

    @Override
    public void onPause() {
        super.onPause();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listenerPreference);
    }

    @Override
    public void showFilms(List<ResultFilm> list) {
        adapter.clear();
        adapter.addAll(list);
    }

    public void loadPreferences(SharedPreferences sp, String key){
        if (key.equals("KEY")) {
            int selectedMenuItem = sp.getInt(key, 0);
            if (selectedMenuItem == R.id.popular_item) {
                presenter.showAllFilm();
            } else presenter.showAllDateFilms();
        }
    }
}
