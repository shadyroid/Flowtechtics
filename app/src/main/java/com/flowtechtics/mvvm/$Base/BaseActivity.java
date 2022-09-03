package com.flowtechtics.mvvm.$Base;

import android.content.DialogInterface;

import androidx.appcompat.app.AppCompatActivity;

import com.flowtechtics.classes.dialogs.ErrorHandlerDialog;
import com.flowtechtics.classes.models.responses.BaseResponse;
import com.flowtechtics.classes.ui.LoadingProgressView;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BaseActivity extends AppCompatActivity {

    @Inject
    ErrorHandlerDialog errorHandlerDialog;
    @Inject
    LoadingProgressView progressView;

    public void onLoading(boolean isLoading) {
        progressView.isShow(isLoading);
    }

    public void onApiError(BaseResponse response) {
        errorHandlerDialog.setBaseResponse(response, this::onErrorHandlerDialogOkClick);
        errorHandlerDialog.show();
    }
    private void onErrorHandlerDialogOkClick(DialogInterface dialog, int which) {
        errorHandlerDialog.dismiss();
        finish();
        startActivity(getIntent());
    }

}
