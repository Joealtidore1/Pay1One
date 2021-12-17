package com.pay1onet.fmca.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pay1onet.fmca.database.DBHelper;
import com.pay1onet.fmca.R;
import com.pay1onet.fmca.models.UserModel;
import com.pay1onet.fmca.network.ApiCall;

public class WalletFragment extends Fragment implements View.OnClickListener {

    TextView balance, bal_hidden, teller_name;
    TextInputEditText voucher;
    Button recharge;
    ImageView visibility;
    private View view;
    TextInputLayout voucherTL;
    String currentBalance;
    DBHelper db;

    boolean visibleBalance = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wallet, container, false);

        initViews(view);

        return view;
    }

    public void initViews(View view){
        balance = view.findViewById(R.id.balanceTV);
        bal_hidden = view.findViewById(R.id.balanceTVINV);
        teller_name = view.findViewById(R.id.tellerName);
        recharge = view.findViewById(R.id.recharge);
        voucher = view.findViewById(R.id.rechargeVoucher);
        visibility = view.findViewById(R.id.visibility);
        voucherTL = view.findViewById(R.id.voucherTL);

        db = new DBHelper(getContext());

        currentBalance = db.getBalance();
        UserModel user = db.getUsers();
        balance.setText(currentBalance);
        teller_name.setText(user.getName());

        recharge.setOnClickListener(this);
        visibility.setOnClickListener(this);
    }

    public void performRecharge(){
        Log.d("BAL", currentBalance);
        if(voucher.getEditableText().length()<1){
            voucherTL.setError("Voucher is required");

            voucherTL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
        }else{
            recharge.setEnabled(false);
            ProgressDialog dialog = new ProgressDialog(getContext());
            dialog.setMessage("Loading");
            dialog.show();
            String bal = currentBalance.substring(1).trim();
            new ApiCall().recharge(getContext(), voucher.getText().toString(), Double.parseDouble(bal), new ApiCall.RechargeCallBack() {
                @Override
                public void onSuccess(double balance) {
                    dialog.cancel();
                    Log.d("VOUCHER BAL", balance + "");
                    recharge.setEnabled(true);
                    Log.d("FINAL BAL", balance + " Naira");

                    if (db.updateWallet(balance)) {
                        WalletFragment.this.balance.setText(db.getBalance());
                        toggleVisibility(false);
                        Toast.makeText(getContext(), "recharge of " + balance + " was successful", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(String message) {
                    dialog.cancel();
                    recharge.setEnabled(true);
                    Log.d("BAL", message);
                    showToast(message);
                    Log.d("DB BAL", Double.parseDouble(bal) + " NAIRA");
                }
            });
        }
    }

    public void hideKeyBoardFrom(Activity activity){
        View view = ((Activity) getContext()).getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void toggleVisibility(boolean b) {
        if(b){
            balance.setVisibility(View.GONE);
            bal_hidden.setVisibility(View.VISIBLE);
            visibility.setImageResource(R.drawable.visible);
        }else{
            bal_hidden.setVisibility(View.GONE);
            balance.setVisibility(View.VISIBLE);
            visibility.setImageResource(R.drawable.invisible);
        }
        visibleBalance = !visibleBalance;
    }

    private void showToast(String msg) {
        Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, -100);
        toast.show();
    }

    public void toggleVisibility(){
        toggleVisibility(visibleBalance);
    }


    @Override
    public void onClick(View view) {
        hideKeyBoardFrom(getActivity());
        int id = view.getId();
        if (id == R.id.visibility) {
            toggleVisibility();
        } else if (id == R.id.recharge) {
            performRecharge();
        }
    }



}