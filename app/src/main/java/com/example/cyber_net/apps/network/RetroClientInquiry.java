package com.example.cyber_net.apps.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClientInquiry {
    private static Retrofit retrofit = null;
    public static String BASE_URL_INQUIRY = "http://103.84.192.229:8098/ppob/";
    public static String BASE_URL_PAYMENT = "http://103.84.192.229:8099/ppob/";
    public static String BASE_URL_STATUS = "http://103.84.192.229:8097/ppob/";
    static String url;

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String turl) {
        url = turl;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static void setRetrofit(Retrofit retrofit) {
        RetroClientInquiry.retrofit = retrofit;
    }

    private static Retrofit getClient(){
        //cek jika retrofit null
        if (retrofit == null){
            //maka buat object dari retrofit
            retrofit = new Retrofit.Builder()
                    //ubah sesuai urlnya
                    .baseUrl(getUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiService getApiService(){
        return getClient().create(ApiService.class);
    }
}