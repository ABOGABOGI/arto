package com.example.cyber_net.apps.network;

import com.example.cyber_net.apps.model.inquiry.Inquiry;
import com.example.cyber_net.apps.model.payment.Payment;
import com.example.cyber_net.apps.model.send.PostInquiry;
import com.example.cyber_net.apps.model.send.PostPayment;
import com.example.cyber_net.apps.model.status.Status;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("inquiry")
    Call<Inquiry> postInquiry(@Body HashMap<String, Object> body);

    @POST("inquiry")
    Call<Inquiry> postInquiry2(@Body PostInquiry postInquiry);

    @POST("payment")
    Call<Payment> postPayment(@Body HashMap<String, Object> body);

    @POST("payment")
    Call<Payment> postPayment2(@Body PostPayment postPayment);

    @FormUrlEncoded
    @POST("read/pos.php")
    Call<Status> postStatus(@Field("partner_id") String PartId, @Field("transaction_id") String transId,
                            @Field("reff_id") String reffId, @Field("product_code") String prodCode,
                            @Field("merchant_code") String merchantCode, @Field("customer_no") String custNo,
                            @Field("amount") String amount);
}