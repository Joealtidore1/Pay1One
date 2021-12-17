package com.pay1onet.fmca.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.pay1onet.fmca.R;
import com.pay1onet.fmca.activities.InvoiceActivity;
import com.pay1onet.fmca.database.DBHelper;
import com.pay1onet.fmca.models.PaymentModel;
import com.pay1onet.fmca.network.ApiClient;
import com.pay1onet.fmca.utils.Helper;
import com.pay1onet.fmca.utils.NetworkConnectivity;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class HistoryFragment extends Fragment implements View.OnClickListener {

    private View view;
    TextView totalTransactions, totalAmount, date, synced, unsynced;
    TextView uploadPayments;
    ListView listView;
    List<PaymentModel> paymentModels;
    DBHelper db;
    private List<PaymentModel> paymentModel;
    int index;


    public HistoryFragment() {

    }

    private void showToast(String msg) {
        Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_LONG);
        View view = toast.getView();
        /*view.setBackgroundResource(android.R.color.white);
        TextView tv = view.findViewById(android.R.id.message);
        tv.setTextColor(Color.argb(255, 17, 62, 95));
        tv.setTextSize(22.0f);*/
        toast.show();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_history, container, false);

        init(view);

        new getData().execute();

        return view;
    }

    public void init(View view){
        db=new DBHelper(getContext());
        totalAmount = view.findViewById(R.id.totalAmount);
        totalTransactions = view.findViewById(R.id.totalTransactions);
        date = view.findViewById(R.id.date);
        synced = view.findViewById(R.id.synced);
        unsynced = view.findViewById(R.id.unsynced);

        uploadPayments = view.findViewById(R.id.uploadPayments);

        uploadPayments.setOnClickListener(this);


        listView = view.findViewById(R.id.paymentList);

        date = view.findViewById(R.id.date);

        date.setText(Helper.getDate());

        listView.setOnItemClickListener((adapterView, views, i, l)-> {
            index = i;
            startInvoice(paymentModels.get(index).getRrr());
        });

        uploadPayments = view.findViewById(R.id.uploadPayments);
    }

    public boolean checkPermission(){
        if(ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{Manifest.permission.BLUETOOTH}, 1000);
            return false;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == PackageManager.PERMISSION_GRANTED){
                startInvoice(paymentModels.get(index).getRrr());
            }else{
                Toast.makeText(getContext(), "Bluetooth permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void startInvoice(String billNo){
        if(checkPermission()){
            startActivity(new Intent(getContext(), InvoiceActivity.class).putExtra("billNo", billNo));
            getActivity().finish();
        }
    }

    public void syncronizePayments(){
        if(paymentModel != null && paymentModel.size()>0){
            uploadPayments.setEnabled(false);
            String token = Objects.requireNonNull(getContext()).getSharedPreferences("shared", 0).getString("token", "");
            Call<JsonObject> call = new ApiClient().getApi().uploadPayment(token, new Helper().jsonBody(paymentModel.get(0)), "fmca");

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    try{
                        Log.d("RESPONSE", response.code() + "");
                        JsonObject jsonObject = response.body();
                        if(response.errorBody() != null) {
                            Log.d("RESPONSE", response.errorBody().string() + "");
                        }
                        if(jsonObject.has("status") && jsonObject.get("status").getAsString().equalsIgnoreCase("success")){
                            db.updatePayment(response.body().get("ref").getAsString());
                            paymentModel.remove(0);
                            if(paymentModel.size()> 0){
                                syncronizePayments();
                            }else{
                                showToast("Synchronized ended successfully");
                                new getData().execute();
                                uploadPayments.setVisibility(GONE);
                            }
                        }else if(jsonObject.has("message") && jsonObject.get("message").getAsString().contains("Record already")){
                            db.updatePayment(response.body().get("ref").getAsString());
                            Log.d("Message", response.body().get("ref").getAsString());
                            paymentModel.remove(0);
                            if(paymentModel.size() > 0) {
                                syncronizePayments();
                            }else{
                                showToast("Synchronized ended successfully");
                                new getData().execute();
                                uploadPayments.setVisibility(GONE);
                            }
                        }else{
                            uploadPayments.setEnabled(true);
                            showToast(response.body().get("message").getAsString());
                        }
                    } catch (Exception e) {
                        uploadPayments.setEnabled(true);
                        e.printStackTrace();
                        showToast(e.getMessage());
                        Log.d("ERRORPY", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    uploadPayments.setEnabled(true);
                    Log.d("SYNC ERROR", t.getMessage());
                    showToast("synchronization failed due to a network error.");

                }
            });
        }else{
            return;
        }
    }

    @Override
    public void onClick(View view) {
        if(!new NetworkConnectivity(getContext()).getCm()){
            new AlertDialog.Builder(getContext())
                    .setMessage("No internet! Turn or Wifi or Data to continue process")
                    .setNeutralButton("Dismiss", (dialog, which)->{
                        dialog.dismiss();
                        dialog.cancel();
                    }).show();
        }else {
            showToast("synchronization started");
            syncronizePayments();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class getData extends AsyncTask<Void, String, Void>{
        ProgressDialog dialog = new ProgressDialog(getContext());



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Loading");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String oAmount = db.getNTransactions("All")[1];
            String oTotal = db.getNTransactions("All")[0];
            String oSync = db.countSynced("All");
            paymentModel = db.getPayment();
            publishProgress(oAmount, oTotal, oSync);

            paymentModels = db.getAllPaymentModels();

            return null;
        }

        @SuppressLint("DefaultLocale")
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            int sync =  Integer.parseInt( values[1])-Integer.parseInt(values[2].split("/")[0]);
            totalTransactions.setText(String.format("Total Transactions: %s", values[1]));
            totalAmount.setText(String.format("Total Amount: ₦%s", values[0]));
            synced.setText(String.format("Synced: %d", sync));
            unsynced.setText(String.format("Unsynced: %s", values[2].split("/")[0]));
        }

        @Override
        protected void onPostExecute(Void o) {
            super.onPostExecute(o);
            ListAdapter adapter = new ListAdapter(getContext(), paymentModels);
            listView.setAdapter(adapter);
            dialog.cancel();

        }
    }

    public class ListAdapter extends ArrayAdapter<PaymentModel>{

        public ListAdapter(Context context, List<PaymentModel> paymentModels) {
            super(context, 0, paymentModels);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
            if(view == null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.custom_report, null, false);
            }

            TextView name = view.findViewById(R.id.name);
            TextView date = view.findViewById(R.id.dateTime);
            TextView amount = view.findViewById(R.id.amount);
            ImageView synced = view.findViewById(R.id.synced);

            PaymentModel paymentModel = paymentModels.get(position);

            if(paymentModel != null) {
                name.setText(paymentModel.getPayerName());
                date.setText(String.format("%s %s", paymentModel.getPaymentDate(), paymentModel.getPaymentTime()));
                amount.setText(new DBHelper(getContext()).
                        getTransTotal("\"" + paymentModel.getRrr() + "\""));

                if (paymentModel.getSynced().equals("0")) {
                    synced.setImageResource(R.drawable.unsynced);
                } else {
                    synced.setImageResource(R.drawable.synced);
                }
            }

            return view;
        }
    }
}