package com.example.cyber_net.apps.network;

import com.example.cyber_net.apps.model.Akun;
import com.example.cyber_net.apps.model.Inquiry;
import com.example.cyber_net.apps.model.Kategori_pulsa;
import com.example.cyber_net.apps.model.PPOB;
import com.example.cyber_net.apps.model.PPOB_payment;
import com.example.cyber_net.apps.model.PULSA_create;
import com.example.cyber_net.apps.model.Pulsa;
import com.example.cyber_net.apps.model.Topup;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Fajar on 4/16/2019.
 */

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    Call<Akun> login(@Field("email") String email,
                     @Field("password") String password
    );
    @FormUrlEncoded
    @POST("user_get")
    Call<Akun> UserGet(@Field("id") String id);
    @FormUrlEncoded
    @POST("ppob_list")
    Call<PPOB> getPPOBlist(@Field("kategori_ppob_id")Integer kategori_ppob_id);

    @FormUrlEncoded
    @POST("kategori_pulsa_list")
    Call<Kategori_pulsa> getKategorilist(@Field("jenis")String jenis);

    @FormUrlEncoded
    @POST("pulsa_list")
    Call<Pulsa> getPulsalist(@Field("kategori_pulsa_id")String kategori_pulsa_id);

    @FormUrlEncoded
    @POST("ppob_inquiry")
    Call<Inquiry> ppob_inquiry(@Field("kode")String kode,@Field("customer")String customer);

    @FormUrlEncoded
    @POST("ppob_payment")
    Call<PPOB_payment> ppob_payment(
            @Field("product_code")String product_code,
            @Field("reff_id")String reff_id,
            @Field("customer_no")String customer_no,
            @Field("amount")String amount,
            @Field("nomor")String nomor,
            @Field("user_id")String user_id);
    @FormUrlEncoded
    @POST("pulsa_create")
    Call<PULSA_create> pulsa_create(
            @Field("kode_pulsa")String kode_pulsa,
            @Field("user_id")String user_id,
            @Field("customer_no")String customer_no);
    @FormUrlEncoded
    @POST("top_up_list")
    Call<Topup> topup_list(
            @Field("cms_users_id")String cms_users_id);
    @FormUrlEncoded
    @POST("topup_cek")
    Call<Topup> topup_cek(
            @Field("id")String id);
    @FormUrlEncoded
    @POST("topup_batal")
    Call<Topup> topup_batal(
            @Field("id")String id);
    @FormUrlEncoded
    @POST("topup_create")
    Call<Topup> topup_create(
            @Field("cms_users_id")String cms_users_id,@Field("nominal")String nominal);
    @FormUrlEncoded
    @POST("sign_in")
    Call<Akun> sign_in(
            @Field("name")String name,@Field("email")String email,@Field("password")String password);
}
