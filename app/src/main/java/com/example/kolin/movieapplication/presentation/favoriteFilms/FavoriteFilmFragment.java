package com.example.kolin.movieapplication.presentation.favoriteFilms;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kolin.movieapplication.R;
import com.example.kolin.movieapplication.domain.ResultFilm;
import com.example.kolin.movieapplication.presentation.BaseFragment;
import com.example.kolin.movieapplication.presentation.Contract;
import com.example.kolin.movieapplication.presentation.DetailActivity;
import com.example.kolin.movieapplication.presentation.di.components.FilmComponent;

import java.util.List;

import javax.inject.Inject;

public class FavoriteFilmFragment extends BaseFragment implements Contract.ViewFavorite {


    private FilmFavoriteAdapter adapter;


    @Inject
    Contract.PresenterFavoriteInterface presenter;
    @Inject
    Context context;

    public FavoriteFilmFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getComponent(FilmComponent.class).inject(this);
        presenter.attachView(this);
        adapter = new FilmFavoriteAdapter(getContext(), presenter);

        setAdapterListener();
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
    public void showFavoriteFilms(List<ResultFilm> list) {
        adapter.clear();
        adapter.addAll(list);
    }

    @Override
    public void setAdapterListener() {
        adapter.setListener(new FilmFavoriteAdapter.OnClickItemDeleteFromDbListener() {
            @Override
            public void onClickDelete(View v, int position) {
                switch (v.getId()){
                    case R.id.favorite_button:
                        presenter.removeFavoriteFilm(context, adapter.getItem(position));
                        break;
                    default:
                        Intent intent = new Intent(getContext(), DetailActivity.class);
                        intent.putExtra("film", adapter.getItem(position));
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
