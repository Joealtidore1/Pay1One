package com.pay1onet.fmca.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
import java.util.List;

import static android.view.View.*;

public class EmrFragment extends Fragment implements TextWatcher, OnTouchListener, AdapterView.OnItemSelectedListener, OnClickListener {

    //Dynamic views lists
    List<Spinner> lSPriceType;
    List<TextInputEditText> lEEmr, lEAmount, lEQty;
    List<Button> lBVerifyEmr;

    //Dynamic view parent
    LinearLayout lytDynamic;

    //Payer Information Section Views
    TextInputEditText ePayerName, ePayerPhone, ePayerEmail;
    TextInputLayout payerNameLY;

    //Payment Method and Shift Views
    Spinner sPaymentMethod, sShift;

    //Database Object
    DBHelper db;

    //Database query variables
    private AppDefaultsModel defaults;
    private UserModel userModel;
    private WalletModel wallet;

    //Payment params init
    private String time;
    private String date;
    private String billNo;
    private double balance;
    private View view;

    private TextInputEditText edtAmount;
    private Spinner sPriceType;
    private List<String> paymentMethod;
    private List<String> shift;

    private List<String> priceType;
    private List<String> refPriceType;
    private List<PaymentModel> paymentModels;

    Button btnVerifyEmr, btnPay;
    private TextInputEditText edtQty, edtEmr;
    
    //RevenueHead object for populating payment parameters
    private RevHeadsModel revHeadsByEmr;
    private RelativeLayout emrLY;

    private FloatingActionButton fabAddPayment, fabRemovePayment;
    private double lastAmt;
    private double overAllTotal;
    private String transFee="0";
    private boolean payClicked = true;


    public EmrFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_emr, container, false);
        initViews(view);

        return view;
    }

    public void initViews(View view){
        //TextInputEditText for Payer Info initialization section
        ePayerName = view.findViewById(R.id.payerName);
        ePayerPhone = view.findViewById(R.id.payerPhone);
        ePayerEmail = view.findViewById(R.id.payerEmail);
        payerNameLY = view.findViewById(R.id.payerNameLY);

        //Pay init
        btnPay = view.findViewById(R.id.pay);

        //Form repeater section
        fabAddPayment = view.findViewById(R.id.addPayment);
        fabRemovePayment = view.findViewById(R.id.removePayment);

        fabRemovePayment.setOnClickListener(this);
        fabAddPayment.setOnClickListener(this);

        //hide keyboard
        RelativeLayout rl = view.findViewById(R.id.parentLy);
        setupUI(rl);

        //Spinner for Payer Info Initialization section
        sPaymentMethod = view.findViewById(R.id.paymentMethod);
        sShift = view.findViewById(R.id.shift);

        //Database object initialization
        db = new DBHelper(getContext());

        btnPay.setOnClickListener(this);

        ePayerName.addTextChangedListener(this);

        lytDynamic = view.findViewById(R.id.linearList);


        initListObjects();
        initPermSpinners();
        validateUserDetails();

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
                if (sPaymentMethod.getSelectedItemPosition() == 0 && ePayerName.getText() == null || ePayerName.getText().toString().isEmpty()) {
                    payerNameLY.setError("required");
                    payerNameLY.setErrorEnabled(true);
                    payerNameLY.setErrorTextColor(ColorStateList.valueOf(Color.red(256)));
                }
            }
        });

        sShift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //hideKeyBoardFrom();
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                if (defaults == null) {
                    defaults = db.getAppDefaults();
                }

                if (userModel == null) {
                    userModel = db.getUsers();
                }
                if (wallet == null) {
                    wallet = db.getWallet();
                }
                /*alert.setMessage("Confirm all entries and selections before you proceed " +
                        "\nDo you want to proceed?");*/
                /*if((ePayerEmail.getText()==null || ePayerEmail.getText().toString().isEmpty()) || (ePayerPhone.getText()==null || ePayerPhone.getText().toString().isEmpty())){
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

                    /*disable(ePayerEmail);
                    disable(ePayerName);
                    disable(ePayerPhone);
                    disable(sPaymentMethod);
                    disable(sShift);*/
                    time = Helper.getTime();
                    date = Helper.getDate();
                    billNo = userModel.getMdaCode() + "IV-" + Helper.getRef();
                    balance = db.getWallet().getBalance();

                    addViews();
                     /*   }
                    });*/

                    /*alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            sShift.setSelection(0);
                            dialogInterface.cancel();
                        }
                    });
                    alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            sShift.setSelection(0);
                        }
                    });
                    if(i > 0) {
                        alert.show();
                    }*/
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void disable(View view){
        view.setEnabled(false);
    }

    private void addViews() {
        view = getLayoutInflater().inflate(R.layout.emr_details, null, false);

        //Instantiate dynamic views

        sPriceType = view.findViewById(R.id.priceType);
        lSPriceType.add(sPriceType);

        edtAmount = view.findViewById(R.id.amount);
        lEAmount.add(edtAmount);

        btnVerifyEmr = view.findViewById(R.id.verifyEmr);
        lBVerifyEmr.add(btnVerifyEmr);

        edtEmr = view.findViewById(R.id.emr);
        lEEmr.add(edtEmr);

        emrLY = view.findViewById(R.id.emrLY);


        edtQty = view.findViewById(R.id.quantity);
        lEQty.add(edtQty);

        CustomAdapter adapter = new CustomAdapter(getContext(), priceType);
        sPriceType.setAdapter(adapter);

        sPriceType.setOnTouchListener(this);


        //TextInput input listeners
        lEEmr.get(lEEmr.size()-1).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()>0){
                    lBVerifyEmr.get(lBVerifyEmr.size()-1).setVisibility(VISIBLE);
                }else{
                    lBVerifyEmr.get(lBVerifyEmr.size()-1).setVisibility(GONE);
                }
            }
        });
        lEAmount.get(lEAmount.size()-1).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.length() > 0){
                    btnPay.setEnabled(true);
                }else{
                    btnPay.setEnabled(false);
                }
            }
        });





        //temporarily set edtAmount, sPriceType and sRevHeads to disabled
        edtAmount.setEnabled(false);

        //get click or select actions for dynamically generated views
        /*setDynamicPriceTypeClick(view.findViewById(R.id.emrTL), edtEmr);
        setDynamicEdtEmrClick();*/

        //method call for handling form repeater events
        setDynamicVerifyClick(view.findViewById(R.id.amountTL), view.findViewById(R.id.quantityTL));
        setDynamicPriceType(view.findViewById(R.id.amountTL), view.findViewById(R.id.quantityTL));

        lytDynamic.addView(view);
    }



    public void setDynamicVerifyClick(TextInputLayout edtAmountLY, TextInputLayout edtQtyTL){
        hideKeyBoardFrom(getActivity());
        lBVerifyEmr.get(lBVerifyEmr.size()-1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboardFrom(getContext());
                revHeadsByEmr = db.getRevHeadsByEmr(refPriceType.get(lSPriceType.get(lSPriceType.size() - 1).getSelectedItemPosition() - 1), lEEmr.get(lEEmr.size() - 1).getText().toString());
                if(revHeadsByEmr != null) {
                    if (revHeadsByEmr.getAmount() != null && !revHeadsByEmr.getAmount().isEmpty()) {
                        lEAmount.get(lEAmount.size() - 1).setText(revHeadsByEmr.getAmount());
                        lEAmount.get(lEAmount.size() - 1).setEnabled(false);
                    }else{
                        lEAmount.get(lEAmount.size() - 1).setText("");
                        lEAmount.get(lEAmount.size() - 1).setEnabled(true);
                    }
                    edtAmountLY.setVisibility(VISIBLE);
                    edtQtyTL.setVisibility(VISIBLE);
                    fabAddPayment.setVisibility(VISIBLE);
                }else{
                    showToast("Invalid EMR");
                    fabAddPayment.setVisibility(GONE);
                    lEAmount.get(lEAmount.size() - 1).setText("");
                    lEAmount.get(lEAmount.size() - 1).setEnabled(false);
                }
            }
        });
    }

    public void setDynamicPriceType(TextInputLayout edtAmountLY, TextInputLayout edtQtyTL){
        lSPriceType.get(lSPriceType.size()-1).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0){
                    emrLY.setVisibility(VISIBLE);
                }else{
                    emrLY.setVisibility(GONE);
                }
                lEEmr.get(lEEmr.size()-1).setText(null);
                lEAmount.get(lEAmount.size()-1).setText(null);
                edtQtyTL.setVisibility(GONE);
                edtAmountLY.setVisibility(GONE);
                fabAddPayment.setVisibility(GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void showToast(String msg) {
        Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_LONG);
        View view = toast.getView();
        view.setBackgroundResource(R.color.white);
        TextView tv = view.findViewById(android.R.id.message);
        tv.setTextColor(Color.argb(255, 17, 62, 95));
        tv.setTextSize(22.0f);
        toast.setGravity(Gravity.CENTER, 0, -100);
        toast.show();
    }

    public void hideKeyBoardFrom(Activity activity){
        View view = ((Activity) getContext()).getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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

    //List Instantiation method
    public void initListObjects(){
        paymentMethod = new ArrayList<>();
        shift = new ArrayList<>();
        priceType = new ArrayList<>();
        refPriceType = new ArrayList<>();


        lEEmr = new ArrayList<>();
        lSPriceType = new ArrayList<>();
        lEAmount = new ArrayList<>();
        lEQty = new ArrayList<>();
        lBVerifyEmr = new ArrayList<>();

        paymentModels = new ArrayList<>();
    }

    public void initPermSpinners(){
        if(paymentMethod.size()<1){
            paymentMethod.add("-select payment method-");
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

    }

    private void confirmPayments() {
        addPayment(false);
        overAllTotal = 0;
        for(PaymentModel p : paymentModels){
            overAllTotal += Double.parseDouble(p.getTotal());
        }
        payClicked = true;
        new AlertDialog.Builder(getContext())
                .setMessage("If you proceed \u20a6" + overAllTotal +
                        ", from your account balance")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        pay();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //overAllTotal = overAllTotal - Double.parseDouble(paymentModels.get(paymentModels.size()-1).getTotal());
                        paymentModels.remove(paymentModels.size()-1);
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        overAllTotal = overAllTotal - Double.parseDouble(paymentModels.get(paymentModels.size()-1).getTotal());
                    }
                }).show();
    }

    private void pay() {
        for(PaymentModel p : paymentModels){
            p.setLastSerial(paymentModels.size() + "");
            boolean b = db.addPayment(p);
            if(b){
                if(db.updateWallet(Double.parseDouble(p.getCurrentBalance()))){
                    showToast("done");
                }
            }
        }
        startInvoice();
    }

    private void addPayment(boolean add) {
        int n = lEAmount.size();
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
                        revHeadsByEmr.getRevenueHead(),
                        "0",
                        refNo,
                        wallet.getBalance() +"",
                        currentBal +"",
                        billNo,
                        userModel.getLocation(),
                        defaults.getMdaId()+"",
                        revHeadsByEmr.getRevenueId()+"",
                        userModel.getUserId(),
                        qty +"",
                        transFee,
                        total + "",
                        sPaymentMethod.getSelectedItem().toString(),
                        revHeadsByEmr.getRevenueCode(),
                        "0",
                        revHeadsByEmr.getDept(),
                        revHeadsByEmr.getDepartment(),
                        revHeadsByEmr.getCate(),
                        revHeadsByEmr.getCategory(),
                        "0",
                        total +"",
                        revHeadsByEmr.getSubs(),
                        "",
                        sShift.getSelectedItem().toString(),
                        ""
                )
        );
        balance = currentBal;

        if(add) {
            fabAddPayment.setVisibility(View.GONE);
            fabRemovePayment.setVisibility(VISIBLE);
            int i = lEAmount.size()-1;
            lSPriceType.get(i).setEnabled(false);
            lEQty.get(i).setEnabled(false);
            lEAmount.get(i).setEnabled(false);

            addViews();
        }
    }

    private void removePayment() {
        if(lytDynamic.getChildCount() > 1) {
            lytDynamic.removeViewAt(lytDynamic.getChildCount() - 1);
            int i = lEEmr.size()-1;
            lSPriceType.remove(i);
            lEQty.remove(i);
            lEAmount.remove(i);
            lBVerifyEmr.remove(i);
            lEEmr.remove(i);
            i = lEEmr.size()-1;

            lEEmr.get(i).setEnabled(true);
            lSPriceType.get(i).setEnabled(true);
            lEQty.get(i).setEnabled(true);
            lEAmount.get(i).setEnabled(true);
            lBVerifyEmr.get(i).setEnabled(true);


            paymentModels.remove(paymentModels.size()-1);

            payClicked = false;
            //showToast(overAllTotal +"  " + lastAmt);
            if(lytDynamic.getChildCount() == 1){
                fabRemovePayment.setVisibility(View.GONE);
            }else{
                fabRemovePayment.setVisibility(VISIBLE);
            }
            btnPay.setEnabled(true);
            fabAddPayment.setVisibility(VISIBLE);
            //showToast(lSDepartment.size() +"");
        }
    }

    public void startInvoice(){
        startActivity(new Intent(getContext(), InvoiceActivity.class).putExtra("billNo", billNo));
        //getActivity().finish();
    }




    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(editable.length()>0){
            if(!TextUtils.isEmpty(payerNameLY.getError())){
                payerNameLY.setError(null);
                payerNameLY.setErrorEnabled(false);
            }
            //sShift.setVisibility(VISIBLE);
            view.findViewById(R.id.shiftLY).setVisibility(VISIBLE);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        hideKeyBoardFrom(getActivity());
        return false;
    }

    public void hideKeyboardFrom(Context context) {
        View view = getActivity().getCurrentFocus();
        if(view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i != 0){
            emrLY.setVisibility(VISIBLE);
        }else{
            emrLY.setVisibility(GONE);
            lEEmr.get(lEEmr.size()-1).setText(null);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addPayment:
                addPayment(true);
                break;
            case R.id.removePayment:
                removePayment();
                break;
            case R.id.pay:
                confirmPayments();
                break;
        }
    }


}