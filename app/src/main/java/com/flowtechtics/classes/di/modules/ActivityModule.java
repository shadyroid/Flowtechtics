package com.flowtechtics.classes.di.modules;


import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AlertDialog;

import com.flowtechtics.classes.models.beans.Movie;

import net.bohush.geometricprogressview.GeometricProgressView;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ActivityContext;

@Module
@InstallIn(ActivityComponent.class)
public class ActivityModule {


    @Provides
    public GeometricProgressView providerProgressView(@ActivityContext Context context) {
        return new GeometricProgressView(context);
    }

    @Provides
    public Handler providerHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @Provides
    public AlertDialog providerAlertDialog(@ActivityContext Context context) {
        return new AlertDialog.Builder(context).create();
    }

    @Provides
    public List<Movie> movies() {
        return new ArrayList<>();
    }


}
