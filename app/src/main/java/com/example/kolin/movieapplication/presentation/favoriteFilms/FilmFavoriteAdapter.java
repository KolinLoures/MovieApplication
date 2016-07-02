package com.example.kolin.movieapplication.presentation.favoriteFilms;

import android.content.Context;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n.kirilov on 17.06.2016.
 */
public class FilmFavoriteAdapter extends RecyclerView.Adapter<FilmFavoriteAdapter.ViewHolder> {

    private List<ResultFilm> list;
    private Context context;
    private Contract.PresenterFavoriteInterface presenter;
    private static OnClickItemDeleteFromDbListener listener;

    public FilmFavoriteAdapter(Context context, Contract.PresenterFavoriteInterface presenter) {
        this.list = new ArrayList<>();
        this.context = context;
        this.presenter = presenter;
    }

    public interface OnClickItemDeleteFromDbListener{
        void onClickDelete(View v, int position);
    }

    public void setListener(OnClickItemDeleteFromDbListener listener){
        FilmFavoriteAdapter.listener = listener;
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

    public ResultFilm getItem(int position){
        return list.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textNameFilm;
        ImageButton deleteBtn;


        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.poster);
            textNameFilm = (TextView) itemView.findViewById(R.id.film_title);
            deleteBtn = (ImageButton) itemView.findViewById(R.id.favorite_button);

            deleteBtn.setImageResource(R.drawable.ic_clear_black_24dp);

            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        listener.onClickDelete(v, getLayoutPosition());
                    }
                }
            };

            itemView.setOnClickListener(onClickListener);
            deleteBtn.setOnClickListener(onClickListener);
        }



    }
}
