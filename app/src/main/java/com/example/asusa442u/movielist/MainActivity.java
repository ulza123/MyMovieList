package com.example.asusa442u.movielist;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Movie>> {
    private RecyclerView recyclerView;
    private ArrayList<Movie> list;
    Context context;

    private String URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=b4ee38e608924dfb547db6b32d028b1c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toobarMain);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.main_recycle);
        recyclerView.setHasFixedSize(true);
        getSupportLoaderManager().initLoader(0, null, (LoaderManager.LoaderCallbacks<ArrayList<Movie>>)this).forceLoad();
    }

    private void showRecyclerCardView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CardViewMovieAdapter CardViewMovieAdapter = new CardViewMovieAdapter(this);
        CardViewMovieAdapter.setMovies(list);
        recyclerView.setAdapter(CardViewMovieAdapter);
    }

    @NonNull
    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new MovieDataAsync(this, URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Movie>> loader, ArrayList<Movie> movies) {
        this.list = movies;
        Log.d("LIST : ", String.valueOf(this.list.size()));
        if(this.list != null){
            showRecyclerCardView();
        }else{
            setContentView(R.layout.activity_detail_movie);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Movie>> loader) {
        this.list = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater infalter = getMenuInflater();
        infalter.inflate(R.menu.menu_bar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Toast.makeText(this,"Search Gagal",Toast.LENGTH_SHORT);
                return true;
            case R.id.setting:
                String languangeToLoad = "id";
                Locale locale = new Locale(languangeToLoad);
                locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                context.getResources().updateConfiguration(config,context.getResources().getDisplayMetrics());

                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.notification:
                Toast.makeText(this,"Search Gagal",Toast.LENGTH_SHORT);
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }
}
