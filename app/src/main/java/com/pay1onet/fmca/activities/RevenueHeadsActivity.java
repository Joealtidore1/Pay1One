package com.pay1onet.fmca.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pay1onet.fmca.R;
import com.pay1onet.fmca.database.DBHelper;

import java.util.List;

public class RevenueHeadsActivity extends AppCompatActivity {

    ListView revenueHeadsList;
    LinearLayout empty_view;
    DBHelper db;

    List<String> revenueHeadsModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue_heads);
        init();
    }

    public void init(){
        db = new DBHelper(getApplicationContext());
        revenueHeadsList = findViewById(R.id.revenueHeadsList);
        empty_view = findViewById(R.id.empty);


        new getDepartments().execute();
    }

    public void pressBack(View view) {
        super.onBackPressed();
    }


    private class getDepartments extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            revenueHeadsModels = db.getRevHeadByDept(getIntent().getStringExtra("dept"));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(revenueHeadsModels.size()>0) {
                empty_view.setVisibility(View.GONE);
                RevenueHeadsAdapter adapter = new RevenueHeadsAdapter(getApplicationContext(), revenueHeadsModels);
                revenueHeadsList.setAdapter(adapter);
            }else{
                empty_view.setVisibility(View.VISIBLE);
            }
        }
    }


    private class RevenueHeadsAdapter extends ArrayAdapter<String> {
        public RevenueHeadsAdapter(Context context, List<String> revenueHeadsModel) {
            super(context, 0, revenueHeadsModel);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

            if(view == null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.custom_departments, null, false);
            }

            TextView revName = view.findViewById(R.id.deptName);

            String model = revenueHeadsModels.get(position);
            if(model != null){
                //deptCode.setText(model.getDeptAbbr());
                revName.setText(model);
            }

            return view;
        }

    }
}