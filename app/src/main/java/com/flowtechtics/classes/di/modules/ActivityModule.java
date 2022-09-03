package com.flowtechtics.classes.di.modules;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import net.bohush.geometricprogressview.GeometricProgressView;

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


}
