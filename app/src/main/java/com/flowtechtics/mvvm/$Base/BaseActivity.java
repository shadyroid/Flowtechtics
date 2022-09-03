package com.flowtechtics.mvvm.$Base;

import androidx.appcompat.app.AppCompatActivity;

import com.flowtechtics.classes.ui.LoadingProgressView;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BaseActivity extends AppCompatActivity {


    @Inject
    LoadingProgressView progressView;

    public void onLoading(boolean isLoading) {
        progressView.isShow(isLoading);
    }


}
