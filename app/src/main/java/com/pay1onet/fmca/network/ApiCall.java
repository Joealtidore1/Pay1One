package com.pay1onet.fmca.network;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.JsonObject;
import com.pay1onet.fmca.JSONSchema.AppDefaultsSchema;
import com.pay1onet.fmca.JSONSchema.BillerSchema;
import com.pay1onet.fmca.JSONSchema.CategorySchema;
import com.pay1onet.fmca.JSONSchema.DepartmentSchema;
import com.pay1onet.fmca.JSONSchema.JSONResponse;
import com.pay1onet.fmca.JSONSchema.PaymentRetrieval;
import com.pay1onet.fmca.JSONSchema.ResponseSchema;
import com.pay1onet.fmca.JSONSchema.RevenueHeads;
import com.pay1onet.fmca.JSONSchema.SyncDepartments;
import com.pay1onet.fmca.JSONSchema.UpdateWalletSchema;
import com.pay1onet.fmca.JSONSchema.WalletSchema;
import com.pay1onet.fmca.constants.Definitions;
import com.pay1onet.fmca.database.DBHelper;
import com.pay1onet.fmca.models.AppDefaultsModel;
import com.pay1onet.fmca.models.CategoriesModel;
import com.pay1onet.fmca.models.DepartmentsModel;
import com.pay1onet.fmca.models.RevHeadsModel;
import com.pay1onet.fmca.models.UserModel;
import com.pay1onet.fmca.models.WalletModel;
import com.pay1onet.fmca.utils.Helper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCall {

    /*public void getLoginResponse(Activity context, NetworkCallBack nCallBack){
        Call<JSONResponse> login = new ApiClient().getApi().login();

        login.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                try{
                    JSONResponse rp = response.body();
                        if(rp.isStatus() ){
                                if((rp.getAppDefaultsSchema() != null)
                                && (rp.getDepartmentSchemas() != null)
                                && (rp.getRevenueHeads() != null)
                                ) {

                                    List<CategoriesModel> categoryModel = new ArrayList<>();
                                    List<DepartmentsModel> departmentModel = new ArrayList<>();
                                    List<RevHeadsModel> revHeadsModel = new ArrayList<>();
                                    UserModel userModel;
                                    WalletModel walletModel;
                                    AppDefaultsModel appDefaultModel;
                                    if(rp.getCategorySchemas() != null) {
                                        for (CategorySchema ca : rp.getCategorySchemas()) {
                                            categoryModel.add(new CategoriesModel(
                                                    0, ca.getCategoryId(), ca.getCategory(), ca.getDept(), ca.getDepartment()
                                            ));
                                        }
                                    }

                                    for (DepartmentSchema dp : rp.getDepartmentSchemas()) {
                                        departmentModel.add(new DepartmentsModel(0, dp.getDeptID(), dp.getDeptName(), dp.getDeptAbbr()));
                                    }
                                    for (RevenueHeads rh : rp.getRevenueHeads()) {
                                        revHeadsModel.add(new RevHeadsModel(0, rh.getRevenueHead(), rh.getCode(), rh.getId(), rh.getAmount(), rh.getDept(), rh.getDepartment(), rh.getCate(), rh.getCategory(), rh.getSubs(), rh.getEmr(), rh.getPricetype()));
                                    }


                                    AppDefaultsSchema ad = rp.getAppDefaultsSchema();
                                    appDefaultModel = new AppDefaultsModel(0, ad.getMdaId(), ad.getPayerEmail(), ad.getPayerPhone(), ad.getChargeType(), ad.getCharge());

                                    ResponseSchema us = rp.getResponseSchema();
                                    Log.d("USER", us.toString());
                                    //userModel = new UserModel(0, us.getUserId(), us.getBillno(), us.getPayerEmail(), us.getAddress(), us.getOrganisation(), us.getUsername(), us.getMdacode(), us.getLastLogin(), us.getLocation(), us.getPayerPhone());

                                    WalletSchema ws = rp.getWalletSchema();
                                    walletModel = new WalletModel(0, ws.getAgent(), ws.getBalance(), ws.getAgentId());


                                    nCallBack.onSuccess(appDefaultModel, categoryModel, departmentModel,revHeadsModel,null, walletModel , rp.isStatus());
                                }else{
                                    nCallBack.onFailure("Login failed due to an error!");
                                }
                    }else{
                        nCallBack.onFailure("an error occurred");
                    }

                } catch (Exception e) {
                    nCallBack.onFailure("An error occurred. try again");
                    Log.d("ERROR", e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                nCallBack.onFailure(t.getMessage());
            }
        });
    }*/

    public void recharge(Context context, String ref, double bal, RechargeCallBack recharge){
        JsonObject object = new JsonObject();
        object.addProperty("balance", String.valueOf(bal));
        object.addProperty("ref", ref);
        String token = context.getSharedPreferences("shared", 0).getString("token", "");
        Call<UpdateWalletSchema> call = new ApiClient().getApi().recharge(token, object);
        call.enqueue(new Callback<UpdateWalletSchema>() {
            @Override
            public void onResponse(Call<UpdateWalletSchema> call, Response<UpdateWalletSchema> response) {
                try{
                    if(response.body().getStatus().equalsIgnoreCase("success")){
                        Log.d("RESPONSE", response.body().getBalance()+"");
                        recharge.onSuccess(response.body().getBalance());
                    }else{
                        Log.d("ERROR", response.body().toString());
                        recharge.onFailure(response.body().getMessage());
                    }
                } catch (Exception e) {
                    Log.d("ERROR E", e.getMessage());
                    recharge.onFailure(e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UpdateWalletSchema> call, Throwable t) {
                Log.d("FAILURE", t.getMessage());
                recharge.onFailure("oops! a possible network error occurred");
            }
        });
    }

    /*public void recharge(String ref, double bal, RechargeCallBack recharge){
        Call<UpdateWalletSchema> call = new ApiClient().getApi().recharge(bal, ref, Definitions.X_API_KEY);
        call.enqueue(new Callback<UpdateWalletSchema>() {
            @Override
            public void onResponse(@NotNull Call<UpdateWalletSchema> call, Response<UpdateWalletSchema> response) {
                try{
                    if(response.body().isStatus()){
                        recharge.onSuccess(response.body().getBalance());
                    }else{
                        recharge.onFailure(response.body().getMessage());
                    }
                } catch (Exception e) {
                    recharge.onFailure("an error occurred and recharge could not be completed");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UpdateWalletSchema> call, Throwable t) {
                recharge.onFailure("oops! a possible network error occurred");
            }
        });
    }*/

    public void getLoginResponse(Activity context, String un, String pw, NetworkCallBack nCallBack){
        Call<JSONResponse> login = new ApiClient().getApi().login(un,
                pw, 123456, 123456, "regular", "fmca");

        login.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                try{
                    JSONResponse rp = response.body();
                    if(rp.isStatus().equalsIgnoreCase("success") ){
                        if((rp.getWalletSchema() != null)
                                && (rp.getAppDefaultsSchema() != null)
                                /*&& (rp.getDepartmentSchemas() != null)*/
                                && (rp.getResponseSchema() != null)
                                && (rp.getRevenueHeads() != null)
                                && (rp.getWalletSchema() != null)
                        ) {

                            List<CategoriesModel> categoryModel = new ArrayList<>();
                            List<DepartmentsModel> departmentModel = new ArrayList<>();
                            List<RevHeadsModel> revHeadsModel = new ArrayList<>();
                            UserModel userModel;
                            WalletModel walletModel;
                            AppDefaultsModel appDefaultModel;
                            if(rp.getCategorySchemas() != null) {
                                for (CategorySchema ca : rp.getCategorySchemas()) {
                                    categoryModel.add(new CategoriesModel(
                                            0, ca.getCategoryId(), ca.getCategory(), ca.getDept(), ca.getDepartment()
                                    ));
                                }
                            }

                            if(rp.getDepartmentSchemas() != null) {
                                for (DepartmentSchema dp : rp.getDepartmentSchemas()) {
                                    departmentModel.add(new DepartmentsModel(0, dp.getDeptID(), dp.getDeptName(), dp.getDeptAbbr()));
                                }
                            }

                            for (RevenueHeads rh : rp.getRevenueHeads()) {
                                revHeadsModel.add(new RevHeadsModel(
                                        0,
                                        rh.getId(),
                                        rh.getServiceTypeId(),
                                        rh.getCode(),
                                        rh.getRevenueHead(),
                                        rh.getTargetValidFrom(),
                                        rh.getMdaId(),
                                        rh.getAccountOwner(),
                                        rh.getAccountName(),
                                        rh.getAccountNumber(),
                                        rh.getBankName(),
                                        rh.getAccountType(),
                                        rh.getBankCode(),
                                        rh.getReady(),
                                        rh.getAmount(),
                                        rh.getType(),
                                        rh.getDept(),
                                        rh.getDepartment(),
                                        rh.getCate(),
                                        rh.getCategory(),
                                        rh.getSubs(),
                                        rh.getRrn(),
                                        rh.getAcct(),
                                        rh.getRemSevType(),
                                        rh.getReportSevType(),
                                        rh.getSc(),
                                        rh.getMod()
                                ));
                            }

                            ResponseSchema us = rp.getResponseSchema();
                            userModel = new UserModel(
                                    0,
                                    us.getName(),
                                    us.getPhone(),
                                    us.getEmail(),
                                    us.getAddress(),
                                    us.getOrganisation(),
                                    us.getUserId(),
                                    us.getUsername(),
                                    us.isLoggedin(),
                                    us.getLastLogin(),
                                    0,
                                    us.getMdaId(),
                                    us.getBalance(),
                                    us.getDefphone(),
                                    us.getDefemail(),
                                    us.getQuantity(),
                                    us.getMdacode(),
                                    us.getSerialNo(),
                                    us.getApikey(),
                                    us.getAirport(),
                                    us.getPaypoint(),
                                    us.getPaypointId(),
                                    us.getDevice(),
                                    us.getLocation()
                            );

                            WalletSchema ws = rp.getWalletSchema();
                            walletModel = new WalletModel(0, ws.getAgent(), ws.getBalance(), ws.getAgentId());

                            AppDefaultsSchema ad = rp.getAppDefaultsSchema();
                            appDefaultModel = new AppDefaultsModel(0, ad.getMdaId(), ad.getEmail(), ad.getPhone(), ad.getChargeType(), ad.getCharge());

                            SharedPreferences preferences = context.getSharedPreferences("shared", 0);
                            SharedPreferences.Editor edit = preferences.edit();
                            edit.putString("token", "Bearer " + rp.getToken());
                            edit.apply();

                            Log.d("SIZE", (departmentModel.size() + revHeadsModel.size() + categoryModel.size() + 3) + "");


                            nCallBack.onSuccess(appDefaultModel, categoryModel, departmentModel, userModel, revHeadsModel, walletModel, true);
                        }else{
                            nCallBack.onFailure("Login failed due to an error!");
                        }
                    }else{
                        nCallBack.onFailure(rp.getMessage());
                    }

                } catch (Exception e) {
                    Log.d("ERROR2", e.getMessage());
                    nCallBack.onFailure("An error occurred. try again");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
                nCallBack.onFailure("A network error occurred. try again");
            }
        });
    }

    public void syncDepartments(Context context, String mdaId, syncDepartmentCallBack callBack){
        String token = context.getSharedPreferences("shared", 0).getString("token", "");
        new ApiClient().getApi().syncDepartments(token, mdaId).enqueue(new Callback<SyncDepartments>() {
            @Override
            public void onResponse(@NotNull Call<SyncDepartments> call, @NotNull Response<SyncDepartments> response) {
                Log.d("SYNC CODE", response.code() + "");
                SyncDepartments body = response.body();
                try{
                    if(body.getStatus().equalsIgnoreCase("success")){
                        callBack.onSuccess(body.getDepartments(), body.getRevenueHeads(), body.getCategory());
                    }else{
                        callBack.onFailure(body.getMessage());
                    }
                } catch (Exception e) {
                    callBack.onFailure("an error occurred");
                    Log.d("SYNC ERROR", e.getMessage());
                }
            }

            @Override
            public void onFailure(@NotNull Call<SyncDepartments> call, @NotNull Throwable t) {
                Log.d("SYNC FAILURE", t.getMessage());
                callBack.onFailure("oops! a possible network error occurred");
            }
        });
    }


    public void getPaymentByBill(Context context, String billId, getPaymentsCallBack paymentsCallBack){
        String token = context.getSharedPreferences("shared", 0).getString("token", "");
        new ApiClient().getApi().getPayments(token, billId).enqueue(new Callback<BillerSchema>() {
            @Override
            public void onResponse(@NotNull Call<BillerSchema> call, @NotNull Response<BillerSchema> response) {
                if(response.errorBody() == null) {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {
                        paymentsCallBack.onSuccess(response.body());
                    } else {
                        paymentsCallBack.onFailure(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<BillerSchema> call, @NotNull Throwable t) {
                paymentsCallBack.onFailure("oops! a network error occurred");
            }
        });
    }



    public interface NetworkCallBack{
        void onSuccess(AppDefaultsModel appDefaults,
                       List<CategoriesModel> categories,
                       List<DepartmentsModel> departments,

                       UserModel userModel,
                       List<RevHeadsModel> revHeads,
                       WalletModel walletModel,
                       boolean status
        );

        public void onFailure(String message);
    }

    public interface RechargeCallBack{
        void onSuccess(double balance);
        void onFailure(String message);
    }

    public interface syncDepartmentCallBack{
        void onSuccess(List<DepartmentSchema> departments, List<RevenueHeads> revenueHeads, List<CategorySchema> categories);
        void onFailure(String message);
    }

    public interface getPaymentsCallBack{
        void onSuccess(BillerSchema schema);
        void onFailure(String errorMessage);
    }
}
