package com.example.kolin.movieapplication.presentation.films;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kolin.movieapplication.R;
import com.example.kolin.movieapplication.domain.ResultFilm;
import com.example.kolin.movieapplication.presentation.BaseFragment;
import com.example.kolin.movieapplication.presentation.Contract;
import com.example.kolin.movieapplication.presentation.DetailActivity;
import com.example.kolin.movieapplication.presentation.di.components.FilmComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class PopularFilmFragment extends BaseFragment implements Contract.View {

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

        this.getComponent(FilmComponent.class).inject(this);

        presenter.attachView(this);
        adapter = new FilmAdapter(getContext());


        adapter.setListener(new FilmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                switch (itemView.getId()){
                    case R.id.favorite_button:
                        Snackbar.make(getView(), "Added to favorite!", Snackbar.LENGTH_LONG).show();
                        presenter.addToFavorite(adapter.getItem(position), getContext());
                        break;
                    default:
                        Intent intent = new Intent(getContext(), DetailActivity.class);
                        intent.putExtra("film", adapter.getItem(position));
                        startActivity(intent);
                        break;
                }
            }
        });

        if (savedInstanceState == null) {
            loadPreferences(sharedPreferences, "KEY");
        } else {
            showFilms((List<ResultFilm>) savedInstanceState.get("list"));
        }

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
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        }
        else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }
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
                Log.i("MyLog", "Показывает фильмы популярные");
            } else
            {
                presenter.showAllDateFilms();
                Log.i("MyLog", "Показывает фильмы по дате релиза");
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) adapter.getList());
    }
}
