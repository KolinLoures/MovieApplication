package com.example.kolin.movieapplication.presentation.favoriteFilms;

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

public class FavoriteFilmFragment extends Fragment implements Contract.ViewFavorite {


    private List<ResultFilm> listFavorite;
    private FilmFavoriteAdapter adapter;

    @Inject
    Contract.PresenterFavoriteInterface presenter;

    public FavoriteFilmFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        listFavorite = new ArrayList<>();
        adapter = new FilmFavoriteAdapter(listFavorite, getContext(), presenter);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getFavorite(getContext());
    }

    @Override
    public void onStop() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favorite_film, container, false);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.rvFavorite);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
        return v;
    }

    @Override
    public void showFavoriteFilms(List<ResultFilm> list) {
        adapter.clear();
        adapter.addAll(list);
    }
}
