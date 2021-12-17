package com.pay1onet.fmca.network;

import com.google.gson.JsonObject;
import com.pay1onet.fmca.JSONSchema.BillerSchema;
import com.pay1onet.fmca.JSONSchema.JSONResponse;
//import com.pay1onet.fmca.JSONSchema.ProcessInvoiceSchema;
import com.pay1onet.fmca.JSONSchema.SyncDepartments;
import com.pay1onet.fmca.JSONSchema.UpdateWalletSchema;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @POST("login")
    @FormUrlEncoded
    Call<JSONResponse> login(
            @Field("username") String username,
            @Field("password") String password,
            @Field("uuid") int uuid,
            @Field("serial") int serial,
            @Query("app") String app,
            @Query("service") String service
    );

    @PUT("sync/updateWallet")
    Call<UpdateWalletSchema> recharge(@Header("Authorization") String token,
            @Body JsonObject body
    );

    //@Headers({"Accept:application/x-www-form-urlencoded", "Content-Type:application/x-www-form-urlencoded;"})
    @POST("sync/payments/fmca")
    Call<JsonObject> uploadPayment(@Header("Authorization")String token, @Body JsonObject body, @Query("service") String service);

    @GET("sync/revHeads/{mdaId}")
    Call<SyncDepartments> syncDepartments(@Header("Authorization") String token, @Path("mdaId") String mdaId);

    @GET("sync/getByBillerNo/{billNo}")
    Call<BillerSchema> getPayments(@Header("Authorization") String token, @Path("billNo") String billNo);

    /*@Headers({"Accept:application/json", "Content-Type:application/json;"})
    @POST("sync/invoice")
    Call<ProcessInvoiceSchema> processInvoice(@Body RequestBody body);*/

}
