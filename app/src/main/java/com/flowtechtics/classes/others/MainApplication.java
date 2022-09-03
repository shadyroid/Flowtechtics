package com.flowtechtics.classes.others;

import android.app.Application;

import com.flowtechtics.BuildConfig;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;
import timber.log.Timber;


@HiltAndroidApp
public class MainApplication extends Application {

    @Inject
    Timber.DebugTree debugTree;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(debugTree);
        }
    }
}