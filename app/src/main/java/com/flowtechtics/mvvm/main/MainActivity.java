package com.flowtechtics.mvvm.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flowtechtics.R;
import com.flowtechtics.classes.adapters.MoviesAdapter;
import com.flowtechtics.classes.dialogs.MovieDetailsDialog;
import com.flowtechtics.classes.models.beans.Movie;
import com.flowtechtics.classes.models.responses.MoviesResponse;
import com.flowtechtics.classes.ui.EndlessRecyclerViewScrollListener;
import com.flowtechtics.databinding.ActivityMainBinding;
import com.flowtechtics.mvvm.$Base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity {


    ActivityMainBinding mBinding;
    private MainViewModel mViewModel;
    @Inject
    MoviesAdapter moviesAdapter;
    @Inject
    MovieDetailsDialog movieDetailsDialog;

    EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


        //ViewModel Setup
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.getOnApiErrorMutableLiveData().observe(this, this::onApiError);
        mViewModel.getOnLoadingMutableLiveData().observe(this, this::onLoading);

        // Observer
        mViewModel.requestMovies(1).observe(this, this::onMoviesResponse);

        //New Instances
        gridLayoutManager = new GridLayoutManager(this,2);
        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mViewModel.requestMovies(page);
            }
        };


        moviesAdapter.setItemClickListener(this::onMovieClick);


        mBinding.rvMovies.setAdapter(moviesAdapter);
        mBinding.rvMovies.setLayoutManager(gridLayoutManager);
        mBinding.rvMovies.addOnScrollListener(endlessRecyclerViewScrollListener);


        mBinding.swipeRefreshLayout.setOnRefreshListener(() -> {
            moviesAdapter.clear();
            endlessRecyclerViewScrollListener.resetState();
            mViewModel.requestMovies(1);
            mBinding.swipeRefreshLayout.setRefreshing(false);
        });

    }


    private void onMoviesResponse(MoviesResponse response) {
        moviesAdapter.setData(response.getResults());
    }

    private void onMovieClick(Movie movie) {
        movieDetailsDialog.setMovie(movie);
        movieDetailsDialog.showDialog();
    }

}