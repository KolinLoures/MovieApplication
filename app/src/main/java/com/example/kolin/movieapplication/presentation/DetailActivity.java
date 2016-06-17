package com.example.kolin.movieapplication.presentation;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kolin.movieapplication.R;
import com.example.kolin.movieapplication.domain.ResultFilm;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView filmDate;
    private TextView filmDescriptions;
    private ImageView filmImage;
    private TextView filmRating;

    private ResultFilm resultFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        filmDescriptions = (TextView) findViewById(R.id.place_detail);
        filmDate = (TextView) findViewById(R.id.place_location);
        filmRating = (TextView) findViewById(R.id.place_rating);

        filmImage = (ImageView) findViewById(R.id.image_image);

        Bundle bundle = getIntent().getExtras();
        resultFilm = bundle.getParcelable("film");


        Picasso.with(getApplicationContext()).load(resultFilm.getUrlPoster())
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .into(filmImage);

        CollapsingToolbarLayout cltb = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        cltb.setTitle(resultFilm.getTitle());

        filmDescriptions.setText(resultFilm.getOverview());
        filmDate.setText(resultFilm.getRelease_date());
        filmRating.setText(String.valueOf(resultFilm.getVote_count()));
    }
}
