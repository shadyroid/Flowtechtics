package com.flowtechtics.classes.di.modules;


import androidx.lifecycle.MutableLiveData;

import com.flowtechtics.classes.models.responses.BaseResponse;
import com.flowtechtics.classes.models.responses.MoviesResponse;
import com.flowtechtics.classes.others.CONSTANTS;
import com.flowtechtics.classes.rest.ApiInterface;
import com.flowtechtics.classes.rest.interceptors.HeadersInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.scopes.ViewModelScoped;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;


@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {


    @Provides
    @Singleton
    public ApiInterface provideApiInterface(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(CONSTANTS.BACKEND.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ApiInterface.class);

    }


    @Provides
    @Singleton
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, HeadersInterceptor headersInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(headersInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build();

    }


    @Provides
    @Singleton
    public HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(Timber::i);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    public CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }


    @Provides
    public MutableLiveData<Boolean> provideBooleanMutableLiveData() {
        return new MutableLiveData<>();
    }

    @Provides
    public MutableLiveData<MoviesResponse> provideMoviesResponseMutableLiveData() {
        return new MutableLiveData<>();
    }

    @Provides
    public MutableLiveData<BaseResponse> provideBaseResponseMutableLiveData() {
        return new MutableLiveData<>();
    }

}
