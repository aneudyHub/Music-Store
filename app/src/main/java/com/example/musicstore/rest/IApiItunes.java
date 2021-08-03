package com.example.musicstore.rest;

import com.example.musicstore.repository.models.SearchResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApiItunes {

    @GET("search")
    Call<SearchResponse> getSearchResponse(@Query("term") String Term,@Query("media") String media);
}
