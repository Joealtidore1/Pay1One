package com.pay1onet.fmca.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.CircularArray;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pay1onet.fmca.R;
import com.pay1onet.fmca.activities.InvoiceActivity;
import com.pay1onet.fmca.adapters.CustomAdapter;
import com.pay1onet.fmca.database.DBHelper;
import com.pay1onet.fmca.models.PaymentModel;
import com.pay1onet.fmca.utils.Helper;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class SearchFragment extends Fragment implements View.OnClickListener, TextWatcher, AdapterView.OnItemSelectedListener {
    Spinner dateSpinner;
    EditText searchString;
    ImageButton searchButton;
    RelativeLayout emptyRL;
    TextView dateTV;

    private List<String> dates;
    ListView listView;
    String date = null;

    DBHelper db;
    private List<PaymentModel> paymentModel;
    private View view;
    private ListAdapter adapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search, container, false);

        init(view);
        return view;
    }

    public void init(View view){
        db = new DBHelper(getContext());
        dateSpinner = view.findViewById(R.id.dates);
        searchButton = view.findViewById(R.id.search);
        searchString = view.findViewById(R.id.searchString);
        listView = view.findViewById(R.id.paymentList);
        emptyRL = view.findViewById(R.id.emptyViewRL);
        dateTV = view.findViewById(R.id.date);

        dateTV.setText(Helper.getDate());

        new getDates().execute();
        searchButton.setOnClickListener(this);

        searchString.addTextChangedListener(this);

        dateSpinner.setOnItemSelectedListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startInvoice(paymentModel.get(i).getRrr());
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        searchString.setText("");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        searchString.setText("");
    }

    @Override
    public void onResume() {
        super.onResume();
        //init(view);
    }


    private void search(){
        new getSearchData().execute(searchString.getText().toString(), null);
    }

    @Override
    public void onClick(View view) {
        searchButton.setEnabled(false);
        search();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    public void startInvoice(String billNo){
        startActivity(new Intent(getContext(), InvoiceActivity.class).putExtra("billNo", billNo));
        //getActivity().finish();
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(editable.length() < 1){
            searchButton.setVisibility(GONE);
        }else{
            searchButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(dateSpinner.getSelectedItemPosition() > 0){
            new getSearchData().execute(searchString.getText().toString(), dateSpinner.getSelectedItem().toString());
        }else{
            if(adapter != null){
                adapter.clear();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class getDates extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dates = db.getPaymentDates();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (dates == null) {
                dates = new ArrayList<>();
            }
            if(dates.size() > 0 && !dates.get(0).equalsIgnoreCase(Helper.getDate())) {
                dates.add(0, Helper.getDate());
            }
            dates.add(0, "--search by date--");
            CustomAdapter adapter = new CustomAdapter(getContext(), dates);
            dateSpinner.setAdapter(adapter);
        }
    }

    public class getSearchData extends AsyncTask<String, Void, List<PaymentModel>>{
        ProgressDialog dialog = new ProgressDialog(getContext());
        @Override
        protected List<PaymentModel> doInBackground(String... params) {
            paymentModel = db.getPaymentsByDateAndSearch(params[0], params[1]);
            return paymentModel;
        }


        @Override
        protected void onPostExecute(List<PaymentModel> o) {
            super.onPostExecute(o);
            adapter = new ListAdapter(getContext(), o);
            listView.setAdapter(adapter);
            dialog.cancel();
            searchButton.setEnabled(true);
            emptyRL.setVisibility(GONE);
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

            PaymentModel paymentModel = SearchFragment.this.paymentModel.get(position);

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
