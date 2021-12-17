package com.pay1onet.fmca.utils;


import android.content.Context;
import android.util.ArrayMap;
import android.util.Log;

/*
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.impact.mobiprints.models.PaymentModel;
*/

import com.google.gson.JsonObject;
import com.pay1onet.fmca.JSONSchema.BillerSchema;
import com.pay1onet.fmca.JSONSchema.PaymentRetrieval;
import com.pay1onet.fmca.database.DBHelper;
import com.pay1onet.fmca.models.PaymentModel;
import com.pay1onet.fmca.models.RevHeadsModel;
import com.pay1onet.fmca.models.UserModel;
import com.pay1onet.fmca.models.WalletModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;


public class Helper {

    DBHelper db;


    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate= formatter.format(date);

        return  strDate;
    }

    public static String getRef(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-ssmmhh");
        String strDate= formatter.format(date);

        return  strDate;
    }


    public static String getTime(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String strDate= formatter.format(date);

        return  strDate;
    }

    public List<PaymentModel> schemaToModelEngine(List<PaymentRetrieval.PaymentDetail> schema, UserModel user){
        List<PaymentModel> model = new ArrayList<>();
        for(PaymentRetrieval.PaymentDetail p : schema){
            model.add(new PaymentModel(
                    0, p.getAmount(), p.getPayerName(), p.getPayerPhone(), p.getPayerEmail(),
                    p.getPaymentDate(), p.getPaymentTime(), p.getAgent(), p.getRevenueHead(), p.getSynced(),
                    p.getRef(), p.getPreviousBalance(), p.getCurrentBalance(), p.getRrr(), p.getLocation(),
                    p.getMdaId(), p.getRevId(), p.getAgentId(), p.getQuantity(), "", p.getTotal(),
                    p.getMethod(), p.getRevCode(), p.getLastSerial(), p.getDept(), p.getDepartment(), p.getCate(),
                    p.getCategory(), p.getDiscount(), p.getMainAmt(), p.getSubs(), p.getEmr(), p.getShift(), p.getPriceType()
            ));
        }
        return model;
    }


    /*public Js jsonBody(PaymentModel model){
        Map<String, Object> js = new ArrayMap<>();
        js.put("amount", model.getAmount());
        js.put("payername", model.getPayerName());
        js.put("payerphone", model.getPayerPhone());
        js.put("payeremail", model.getPayerEmail());
        js.put("paymentdate", model.getPaymentDate());
        js.put("paymenttime", model.getPaymentTime());
        js.put("agent", model.getAgent());
        js.put("revenuehead", model.getRevenueHead());
        js.put("synced", model.getSynced());
        js.put("ref", model.getRef());
        js.put("agentID", model.getAgentId());
        js.put("mdaID", model.getMdaId());
        js.put("revID", model.getRevId());
        js.put("cbalance", model.getCurrentBalance());
        js.put("pbalance", model.getPreviousBalance());
        js.put("lastserial", model.getLastSerial());
        js.put("method", model.getMethod());
        js.put("revcode", model.getRevCode());
        js.put("location", model.getLocation());
        js.put("dept", model.getDept());
        js.put("department", model.getDepartment());
        js.put("cate", model.getCate());
        js.put("category", model.getCategory());
        js.put("discount", model.getDiscount());
        js.put("quantity", model.getQuantity());
        js.put("mainamt", model.getMainAmt());
        js.put("desc", model.getRevenueHead());
        js.put("billno", model.getBillNo());
        js.put("shift", model.getShift());
        js.put("emr", model.getEmr());
        js.put("pricetype", model.getPriceType());
        js.put("uniqueID", model.getRrr());
        js.put("total", model.getTotal());



        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(js)).toString());
        return body;
    }*/

    public JsonObject jsonBody(PaymentModel model){
        JsonObject js = new JsonObject();
        js.addProperty("amount", String.valueOf(model.getAmount()));
        js.addProperty("payername",model.getPayerName());
        js.addProperty("payerphone", model.getPayerPhone());
        js.addProperty("payeremail", model.getPayerEmail());
        js.addProperty("paymentdate", model.getPaymentDate());
        js.addProperty("paymenttime", model.getPaymentTime());
        js.addProperty("agent", model.getAgent());
        js.addProperty("revenuehead", model.getRevenueHead());
        js.addProperty("synced", model.getSynced());
        js.addProperty("ref", model.getRef());
        js.addProperty("agentID", model.getAgentId());
        js.addProperty("mdaID", model.getMdaId());
        js.addProperty("revID", model.getRevId());
        js.addProperty("cbalance", model.getCurrentBalance());
        js.addProperty("pbalance", model.getPreviousBalance());
        js.addProperty("lastserial",  model.getLastSerial());
        js.addProperty("method", model.getMethod());
        js.addProperty("shift", model.getShift());
        js.addProperty("revcode", model.getRevCode());
        js.addProperty("location", model.getLocation());
        js.addProperty("dept", model.getDept());
        js.addProperty("department", model.getDepartment());
        js.addProperty("cate", model.getCate());
        js.addProperty("category", model.getCategory());
        js.addProperty("discount", model.getDiscount());
        js.addProperty("quantity", model.getQuantity());
        js.addProperty("mainamt", model.getMainAmt());
        js.addProperty("desc", model.getRevenueHead());
        js.addProperty("billno", model.getRrr());
        js.addProperty("shift", model.getShift());
        js.addProperty("emr", model.getEmr());
        js.addProperty("pricetype", model.getPriceType());
        js.addProperty("uniqueID", model.getRrr());
        js.addProperty("total", model.getTotal());

        Log.d("sample data", js.toString());
        return js;
    }


    public RequestBody jsonBody(String mda){
        Map<String, String> js = new ArrayMap<>();
        js.put("amount", mda);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(js)).toString());
    }

    public PaymentModel schemaToModel(BillerSchema.GeneratedBill bill, String time, UserModel user, String preBal, WalletModel walletModel, String method, DBHelper db){
        PaymentModel model;
        double total = (Double.parseDouble(bill.getAmount()));
        double amount = total / Double.parseDouble(bill.getQuantity());
        String curBal = Double.parseDouble(preBal) - total + "";
        RevHeadsModel rev = db.getRevById(bill.getRevenueId());

        model = new PaymentModel(
                0,
                amount,
                bill.getPayerName(),
                bill.getPayerPhone(),
                bill.getPayerEmail(),
                getDate(),
                time,
                walletModel.getAgent(),
                rev.getRevenueHead(),
                String.valueOf(0),
                bill.getRef(),
                preBal,
                curBal,
                bill.getBillno(),
                user.getLocation(),
                rev.getMdaId().toString(),
                String.valueOf(rev.getRevenueId()),
                String.valueOf(user.getUserId()),
                bill.getQuantity(),
                String.valueOf(0),
                String.valueOf(total),
                method,
                rev.getRevenueCode(),
                "",
                rev.getDept(),
                rev.getDepartment(),
                rev.getCate(),
                rev.getCategory(),
                String.valueOf(0),
                String.valueOf(total),
                rev.getSubs(),
                "",
                "",
                bill.getBillno()
        );
        return model;
    }

    public RequestBody getPaymentByBillNo(String billNo){
        Map<String, String> js = new ArrayMap<>();
        js.put("billNo", billNo);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(js)).toString());
    }


    public static String convert(double n){
        String[] ones = {
                "", "One", "Two", "Three", "Four",
                "Five", "Six", "Seven", "Eight",
                "Nine", "Ten", "Eleven", "Twelve",
                "Thirteen", "Fourteen", "Fifteen",
                "Sixteen", "Seventeen", "Eighteen",
                "Nineteen"
        };

        String[] tens = {
                "",          // 0
                "",          // 1
                "Twenty",    // 2
                "Thirty",    // 3
                "Forty",     // 4
                "Fifty",     // 5
                "Sixty",     // 6
                "Seventy",   // 7
                "Eighty",    // 8
                "Ninety"     // 9
        };

        /*long naira = (long) n;
        long kobo = Math.round((n - naira) * 100);*/

        if (n < 20) {
            return ones[(int) n];
        }

        if (n < 100) {
            return tens[(int) n / 10]
                    + ((n % 10 != 0) ? " " : "")
                    + ones[(int) n % 10];
        }

        if (n < 1000) {
            return convert(n / 100) + " Hundred" + ((n % 100 != 0) ? " and " : "")
                    + convert(n % 100);
        }


        if (n < 1_000_000) {
            return convert(n / 1000) + " Thousand" + ((n % 1000 != 0) ? " " : "")
                    + convert(n % 1000);
        }

        if (n < 1_000_000_000) {
            return convert(n / 1000000) + " Million" + ((n % 1000 != 0) ? " " : "")
                    + convert(n % 1000000);
        }

        return String.valueOf(n);
    }

    public DBHelper getDb(Context context){
        if(db == null){
            db = new DBHelper(context);
        }
        return db;
    }

    public static  String getPrevDate(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);

        return df.format(cal.getTime());
    }
}
