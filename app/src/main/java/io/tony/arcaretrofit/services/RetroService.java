package io.tony.arcaretrofit.services;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class RetroService {
    private static RetroService sService;

    private final RetroApiService mApiService;

    public static RetroService getInstance() {
        if (sService == null) {
            sService = new RetroService();
        }
        return sService;
    }

    public RetroService() {
        final RestAdapter.Builder builder = new RestAdapter.Builder().setEndpoint("http://api.mtgdb.info").setClient(new OkClient(new OkHttpClient())).setLogLevel(RestAdapter.LogLevel.FULL);
        final RestAdapter adapter = builder.build();
        mApiService = adapter.create(RetroApiService.class);
    }

    public RetroApiService getApiService() {
        return mApiService;
    }
}
