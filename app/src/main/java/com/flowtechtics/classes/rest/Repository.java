package com.flowtechtics.classes.rest;


import android.content.Context;
import android.location.Geocoder;


import com.flowtechtics.classes.models.responses.MoviesResponse;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.scopes.ViewModelScoped;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import timber.log.Timber;

@Singleton
public class Repository {
    private final ApiInterface apiInterface;


    @Inject
    public Repository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }



    public Observable<MoviesResponse> requestMovies(int page) {
        return apiInterface.requestMovies(page);
    }
}

