package com.pay1onet.fmca.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pay1onet.fmca.R;
import com.pay1onet.fmca.activities.InvoiceActivity;
import com.pay1onet.fmca.adapters.CustomAdapter;
import com.pay1onet.fmca.database.DBHelper;
import com.pay1onet.fmca.models.AppDefaultsModel;
import com.pay1onet.fmca.models.PaymentModel;
import com.pay1onet.fmca.models.RevHeadsModel;
import com.pay1onet.fmca.models.UserModel;
import com.pay1onet.fmca.models.WalletModel;
import com.pay1onet.fmca.utils.Helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.view.View.VISIBLE;


public class PaymentsFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    TextInputEditText ePayerName, ePayerPhone, ePayerEmail;
    Spinner sPaymentMethod, sShift;
    FloatingActionButton fabAddPayments, fabRemovePayments;
    Button btnPay;
    DBHelper db;
    boolean payClicked=false;

    //Dynamic Views
    Spinner sDept, sRevHeads, sPriceType;
    TextInputEditText edtAmount, edtQty;
    LinearLayout lytDynamic;

    //Spinner data Objects
    List<String> departments;
    List<String> paymentMethod;
    List<String> shift;
    List<String> revheads;
    List<String> priceType;

    //Dynamic views list
    List<Spinner> lSDepartment, lSPriceType, lSRevHead;
    List<TextInputEditText> lEAmount, lEQty;

    //price type key reference object;
    List<String> refPriceType;

    //Payment List object for making payments
    List<PaymentModel> paymentModels;

    //User model object and Wallet model
    UserModel userModel;
    WalletModel wallet;

    //revenue head global adapter to interact with department and revhead background tasks
    CustomAdapter revHeadAdapter;

    //Bill Number, Date and time variable
    String date, time, billNo;
    private AppDefaultsModel defaults;
    private String transFee = "0";

    //balance and overall transaction cost variables
    double balance, overAllTotal = 0.0, lastAmt;
    private View view;
    private TextInputLayout payerNameLY;


    public PaymentsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payments, container, false);
        initViews(view);

        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    public void initViews(View view){
        //Payer Details View
        ePayerName = view.findViewById(R.id.payerName);
        ePayerEmail = view.findViewById(R.id.payerEmail);
        ePayerPhone = view.findViewById(R.id.payerPhone);

        RelativeLayout rl = view.findViewById(R.id.parentLy);
        setupUI(rl);
        /*rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyBoardFrom();
                return false;
            }
        });*/



        //payment method/shift view
        sPaymentMethod = view.findViewById(R.id.paymentMethod);
        sShift = view.findViewById(R.id.shift);
        payerNameLY = view.findViewById(R.id.payerNameLY);

        sShift.setEnabled(false);

        sShift.setOnTouchListener(this);
        sPaymentMethod.setOnTouchListener(this);

        //button view
        fabAddPayments = view.findViewById(R.id.addPayment);
        fabRemovePayments = view.findViewById(R.id.removePayment);
        btnPay = view.findViewById(R.id.pay);

        //dynamic linearlayout holder
        lytDynamic = view.findViewById(R.id.linearList);

        //db injection
        db = new Helper().getDb(getContext());

        ePayerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() > 0){
                    if(!TextUtils.isEmpty(payerNameLY.getError())){
                        payerNameLY.setError(null);
                        payerNameLY.setErrorEnabled(false);
                    }
                    view.findViewById(R.id.shiftLY).setVisibility(VISIBLE);
                }else{
                    //view.findViewById(R.id.shiftLY).setVisibility(View.GONE);
                }
            }
        });



        setViewClicks();
        validateUserDetails();
        initListObjects();
        initPermSpinners();
    }

    public void validateUserDetails(){
        sPaymentMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //hideKeyBoardFrom();
                //sShift.setEnabled(false);
                //sShift.setVisibility(View.GONE);
                if(i != 0) {
                    if (ePayerName.getText() == null || ePayerName.getText().toString().isEmpty()) {
                        payerNameLY.setError("required");
                        payerNameLY.setErrorEnabled(true);
                        payerNameLY.setErrorTextColor(ColorStateList.valueOf(Color.red(256)));
                        //showToast("Enter Payer Name first");
                        sPaymentMethod.setSelection(0);
                    } else {
                        sShift.setVisibility(VISIBLE);
                        sShift.setEnabled(true);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sShift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //hideKeyBoardFrom();
                //AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                if (defaults == null) {
                    defaults = db.getAppDefaults();
                }

                if (userModel == null) {
                    userModel = db.getUsers();
                }
                if (wallet == null) {
                    wallet = db.getWallet();
                }
              /*  alert.setMessage("Confirm all entries and selections before you proceed " +
                        "\nDo you want to proceed?");
                if((ePayerEmail.getText()==null || ePayerEmail.getText().toString().isEmpty()) || (ePayerPhone.getText()==null || ePayerPhone.getText().toString().isEmpty())){
                            alert.setMessage("Confirm all entries and selections before you proceed " +
                                    "\n\n" +
                                    "* You did not add a phone Number or an email.\n" +
                                    "Default settings will be used instead. \n\n" +
                                    "Do you want to proceed?");


                }*/
                /*alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {*/
                if(i > 0){
                    if (ePayerEmail.getText() == null || ePayerEmail.getText().toString().isEmpty()) {
                        ePayerEmail.setText(defaults.getEmail());
                    }
                    if (ePayerPhone.getText() == null || ePayerPhone.getText().toString().isEmpty()) {
                        ePayerPhone.setText(defaults.getPhone());
                    }
                    disable(ePayerEmail);
                    disable(ePayerName);
                    disable(ePayerPhone);
                    disable(sPaymentMethod);
                    disable(sShift);
                    time = Helper.getTime();
                    date = Helper.getDate();
                    billNo = userModel.getMdaCode() + "IV-" + Helper.getRef();
                    balance = db.getWallet().getBalance();

                    addViews();

                }

                //});

                /*alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sShift.setSelection(0);
                        sShift.setSelection(0);
                        dialogInterface.cancel();
                    }
                });*/
                /*alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        sShift.setSelection(0);
                    }
                });*/
                /*if(i > 0) {
                    alert.show();
                }*/

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void disable(View view){
        view.setEnabled(false);
    }

    public void setViewClicks(){
        fabAddPayments.setOnClickListener(this);
        fabRemovePayments.setOnClickListener(this);
        btnPay.setOnClickListener(this);
    }

    public void initPermSpinners(){
        if(paymentMethod.size()<1){
            paymentMethod.add("--select payment method--");
            paymentMethod.add("CASH");
            paymentMethod.add("CARD");
            CustomAdapter paymentMethodAdapter = new CustomAdapter(getContext(), paymentMethod);
            sPaymentMethod.setAdapter(paymentMethodAdapter);
        }

        if(shift.size()<1){
            shift.add("--select shift--");
            shift.add("1");
            shift.add("2");
            shift.add("3");
            CustomAdapter shiftAdapter = new CustomAdapter(getContext(), shift);
            sShift.setAdapter(shiftAdapter);
        }

    }


    //dynamic spinner onItemselectedListener methods
    public void setDynamicDepartmentClick(){
        sDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0) {
                    sPriceType.setEnabled(true);
                    sPriceType.setSelection(0);
                }
                else
                    sPriceType.setEnabled(false);

                sRevHeads.setEnabled(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void setDynamicPriceTypeClick(){
        sPriceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0){
                    edtAmount.setText(null);
                    edtAmount.setEnabled(false);
                    sRevHeads.setEnabled(false);
                    new getRevHeads().execute(lSDepartment.get(lSDepartment.size()-1).getSelectedItem().toString(), refPriceType.get(lSPriceType.get(lSPriceType.size()-1).getSelectedItemPosition()-1));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /*sPriceType.setOnItemSelectedListener(this);
        sRevHeads.setOnItemSelectedListener(this);*/
    }

    private void setDynamicRevenueHeadClick(TextInputLayout edtAmounts, TextInputLayout edtQty) {
        sRevHeads.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0){
                    fabAddPayments.setVisibility(VISIBLE);
                    btnPay.setEnabled(true);
                    edtAmounts.setVisibility(VISIBLE);
                    edtQty.setVisibility(VISIBLE);
                    edtAmount.setEnabled(false);
                    String revenueAmount = db.getRevenueAmount(departments.get(sDept.getSelectedItemPosition()),
                            refPriceType.get(sPriceType.getSelectedItemPosition()-1).toString(),
                            revheads.get(sRevHeads.getSelectedItemPosition()));
                    if(revenueAmount != null){
                        PaymentsFragment.this.edtAmount.setText(revenueAmount);
                        PaymentsFragment.this.edtAmount.setEnabled(false);
                    }else{
                        PaymentsFragment.this.edtAmount.setEnabled(true);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    //List Instantiation method
    public void initListObjects(){
        paymentMethod = new ArrayList<>();
        shift = new ArrayList<>();
        priceType = new ArrayList<>();
        refPriceType = new ArrayList<>();
        paymentModels = new ArrayList<>();

        lSDepartment = new ArrayList<>();
        lSPriceType = new ArrayList<>();
        lSRevHead = new ArrayList<>();
        lEAmount = new ArrayList<>();
        lEQty = new ArrayList<>();
    }

    //onClick implementation for Buttons
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.addPayment:
                addPayments(true);
                break;
            case R.id.pay:
                confirmPayment();
                break;
            case R.id.removePayment:
                removePayment();
        }
    }

    private void removePayment() {
        if(lytDynamic.getChildCount() > 1) {
            lytDynamic.removeViewAt(lytDynamic.getChildCount() - 1);
            int i = lSDepartment.size()-1;
            lSDepartment.remove(i);
            lSPriceType.remove(i);
            lSRevHead.remove(i);
            lEQty.remove(i);
            lEAmount.remove(i);

            i = lSDepartment.size()-1;


            lSDepartment.get(i).setEnabled(true);
            lSPriceType.get(i).setEnabled(true);
            lSRevHead.get(i).setEnabled(true);
            lEQty.get(i).setEnabled(true);
            lEAmount.get(i).setEnabled(true);


            paymentModels.remove(paymentModels.size()-1);

            payClicked = false;
            //showToast(overAllTotal +"  " + lastAmt);
            if(lytDynamic.getChildCount() == 1){
                fabRemovePayments.setVisibility(View.GONE);
            }
            fabAddPayments.setVisibility(VISIBLE);
            //showToast(lSDepartment.size() +"");
        }
    }

    public void hideKeyBoardFrom(Activity activity){
        View view = ((Activity) getContext()).getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof TextInputEditText)) {
            view.setOnTouchListener(this);
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    private void addPayments(boolean add) {
        //hideKeyBoardFrom();
        payClicked = false;
        int n = lSDepartment.size();


        if(lSDepartment.get(n-1).getSelectedItemPosition() == 0){
            showToast("select a department first");
            return;
        }
        if(lSPriceType.get(n-1).getSelectedItemPosition() == 0){
            showToast("select a price type first");
            return;
        }
        if(lSRevHead.get(n-1).getSelectedItemPosition() == 0){
            showToast("select a revenue head first");
            return;
        }
        if(lEAmount.get(n-1).getText() == null || lEAmount.get(n-1).getText().toString().isEmpty()){
            showToast("Enter Amount");
            return;
        }

        int qty;
        if(lEQty.get(n-1).getText() == null && lEQty.get(n-1).getText().toString().isEmpty()){
            qty = 1;
        }else{
            qty = Integer.parseInt(lEQty.get(n-1).getText().toString());
        }
        double amount = Double.parseDouble(lEAmount.get(n-1).getText().toString());
        double total = amount * qty;
        lastAmt = total;
        overAllTotal += total;
        double currentBal = balance - total;
        String refNo = userModel.getMdaCode()+ "-" + Helper.getRef();

        RevHeadsModel rHMod=db.getRevenueHead(departments.get(lSDepartment.get(n-1).getSelectedItemPosition()),
                refPriceType.get(lSPriceType.get(n-1).getSelectedItemPosition()-1),
                lSRevHead.get(lSRevHead.size()-1)
                        .getSelectedItem().toString()
                        );
        if(rHMod == null){
            showToast("revenue head not found");
            return;
        }
        paymentModels.add(
                new PaymentModel(
                        0,
                        amount,
                        ePayerName.getText().toString(),
                        ePayerPhone.getText().toString(),
                        ePayerEmail.getText().toString(),
                        date,
                        time,
                        wallet.getAgent(),
                        rHMod.getRevenueHead(),
                        "0",
                        refNo,
                        wallet.getBalance() +"",
                        currentBal +"",
                        billNo,
                        userModel.getLocation(),
                        defaults.getMdaId()+"",
                        rHMod.getRevenueId()+"",
                        userModel.getUserId(),
                        qty +"",
                        transFee,
                        total + "",
                        sPaymentMethod.getSelectedItem().toString(),
                        rHMod.getRevenueCode(),
                        "0",
                        rHMod.getDept(),
                        rHMod.getDepartment(),
                        rHMod.getCate(),
                        rHMod.getCategory(),
                        "0",
                        total +"",
                        rHMod.getSubs(),
                        "",
                        sShift.getSelectedItem().toString(),
                        ""
                )
        );
        balance = currentBal;

        if(add) {
            fabAddPayments.setVisibility(View.GONE);
            fabRemovePayments.setVisibility(VISIBLE);
            int i = lSDepartment.size()-1;
            lSDepartment.get(i).setEnabled(false);
            lSPriceType.get(i).setEnabled(false);
            lSRevHead.get(i).setEnabled(false);
            lEQty.get(i).setEnabled(false);
            lEAmount.get(i).setEnabled(false);

            addViews();
        }
    }

    private void showToast(String msg) {
        Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_LONG);
        View view = toast.getView();
        view.setBackgroundResource(android.R.color.white);
        TextView tv = view.findViewById(android.R.id.message);
        tv.setTextColor(Color.argb(255, 17, 62, 95));
        tv.setTextSize(22.0f);
        toast.setGravity(Gravity.CENTER, 0, -100);
        toast.show();
    }

    private void confirmPayment() {
        //hideKeyBoardFrom();
        addPayments(false);
        overAllTotal = 0;
        for(PaymentModel p : paymentModels){
            overAllTotal += Double.parseDouble(p.getTotal());
        }
        payClicked = true;
        new AlertDialog.Builder(getContext())
                .setMessage("If you proceed \u20a6" + overAllTotal +
                        ", from your account balance")
                .setPositiveButton("yes", (dialog, i)-> {
                    pay();
                })
                .setNegativeButton("no", (dialog, i)-> {
                        //overAllTotal = overAllTotal - Double.parseDouble(paymentModels.get(paymentModels.size()-1).getTotal());
                        paymentModels.remove(paymentModels.size()-1);
                })
                .setOnCancelListener((dialog)-> {
                        overAllTotal = overAllTotal - Double.parseDouble(paymentModels.get(paymentModels.size()-1).getTotal());
                }).show();

    }

    private void pay() {
        for(PaymentModel p : paymentModels){
            p.setLastSerial(paymentModels.size() + "");
            boolean b = db.addPayment(p);
            if(b){
                if(db.updateWallet(Double.parseDouble(p.getCurrentBalance()))){
                    //showToast("done");
                }
            }
        }
        startInvoice();
    }

    public void startInvoice(){
        startActivity(new Intent(getContext(), InvoiceActivity.class).putExtra("billNo", billNo));
        //getActivity().finish();
    }

    private void addViews() {
        view = getLayoutInflater().inflate(R.layout.payment_details, null, false);

        //Instantiate dynamic views
        sDept = view.findViewById(R.id.department);
        lSDepartment.add(sDept);

        sRevHeads = view.findViewById(R.id.revHeads);
        lSRevHead.add(sRevHeads);

        sPriceType = view.findViewById(R.id.priceType);
        lSPriceType.add(sPriceType);

        edtAmount = view.findViewById(R.id.amount);
        lEAmount.add(edtAmount);

        edtQty = view.findViewById(R.id.quantity);
        lEQty.add(edtQty);

        sDept.setOnTouchListener(this);
        sRevHeads.setOnTouchListener(this);
        sPriceType.setOnTouchListener(this);

        //temporarily set edtAmount, sPriceType and sRevHeads to disabled
        edtAmount.setEnabled(false);
        sPriceType.setEnabled(false);
        sRevHeads.setEnabled(false);

        //get click or select actions for dynamically generated views
        setDynamicDepartmentClick();
        setDynamicPriceTypeClick();
        setDynamicRevenueHeadClick(view.findViewById(R.id.amountTL), view.findViewById(R.id.quantityTL));


        new getDepartments().execute();

        lytDynamic.addView(view);
    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        hideKeyBoardFrom(getActivity());
        return false;
    }


    public class getDepartments extends AsyncTask<Void,  Void, String>{

        ProgressDialog dialog=new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //dialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            departments = db.getDeptNames();
            String val = "";
            if(paymentMethod.size()<1){
                paymentMethod.add("--select payment method--");
                paymentMethod.add("CASH");
                paymentMethod.add("CARD");
                val = val.concat("1");
            }

            if(shift.size()<1){
                shift.add("--select shift--");
                shift.add("1");
                shift.add("2");
                shift.add("3");
                val = val.concat("2");
            }

            if(priceType.size() < 1){
                priceType.add("--select price type--");
                priceType.add("Default");
                priceType.add("Hospital Child");
                priceType.add("Staff");
                priceType.add("Non Hospital Child");
                priceType.add("Non Hospital Adult");
                priceType.add("CMPC LOC");
            }

            if(refPriceType.size() < 1){
                refPriceType.add("default");
                refPriceType.add("hoschild");
                refPriceType.add("staff");
                refPriceType.add("nonhoschild");
                refPriceType.add("nonhosadult");
                refPriceType.add("cmpc");
            }

            revheads = new ArrayList<>();
            revheads.add("--select revenue head--");

            return val;
        }

        @Override
        protected void onPostExecute(String val) {
            super.onPostExecute(val);
            CustomAdapter departmentAdapter = new CustomAdapter(getContext(), departments);
            sDept.setAdapter(departmentAdapter);

            CustomAdapter priceTypeAdapter = new CustomAdapter(getContext(), priceType);
            sPriceType.setAdapter(priceTypeAdapter);
            revHeadAdapter = new CustomAdapter(getContext(), revheads);
            sRevHeads.setAdapter(revHeadAdapter);
            dialog.dismiss();


        }
    }

    public class getRevHeads extends AsyncTask<String, Void,Void>{

        ProgressDialog dialog=new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected Void doInBackground(String... strings) {
            revheads = null;
            revheads = db.getRevHeadByDept(strings[0], strings[1]);
            Collections.sort(revheads);
            revheads.add(0, "--select revenue head--");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            //revHeadAdapter.notifyDataSetChanged();
            sRevHeads.setEnabled(true);
           revHeadAdapter = new CustomAdapter(getContext(), revheads);
            sRevHeads.setAdapter(revHeadAdapter);
        }
    }
}