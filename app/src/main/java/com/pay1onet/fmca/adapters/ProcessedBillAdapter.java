package com.pay1onet.fmca.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pay1onet.fmca.R;
import com.pay1onet.fmca.models.RevHeadsModel;

import java.util.List;

public class ProcessedBillAdapter extends ArrayAdapter<RevHeadsModel> {

    public ProcessedBillAdapter(@NonNull Context context, List<RevHeadsModel> items) {
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

        RevHeadsModel item = getItem(position);
        if(item != null){
            mRevCode.setText(item.getRevenueCode());
            mPriceType.setText("");
            mRevHead.setText(item.getRevenueHead());
            if(item.getAmount() != null)
                mAmount.setText(String.format("\u20a6 %s", item.getAmount()));
            else mAmount.setText("");
        }
        return view;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
