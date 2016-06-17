package com.example.kolin.movieapplication.presentation;

import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kolin.movieapplication.App;
import com.example.kolin.movieapplication.R;
import com.example.kolin.movieapplication.presentation.favoriteFilms.FavoriteFilmFragment;
import com.example.kolin.movieapplication.presentation.films.PopularFilmFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {


    @Inject
    SharedPreferences sharedPreferences;


    private Adapter adapter;
    private ViewPager viewPager;
    private MenuItem itemPopularity, itemDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getComponent().inject(this);
        viewPager = (ViewPager) findViewById(R.id.viewP);

        setupAdapter();

        ActionBar actionBar = getSupportActionBar();
        if (getSupportActionBar() != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_navigation, menu);
        itemDate = (MenuItem) menu.findItem(R.id.date_item);
        itemPopularity = (MenuItem) menu.findItem(R.id.popular_item);
        int selectedMenuItem = sharedPreferences.getInt("KEY", 0);
        if (selectedMenuItem == R.id.popular_item){
            itemPopularity.setChecked(true);
        } else {
            itemDate.setChecked(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.date_item:
                item.setChecked(true);
                savePreferences(R.id.date_item);
                Log.i("MENU_ITEM", "Add date_item to settings");
                break;
            case R.id.popular_item:
                item.setChecked(true);
                savePreferences(R.id.popular_item);
                Log.i("MENU_ITEM", "Add popular_item to settings");
                break;
        }
        return true;
    }

    public void savePreferences(int id){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("KEY");
        editor.putInt("KEY", id);
        editor.commit();
    }

    public void setupAdapter(){
        adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new PopularFilmFragment(), "Films");
        adapter.addFragment(new FavoriteFilmFragment(), "Favorite");
        viewPager.setAdapter(adapter);
    }






    public static class Adapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();


        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title){
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }

    }


}
