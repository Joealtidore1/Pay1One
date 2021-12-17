package com.pay1onet.fmca.utils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BluetoothHelper extends Activity{
    static BluetoothAdapter bt;
    private static final int REQUEST_ON = 1000;
    private static final int REQUEST_DISCOVERY = 2000;
    Context context;
    public BluetoothHelper(Context context){
    }

    public boolean initBluetooth(){
        if (bt==null) {
            bt = BluetoothAdapter.getDefaultAdapter();
        }

        if (bt==null) {
            return false;
        }
        if(!bt.isEnabled()){
            startActivityForResult(
                    new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQUEST_ON
            );
            return false;
        }
        if(!bt.isDiscovering()){
            startActivityForResult(
                    new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE),
                    REQUEST_DISCOVERY
            );
            return false;
        }


        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_ON:
                if(resultCode == RESULT_OK){
                    initBluetooth();
                }else{
                    showToast("bluetooth request denied");
                }
                break;
            case REQUEST_DISCOVERY:
                if(resultCode == RESULT_OK){
                    initBluetooth();
                }else{
                    showToast("bluetooth discoverability denied");
                }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    public void showToast(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public BluetoothAdapter getBt(){
        if(initBluetooth()){
            if(bt != null)
                return bt;
            else
                return BluetoothAdapter.getDefaultAdapter();
        }

        return null;
    }
}
