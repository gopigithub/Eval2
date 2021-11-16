package com.example.tcseval2.model;

import com.example.tcseval2.di.DaggerDIComponent;

import java.util.List;

import javax.inject.Inject;

import dagger.Provides;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchService {

    @Inject
    public API api;

    private static SearchService service;
    private SearchService(){
        DaggerDIComponent.create().inject(this);

    }

    public static SearchService getInstance(){
        if(service == null){
            service = new SearchService();
        }
        return service;
    }



    public Single<List<SearchModel>> getSearchModel(String id){

        return api.getSearchResult(id);
    }
}
