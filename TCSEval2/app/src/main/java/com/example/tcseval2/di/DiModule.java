package com.example.tcseval2.di;

import com.example.tcseval2.model.API;
import com.example.tcseval2.model.SearchService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DiModule {
    public static final String baseUrl = "https://itunes.apple.com";

    @Provides
    public API getSearchAPI(){
        return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(API.class);
    }

    @Provides
    public SearchService getSearchService(){
        return SearchService.getInstance();
    }
}
