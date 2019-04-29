package com.example.cyber_net.apps.network;

/**
 * Created by Fajar on 4/16/2019.
 */
import java.net.CookieManager;


import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit getRetrofit(String link){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build())
                .build();

        return retrofit;
    }
}
