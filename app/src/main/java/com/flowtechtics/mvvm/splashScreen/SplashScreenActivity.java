package com.flowtechtics.mvvm.splashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.flowtechtics.databinding.ActivitySplashScreenBinding;
import com.flowtechtics.mvvm.$Base.BaseActivity;
import com.flowtechtics.mvvm.main.MainActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class SplashScreenActivity extends BaseActivity {
    ActivitySplashScreenBinding mBinding;

    @Inject
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        handler.postDelayed(this::startMainActivity, 1000);

    }

    private void startMainActivity() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }


}