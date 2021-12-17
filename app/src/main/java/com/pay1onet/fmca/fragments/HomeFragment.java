package com.pay1onet.fmca.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pay1onet.fmca.R;
import com.pay1onet.fmca.database.DBHelper;
import com.pay1onet.fmca.utils.Helper;

import java.nio.channels.AsynchronousChannelGroup;


public class HomeFragment extends Fragment {

    TextView balance, balanceInv, tellerName,
            todayAmount, todayTrans, todaySynced,
            yesAmount, yesTrans, yesSynced,
            ovAmount, ovTrans, ovSynced;
    ImageView visibility;

    DBHelper db;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);

        return view;
    }

    private void init(View view){
        balance = view.findViewById(R.id.balanceTV);
        balanceInv = view.findViewById(R.id.balanceTVINV);
        tellerName = view.findViewById(R.id.tellerName);

        //Today's views
        todayAmount = view.findViewById(R.id.todayAmount);
        todayTrans = view.findViewById(R.id.todayTrans);
        todaySynced = view.findViewById(R.id.todaySynced);

        //Yesterday's views
        yesAmount = view.findViewById(R.id.yesterdayAmount);
        yesTrans = view.findViewById(R.id.yesterdayTrans);
        yesSynced = view.findViewById(R.id.yesterdaySynced);

        //Overall views
        ovAmount = view.findViewById(R.id.overallAmount);
        ovTrans = view.findViewById(R.id.overallTrans);
        ovSynced = view.findViewById(R.id.overallSynced);

        //Visibility View
        visibility = view.findViewById(R.id.visibility);
        visibility.setVisibility(View.GONE);

        //Instantiate Database
        db = new Helper().getDb(getContext());



        setView();
        //visibility Toggle
        boolean[] opt = {true};
        visibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(opt[0]){
                    balanceInv.setVisibility(View.GONE);
                    balance.setVisibility(View.VISIBLE);
                }else{
                    balanceInv.setVisibility(View.VISIBLE);
                    balance.setVisibility(View.GONE);
                }
                opt[0] = !opt[0];
            }
        });

    }

    private void setView(){
       new getData().execute();
    }


    public class getData extends AsyncTask<Void, String, String[]>{

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getContext(), "please wait...", Toast.LENGTH_SHORT).show();
            /*dialog = new ProgressDialog(getContext());
            dialog.setMessage("loading...");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();*/

        }

        @Override
        protected String[] doInBackground(Void... voids) {
            //balance query
            String bal = db.getBalance();

            //tellerName query
            String tellerName = db.getUsers().getName();

            //Today's queries
            String tAmount = db.getNTransactions(Helper.getDate())[1];
            String tTrans = db.getNTransactions(Helper.getDate())[0];
            String tSync = db.countSynced(Helper.getDate());

            //Yesterday's queries
            String yAmount = db.getNTransactions(Helper.getPrevDate())[1];
            String yTrans = db.getNTransactions(Helper.getPrevDate())[0];
            String ySync = db.countSynced(Helper.getPrevDate());

            //All Queries
            String oAmount = db.getNTransactions("All")[1];
            String oTotal = db.getNTransactions("All")[0];
            String oSync = db.countSynced("All");


            return new String[]{bal, tAmount, tTrans, yAmount, yTrans, oAmount, tSync, ySync, oSync, oTotal, tellerName};
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String[] data) {
            super.onPostExecute(data);
            //dialog.dismiss();
            visibility.setVisibility(View.VISIBLE);
            balance.setText(data[0]);
            todayAmount.setText(data[1]);
            todayTrans.setText(data[2]);
            yesAmount.setText(data[3]);
            yesTrans.setText(data[4]);
            ovAmount.setText(data[5]);
            todaySynced.setText(data[6]);
            yesSynced.setText(data[7]);
            ovSynced.setText(data[8]);
            ovTrans.setText(data[9]);
            tellerName.setText(data[10]);
        }
    }
}