package com.example.kolin.movieapplication.presentation;

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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FavoriteFilmFragment extends Fragment implements Contract.View {


    private List<ResultFilm> listFavorite;
    private FilmAdapter adapter;

    private Contract.PresenterInterface presenter;

    public FavoriteFilmFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new Presenter(this);
        listFavorite = new ArrayList<>();
        adapter = new FilmAdapter(listFavorite, getActivity(), presenter, this);
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
    public void showFilms(List<ResultFilm> list) {
        adapter.clear();
        adapter.addAll(list);
    }
}
