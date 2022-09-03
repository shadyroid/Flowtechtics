package com.flowtechtics.mvvm.$Base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.flowtechtics.classes.models.responses.BaseResponse;
import com.flowtechtics.classes.rest.Repository;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.CompositeDisposable;
import lombok.Getter;
import lombok.Setter;
import timber.log.Timber;

@Setter
@Getter
@HiltViewModel
public class BaseViewModel extends ViewModel {


    @Inject
    Repository repository;
    @Inject
    CompositeDisposable compositeDisposable;
    @Inject
    MutableLiveData<Boolean> onLoadingMutableLiveData;
    @Inject
    MutableLiveData<BaseResponse> onApiErrorMutableLiveData;

    @Inject
    public BaseViewModel() {
    }



    protected void onFailure(Throwable throwable) {
        onApiErrorMutableLiveData.setValue(BaseResponse.builder().status_message(throwable.toString()).success(false).build());
        Timber.e(throwable);

    }
    public boolean isInternetAvailable(Object object) {
        try {
            int timeoutMs = 5000;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

            sock.connect(sockaddr, timeoutMs);
            sock.close();

            return true;
        } catch (IOException e) {
            onLoadingMutableLiveData.postValue(false);
            onApiErrorMutableLiveData.postValue(BaseResponse.builder().status_code(503).success(false).build());
            return false;
        }
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

}
