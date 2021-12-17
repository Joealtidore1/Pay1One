package com.pay1onet.fmca.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkConnectivity {
    Context context;


    public NetworkConnectivity(Context ct) {
        context = ct;
    }


    public  boolean getCm() {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null && ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo().isConnected();
    }

    public void listener(){
        ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).addDefaultNetworkActiveListener(new ConnectivityManager.OnNetworkActiveListener() {
            @Override
            public void onNetworkActive() {

            }
        });
    }
}
