package com.flowtechtics.classes.rest.interceptors;


import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.scopes.ViewModelScoped;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


@Singleton
public class HeadersInterceptor implements Interceptor {

    @Inject
    public HeadersInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();

        requestBuilder.addHeader("Accept", "application/json");
        requestBuilder.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNGQ2N2JjZmIxYzFmNDhmMmNmNzQwMjcwMjliM2YxZiIsInN1YiI6IjVlN2U5ZjkzNDM1YWJkMDAxNWIwZjZhNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AA38kTRQmJlVZq0K3kM2zwUK-OE9RtHW8TvN_SySjRw");

        return chain.proceed(requestBuilder.build());
    }


}
