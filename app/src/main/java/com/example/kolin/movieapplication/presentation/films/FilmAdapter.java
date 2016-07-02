package com.example.kolin.movieapplication.presentation.films;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kolin.movieapplication.R;
import com.example.kolin.movieapplication.domain.ResultFilm;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> implements Parcelable {
    private List<ResultFilm> list;
    private Context context;

    private static OnItemClickListener listener;

    public FilmAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }



    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }

    public static void setListener(OnItemClickListener listener) {
        FilmAdapter.listener = listener;
    }

    protected FilmAdapter(Parcel in) {
        list = in.createTypedArrayList(ResultFilm.CREATOR);
    }

    public static final Creator<FilmAdapter> CREATOR = new Creator<FilmAdapter>() {
        @Override
        public FilmAdapter createFromParcel(Parcel in) {
            return new FilmAdapter(in);
        }

        @Override
        public FilmAdapter[] newArray(int size) {
            return new FilmAdapter[size];
        }
    };

    @Override
    public FilmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmAdapter.ViewHolder holder, int position) {
        ResultFilm resultFilm = list.get(position);
        holder.textNameFilm.setText(resultFilm.getTitle());
        Picasso.with(context).load(resultFilm.getUrlPoster())
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

    public ResultFilm getItem(int position){
        return list.get(position);
    }

    public List<ResultFilm> getList() {
        return list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(list);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textNameFilm;
        ImageButton favoriteBtn;


        public ViewHolder(final View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.poster);
            textNameFilm = (TextView) itemView.findViewById(R.id.film_title);
            favoriteBtn = (ImageButton) itemView.findViewById(R.id.favorite_button);


            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        listener.onItemClick(v, getLayoutPosition());
                    }
                }
            };
            itemView.setOnClickListener(onClickListener);
            favoriteBtn.setOnClickListener(onClickListener);
        }

    }
}
