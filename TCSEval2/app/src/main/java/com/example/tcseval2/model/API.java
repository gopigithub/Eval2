package com.example.tcseval2.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("/lookup")
    public Single<List<SearchModel>> getSearchResult(@Query("id") String id);
}
