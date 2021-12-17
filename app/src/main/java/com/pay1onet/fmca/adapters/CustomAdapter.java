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

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {
    /**
     * Constructor
     *
     * @param context  The current context.
     *
     */
    public CustomAdapter(@NonNull Context context, List<String> customItem) {
        super(context, 0, customItem);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner, parent, false);
        }
        String item = getItem(position);
        TextView textTV = convertView.findViewById(R.id.spinnerText);
        if(item != null){
            textTV.setText(item);
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_dropdown, parent, false);
        }
        String item = getItem(position);
        TextView textTV = convertView.findViewById(R.id.spinnerText);
        if(item != null){
            textTV.setText(item);
        }

        return convertView;
    }
}
