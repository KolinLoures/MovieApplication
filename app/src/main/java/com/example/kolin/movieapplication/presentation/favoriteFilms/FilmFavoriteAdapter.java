package com.example.kolin.movieapplication.presentation.favoriteFilms;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kolin.movieapplication.R;
import com.example.kolin.movieapplication.domain.ResultFilm;
import com.example.kolin.movieapplication.presentation.Contract;
import com.example.kolin.movieapplication.presentation.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by n.kirilov on 17.06.2016.
 */
public class FilmFavoriteAdapter extends RecyclerView.Adapter<FilmFavoriteAdapter.ViewHolder> {

    private List<ResultFilm> list;
    private Context context;
    private Contract.PresenterFavoriteInterface presenter;

    public FilmFavoriteAdapter(List<ResultFilm> list, Context context, Contract.PresenterFavoriteInterface presenter) {
        this.list = list;
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public FilmFavoriteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResultFilm resultFilm = list.get(position);
        holder.textNameFilm.setText(resultFilm.getTitle());
        Picasso.with(context)
                .load(resultFilm.getUrlPoster())
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .fit()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void addAll(List<ResultFilm> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textNameFilm;
        ImageButton deleteBtn;


        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.poster);
            textNameFilm = (TextView) itemView.findViewById(R.id.film_title);
            deleteBtn = (ImageButton) itemView.findViewById(R.id.favorite_button);

            deleteBtn.setImageResource(R.drawable.ic_clear_black_24dp);

            imageView.setOnClickListener(this);
            textNameFilm.setOnClickListener(this);
            deleteBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.poster:
                    startDetailActivity(v.getContext());
                    break;
                case R.id.film_title:
                    startDetailActivity(v.getContext());
                    break;
                case R.id.favorite_button:
                    Snackbar.make(v, "Deleted from favorite!", Snackbar.LENGTH_LONG).show();
                    presenter.removeFavoriteFilm(v.getContext(), list.get(getAdapterPosition()));
                    break;
            }
        }

        private void startDetailActivity (Context context){
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("film", list.get(getAdapterPosition()));
            context.startActivity(intent);
        }
    }
}
