package com.example;

import com.google.gson.JsonObject;

import java.util.Currency;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ConverterService {

    @GET("latest")
    Call<JsonObject> currency(@Query("access_key")String key);
}
