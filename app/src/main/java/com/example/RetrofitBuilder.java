package com.example;

import com.example.ConverterService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.converter.BuildConfig.BASE_URL;

public class RetrofitBuilder {
    private static ConverterService converterService;

    public static ConverterService getService(){
        if (converterService == null){
            converterService = buildRetrofit();}
        return converterService;
    }

    private static ConverterService buildRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ConverterService.class); }
}
