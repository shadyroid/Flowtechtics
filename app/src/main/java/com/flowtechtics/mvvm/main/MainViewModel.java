package com.flowtechtics.mvvm.main;

import androidx.lifecycle.MutableLiveData;

import com.flowtechtics.classes.models.responses.MoviesResponse;
import com.flowtechtics.mvvm.$Base.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;

@Getter
@HiltViewModel
public class MainViewModel extends BaseViewModel {
    @Inject
    MutableLiveData<MoviesResponse> moviesResponseMutableLiveData;

    @Inject
    public MainViewModel() {
    }

    public MutableLiveData<MoviesResponse> requestMovies(int page) {
        getCompositeDisposable().add(Observable.just(page)
                .doOnNext(__ -> getOnLoadingMutableLiveData().setValue(true))
                .observeOn(Schedulers.io())
                .takeWhile(this::isInternetAvailable)
                .flatMap(data -> getRepository().requestMovies(data))
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> getOnLoadingMutableLiveData().setValue(false))
                .subscribe(response -> moviesResponseMutableLiveData.setValue(response), this::onFailure));
        return moviesResponseMutableLiveData;

    }
}
