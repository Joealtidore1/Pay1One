package com.pay1onet.fmca.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.pay1onet.fmca.R;
import com.pay1onet.fmca.models.PaymentModel;
import com.pay1onet.fmca.models.RevHeadsModel;

import java.util.List;

public class AddedInvoiceAdapter extends ArrayAdapter<PaymentModel> {

    public AddedInvoiceAdapter(@NonNull Context context, List<PaymentModel> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.generate_invoice_cell, parent, false);
        }

        TextView mRevCode = view.findViewById(R.id.revCode);
        TextView mPriceType = view.findViewById(R.id.priceType);
        TextView mRevHead = view.findViewById(R.id.revHead);
        TextView mAmount = view.findViewById(R.id.amount);
        //Typeface face = Typeface.createFromAsset(getContext().getAssets(), "font/poppins_bold");
        //mRevCode.setTypeface(face);

        PaymentModel item = getItem(position);

        if(item != null){
            mRevCode.setText(item.getRevCode());
            mPriceType.setText(item.getPriceType());
            mRevHead.setText(item.getRevenueHead());
            Log.d("AMOUNT", item.getAmount()+ "");
            mAmount.setText(String.format("%s X \u20a6 %s",item.getQuantity(), item.getAmount()));
        }
        return view;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
