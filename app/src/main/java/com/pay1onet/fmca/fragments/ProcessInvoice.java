package com.pay1onet.fmca.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pay1onet.fmca.JSONSchema.BillerSchema;
import com.pay1onet.fmca.R;
import com.pay1onet.fmca.activities.InvoiceActivity;
import com.pay1onet.fmca.adapters.AddedInvoiceAdapter;
import com.pay1onet.fmca.adapters.CustomAdapter;
import com.pay1onet.fmca.adapters.ProcessedBillAdapter;
import com.pay1onet.fmca.database.DBHelper;
import com.pay1onet.fmca.models.BillerModel;
import com.pay1onet.fmca.models.PaymentModel;
import com.pay1onet.fmca.models.RevHeadsModel;
import com.pay1onet.fmca.models.UserModel;
import com.pay1onet.fmca.models.WalletModel;
import com.pay1onet.fmca.network.ApiCall;
import com.pay1onet.fmca.utils.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class ProcessInvoice extends Fragment implements View.OnClickListener {

    ListView mAddedItems, mPaymentInfo;
    TextInputEditText mBillNo;
    Button mProcessInvoice;
    RelativeLayout mQuantityLy;
    List<BillerModel> billers;
    //List<String> spinnerBillers;
    BillerModel biller;
    ProgressBar progressBar;
    TextInputLayout mVoucherTl;
    LinearLayout mSearchLy;
    Spinner mShift, mMethod;

    String date, time;


    List<RevHeadsModel> searchedList;
    List<PaymentModel> addedList;
    private DBHelper db;
    private ProcessedBillAdapter processedBillAdapter;
    int index;
    private String billNo;
    private List<PaymentModel> paymentModels;
    private List<String> paymentMethod;
    private List<String> shift;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_process_invoice, container, false);

        init(view);
        return view;
    }



    private void init(View v) {
        db = new DBHelper(getContext());
        mPaymentInfo = v.findViewById(R.id.paymentInfo);
        mBillNo = v.findViewById(R.id.billNo);
        mProcessInvoice = v.findViewById(R.id.invoiceProcess);
        mVoucherTl = v.findViewById(R.id.voucherTL);
        mSearchLy = v.findViewById(R.id.searchLY);
        progressBar = v.findViewById(R.id.progressBar);
        mMethod = v.findViewById(R.id.paymentMethod);
        mShift = v.findViewById(R.id.shift);


        addedList = new ArrayList<>();
        searchedList = new ArrayList<>();
        billers = new ArrayList<>();
        paymentModels = new ArrayList<>();
        mProcessInvoice.setVisibility(VISIBLE);
        mBillNo.requestFocus();
        showKeyboard();
        date = Helper.getDate();
        time = Helper.getTime();



        mProcessInvoice.setOnClickListener(this);
        mBillNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               /* if(s.length() > 3){
                    searchedList = db.getRevBySearch(s.toString());
                    if(searchedList == null){
                        Toast.makeText(getContext(), s.toString(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), searchedList.size()+"", Toast.LENGTH_SHORT).show();
                    }
                    processedBillAdapter = new ProcessedBillAdapter(getContext(), searchedList);
                    mPaymentInfo.setAdapter(processedBillAdapter);
                    processedBillAdapter.notifyDataSetChanged();
                    mPaymentInfo.setVisibility(VISIBLE);

                }*/
            }
        });

        initPermSpinners();
    }

    public void initPermSpinners(){
        paymentMethod = new ArrayList<>();
        shift = new ArrayList<>();
        if(paymentMethod.size()<1){
            paymentMethod.add("--select payment method--");
            paymentMethod.add("CASH");
            paymentMethod.add("CARD");
            CustomAdapter paymentMethodAdapter = new CustomAdapter(Objects.requireNonNull(getContext()), paymentMethod);
            mMethod.setAdapter(paymentMethodAdapter);
        }

        if(shift.size()<1){
            shift.add("--select shift--");
            shift.add("1");
            shift.add("2");
            shift.add("3");
            CustomAdapter shiftAdapter = new CustomAdapter(Objects.requireNonNull(getContext()), shift);
            mShift.setAdapter(shiftAdapter);
        }

    }



    public void startInvoice(){
        startActivity(new Intent(getContext(), InvoiceActivity.class).putExtra("billNo", billNo));
        //getActivity().finish();
    }

    public void hideKeyBoardFrom(Activity activity){
        View view = ((Activity) getContext()).getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showKeyboard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethod.SHOW_FORCED, 0);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
       if (id == R.id.invoiceProcess) {
           hideKeyBoardFrom(getActivity());
           if(mMethod.getSelectedItemPosition() == 0 ) {
               Toast.makeText(getContext(), "Select Payment method", Toast.LENGTH_SHORT).show();
               return;
           }
           if (mShift.getSelectedItemPosition() == 0) {
               Toast.makeText(getContext(), "Select Shift", Toast.LENGTH_SHORT).show();
               return;
           }
           if(mBillNo.getVisibility() == VISIBLE){
               mVoucherTl.setVisibility(GONE);
               getPayments();
           }else{
               payDialog();
           }

        }
    }


    public boolean checkPermission(){
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.BLUETOOTH}, 1000);
            return false;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == PackageManager.PERMISSION_GRANTED){
                payDialog();
            }else{
                Toast.makeText(getContext(), "Bluetooth permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void payDialog(){
        double amount = 0;
        if(checkPermission()){
            for(PaymentModel p : paymentModels){
                amount += p.getAmount() * Integer.parseInt(p.getQuantity());
            }

            WalletModel walletModel = db.getWallet();
            if (walletModel.getBalance() < amount) {
                new AlertDialog.Builder(getContext()).setMessage("Insufficient funds\nPlease recharge and try again").setNegativeButton("Okay", (dialog, i) ->{
                     dialog.dismiss();
                }).show();
                return;
            }

            new AlertDialog.Builder(getContext())
                .setMessage("If you proceed \u20a6 " + amount +
                        " will be deducted from your account balance")
                .setPositiveButton("yes", (dialog, i) ->{

                    if(paymentModels != null && paymentModels.size()>0) {
                        for (PaymentModel p : paymentModels) {
                            if(db.addPayment(p)){
                                db.updateWallet(Double.parseDouble(p.getCurrentBalance()));
                            }
                        }
                        startInvoice();
                    }
                })
                .setNegativeButton("no", (dialog, i)-> {
                    Toast.makeText(getContext(), "cancelled", Toast.LENGTH_SHORT).show();
                }).show();
        }
    }

    public void getPayments(){
        if(mBillNo.getEditableText().length() == 0){
            Toast.makeText(getContext(), "Bill Id field cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }


        progressBar.setVisibility(VISIBLE);

        if(db.getPaymentByBillNo(Objects.requireNonNull(mBillNo.getText()).toString()).size() > 0){
            new AlertDialog.Builder(getContext())
                    .setMessage("This Bill has been processed already.")
                    .setNegativeButton("Okay", (v, i)-> v.cancel());
        }else{

            new ApiCall().getPaymentByBill(Objects.requireNonNull(getContext()), mBillNo.getEditableText().toString(), new ApiCall.getPaymentsCallBack() {
                @Override
                public void onSuccess(BillerSchema schema) {
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                    UserModel model = db.getUsers();
                    WalletModel walletModel = db.getWallet();
                    double preBal = walletModel.getBalance();
                    String method = mMethod.getSelectedItem().toString();
                    String shift = mShift.getSelectedItem().toString();
                    Helper help = new Helper();
                    int i = 0;
                    for(BillerSchema.GeneratedBill gb : schema.getGeneratedBill()){
                        PaymentModel paymentModel = help.schemaToModel(gb, time, model, String.valueOf(preBal), walletModel, method, db);
                        paymentModel.setShift(shift);
                        paymentModel.setMethod(method);
                        paymentModel.setLastSerial(String.valueOf(++i));
                        paymentModels.add(paymentModel);
                        preBal -= Double.parseDouble(gb.getAmount());
                    }

                    if(schema.getGeneratedBill().size() > 0)
                        billNo = schema.getGeneratedBill().get(0).getBillno();
                    AddedInvoiceAdapter adapter = new AddedInvoiceAdapter(Objects.requireNonNull(getContext()), paymentModels);
                    mPaymentInfo.setAdapter(adapter);
                    progressBar.setVisibility(GONE);
                    mBillNo.setVisibility(GONE);
                    mVoucherTl.setVisibility(VISIBLE);

                }

                @Override
                public void onFailure(String errorMessage) {
                    mVoucherTl.setVisibility(VISIBLE);
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(GONE);
                    mBillNo.setVisibility(VISIBLE);
                }
            });
        }
    }

    public void prepareDataForDB(){
        if(sendDataToDB(paymentModels.get(0))) {
            if(db.updateWallet(Double.parseDouble(paymentModels.get(0).getCurrentBalance()))){
                paymentModels.remove(0);
                if (paymentModels.size() > 0) {
                    prepareDataForDB();
                }
            }

        }
    }

    public boolean sendDataToDB(PaymentModel model){
        return db.addPayment(model);
    }

    public void addItem(RevHeadsModel model, int q, double amount){
        mAddedItems.setVisibility(VISIBLE);
        String ref = biller.getBillerId()+time+date+"BI";
        addedList.add(
                new PaymentModel(
                        0,
                        amount,
                        biller.getName(),
                        biller.getPhone(),
                        biller.getEmail(),
                        date,
                        time,
                        biller.getBillerId(),
                        model.getRevenueHead(),
                        "0",
                        ref,
                        "",
                        "",
                        billNo,
                        "",
                        "",
                        model.getRevenueId()+"",
                        biller.getBillerId(),
                        q + "",
                        0+"",
                        amount*q+"",
                        "",
                        model.getRevenueCode(),
                        "0",
                        model.getDept(),
                        model.getDepartment(),
                        model.getCate(),
                        "",
                        "0",
                        amount + "",
                        model.getSubs(),
                        null,
                        "",
                        null
                )
        );
        AddedInvoiceAdapter adapter = new AddedInvoiceAdapter(Objects.requireNonNull(getContext()), addedList);
        mAddedItems.setAdapter(adapter);

        //Toast.makeText(getContext(), addedList.size() + "fgf", Toast.LENGTH_SHORT).show();
    }


}