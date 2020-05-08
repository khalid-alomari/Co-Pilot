package com.example.khalid.CoPilot;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String base_url = "http://quotidian-twins.000webhostapp.com/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
