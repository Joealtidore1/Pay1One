package com.pay1onet.fmca.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dantsu.escposprinter.EscPosPrinter;
import com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections;
import com.dantsu.escposprinter.exceptions.EscPosBarcodeException;
import com.dantsu.escposprinter.exceptions.EscPosConnectionException;
import com.dantsu.escposprinter.exceptions.EscPosEncodingException;
import com.dantsu.escposprinter.exceptions.EscPosParserException;
import com.dantsu.escposprinter.textparser.PrinterTextParserImg;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pay1onet.fmca.R;
import com.pay1onet.fmca.adapters.CustomAdapter;
import com.pay1onet.fmca.adapters.DeviceInfo;
import com.pay1onet.fmca.database.DBHelper;
import com.pay1onet.fmca.models.PaymentModel;
import com.pay1onet.fmca.models.UserModel;
import com.pay1onet.fmca.utils.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InvoiceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RelativeLayout dataRL;
    Spinner printFormat;
    TextView dataTV;
    LinearLayout bluetoothSelectLL;
    String billNumber;
    FloatingActionButton print;
    private DBHelper db;
    private String payerName;
    double amount;
    private String total;
    private String method;
    private String discount = "0";
    private String items="";
    private String printData;
    private String date;

    BluetoothAdapter bt;
    private ListView listView;
    private String cusCopy;
    private DeviceAdapter btAdapter;
    private List<String> printFormatData;
    private CustomAdapter pTypeAdapter;
    private List<DeviceInfo> btList;
    LinearLayout alt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        init();
    }

    public void init(){
        dataRL = findViewById(R.id.toRel);
        printFormat = findViewById(R.id.format);
        dataTV = findViewById(R.id.data);
        print=findViewById(R.id.print);
        alt = findViewById(R.id.alt);
        bluetoothSelectLL = findViewById(R.id.bluetoothSelectLL);
        billNumber = getIntent().getStringExtra("billNo");

        bt = BluetoothAdapter.getDefaultAdapter();

        listView=findViewById(R.id.listView);
        listView.setVisibility(View.VISIBLE);
        btList=new ArrayList<>();
        printFormatData = new ArrayList<>();
        listView=findViewById(R.id.listView);
        btAdapter = new DeviceAdapter(getApplicationContext(), btList);
        listView.setAdapter(btAdapter);

        printFormatData.add("Customer Copy");
        printFormatData.add("Audit Copy");
        printFormatData.add("Account Dept Copy");
        printFormatData.add("Staff");
        printFormatData.add("Case File Copy");
        printFormatData.add("Teller Copy");



        pTypeAdapter = new CustomAdapter(this,  printFormatData);
        printFormat.setAdapter(pTypeAdapter);
        printFormat.setSelection(0);
        pTypeAdapter.notifyDataSetChanged();

        printFormat.setOnItemSelectedListener(this);
    }

    public void prints(View view) {
        alt.setVisibility(View.GONE);
        bluetoothSelectLL.setVisibility(View.VISIBLE);
        print.setVisibility(View.GONE);
        printFormat.setVisibility(View.GONE);

        //bluetoothHandler();
        btHandle();

        //print();
    }


    public void dataBuild(String copy){
        amount = 0.0;
        total = "0";

        db = new DBHelper(InvoiceActivity.this);
        UserModel userModel  = db.getUsers();

        List<PaymentModel> pModels = db.getPaymentByRef(billNumber);

        Toast.makeText(this, ""+pModels.size(), Toast.LENGTH_SHORT).show();

        payerName = pModels.get(0).getPayerName();
        method = pModels.get(0).getMethod();
        date = pModels.get(0).getPaymentDate() + " " + pModels.get(0).getPaymentTime();
        //centerName = userModel.getOrganization();
        //teller = userModel.getBillno();

        String p="<p text-align=\"center\">";
        p = p.concat("Federal Medical Centre Abeokuta\t" +"<br>");
        p = "<br>" + p.concat(copy +"<br>");
        p = p.concat( "++++++++++++++++++++++<br>\n\n");

        p = p.concat("OFFICIAL RECEIPT"+"</p><br>");

        p = p.concat("PAYER NAME: \r\r\r<strong>"+ payerName+"</strong><br>");
        p = p.concat("ITEM(S)<br>" /*+"<ol type=\"1\">"*/)+
                "\n[C]--------------------------------\n";

        amount = 0.0;
        items = "<font size=normal>";
        int i = 1;
        for(PaymentModel s : pModels){

            items= new StringBuilder()
                    .append(items)
                    .append(s.getRevCode())
                    .append(" ")
                    .append(s.getRevenueHead().length() < 23 ? s.getRevenueHead() : s.getRevenueHead().substring(0, 19) + "...")
                    .append("\n[L]")
                    .append(s.getQuantity())
                    .append(" X[C]")
                    .append(s.getAmount())
                    .append("[R]")
                    .append(s.getTotal())
                    .append("</font>\n")
                    .append("[C]--------------------------------\n")
                    .toString();
            p=p   + "<li>    DEPT: " + s.getDepartment()  + "<br>[" + s.getRevCode() + "] " + s.getRevenueHead() + "<br>" +s.getQuantity() + " \r\r\rX\r\u20a6 " + s.getAmount()
                    + " - \r\r\r\u20a6             "  + s.getTotal() + "</li>" ;
            discount = String.valueOf(Double.parseDouble(discount) + Double.parseDouble(s.getDiscount()));
            total = String.valueOf(Double.parseDouble(total)+Double.parseDouble(s.getTotal())-Double.parseDouble(discount));
            amount += s.getAmount();
            i++;
        }



        String td = "";

        td = td.concat("[C]<font size=big>Federal Medical Centre Abeokuta</font>\n");
        td = td.concat("[C]OFFICIAL RECEIPT"+"\n");
        td = td.concat("[C]--------------------------------\n");
        td = td.concat("[C]" + copy +"\n");
        td = td.concat("[L]PAYER NAME: [C]<b><font size=big>"+ payerName+"</font></b>\n");
        td = td.concat("[C]--------------------------------\n");
        td = td.concat("[L]ITEM(S)" +"\n");
        td = td.concat(items);
        td = td.concat("[L]TOTAL: <font size=big>\u20a6 "+ total +"</font>\n");
        td = td.concat("[L]<font size=normal>DISCOUNT " + discount + "</font>\n");
        td = td.concat("[L]<font size=big>MAIN AMT: \u20a6"+ amount+"</font>\n");
        td = td.concat("[L]<font size=normal>DATE: " + date +"</font>\n");
        td = td.concat("[L]<font size=big>BILL NO: <b>" + billNumber +"</font>\n");
        td = td.concat("[L]<font size=big>TELLER:" + userModel.getName() +"\n");
        td = td.concat("[C]********************************\n");
        td = td.concat("[C]<font size=big>Powered by Pay1One</font>\n\n");
        td = td.concat("[C]- - - - - - - - - - - - - - - -");

        printData = td;


        //textview data



        // p = p.concat(p+"\n");

        p = p.concat("<br>" +"");
        p = p.concat("---------------------------------<br>");
        p = p.concat("TOTAL: \r\u20a6 "+ (Double.parseDouble(total) - Double.parseDouble(discount)) +"<br>");
        p = p.concat("DISCOUNT: \u20a6 " + discount + "<br>");
        p = p.concat("MAIN AMT: \u20a6 "+ total+"<br>");


        p = p.concat("----------------------------------<br>");
        p = p.concat("DATE: " + date +"<br>");
        p = p.concat("BILL NO: " + billNumber+"<br>");
        p = p.concat("BILLER: " + payerName +"<br>");
        p = p.concat("********************************<br>");
        p = p.concat("Powered by Pay1One"+"<br>"+"\n"+"\n");

        dataTV.setText(Html.fromHtml(p));
    }

    public void backHandle(View view) {
        startActivity(new Intent(getApplicationContext(), Dashboard.class));
        finish();
    }

    public void printerFunction(){
        try {
            EscPosPrinter printer = new EscPosPrinter(BluetoothPrintersConnections.selectFirstPaired(), 203, 48f, 30);
            int printerDpi = printer.getPrinterCharSizeWidthPx();
            Log.d("DPI", printerDpi + "");
            printer.printFormattedText(
                    "[C]<img>"  + PrinterTextParserImg.bitmapToHexadecimalString(printer, this.getApplicationContext().getResources().getDrawableForDensity(R.drawable.logs1, DisplayMetrics.DENSITY_LOW)) +
                            "</img>\n" +
                            printData, printerDpi-5
            );
        } catch (EscPosConnectionException | EscPosBarcodeException | EscPosEncodingException | EscPosParserException e) {
            Log.d("ERROR", e.getMessage());
            e.printStackTrace();
        }
    }


    public void btHandle(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        InvoiceActivity.this,
                        new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                        1000);
            }
        }

        if(bt == null){
            return;
        }
        if(!bt.isEnabled()){
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 3000);
        }else{
            getBluetoothDevices();
        }


        //btAdapter.notifyDataSetChanged();
    }
    public void getBluetoothDevices(){
        Set<BluetoothDevice> pDevices = bt.getBondedDevices();
        //showToast("listing..."+pDevices.size());
        btList = new ArrayList<>();
        for(BluetoothDevice d : pDevices){
            btList.add(new DeviceInfo(d.getName(), d.getAddress()));
        }
        btAdapter = new DeviceAdapter(getApplicationContext(), btList);
        listView.setAdapter(btAdapter);
    }



    public boolean checkPermission(){
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(InvoiceActivity.this, new String[]{Manifest.permission.BLUETOOTH}, 1000);
            return false;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == PackageManager.PERMISSION_GRANTED){
                printerFunction();
            }else{
                Toast.makeText(getApplicationContext(), "Bluetooth permission denied", Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode == 3000){
            if(bt != null && bt.isEnabled()){
                getBluetoothDevices();
            }else{
                Toast.makeText(this, "Bluetooth request denied. Printing cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*public void printerFunction(){
        try {
            EscPosPrinter printer = new EscPosPrinter(BluetoothPrintersConnections.selectFirstPaired(), 203, 48f, 32);
            int printerDpi = printer.getPrinterCharSizeWidthPx();
            printer.printFormattedText(
                    "[C]<img>"  + PrinterTextParserImg.bitmapToHexadecimalString(printer, this.getApplicationContext().getResources().getDrawableForDensity(R.drawable.logs, DisplayMetrics.DENSITY_DEFAULT)) +
                            "</img>\n" +"\n"+
                            printData, printerDpi
            );
        } catch (EscPosConnectionException | EscPosBarcodeException | EscPosEncodingException | EscPosParserException e) {
            Log.d("ERROR", e.getMessage());
            e.printStackTrace();
        }
    }*/

    public void print(){
        if (checkPermission()) {
            printerFunction();
        }else{
            Log.d("PERMISSION ERROR", "No permission");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        dataBuild(printFormat.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class DeviceAdapter extends ArrayAdapter<DeviceInfo> {

        List<DeviceInfo> info;

        public DeviceAdapter(@NonNull Context context, List<DeviceInfo>list) {
            super(context, 0, list);
        }

        @NonNull
        @Override
        public View getView(int i, @Nullable View view, @NonNull ViewGroup parent) {
            if(view==null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.bluetooth_layout, null, false);
            }
            TextView btName = view.findViewById(R.id.btName);
            TextView btAddress = view.findViewById(R.id.btAddress);
            LinearLayout btDevice = view.findViewById(R.id.btDevice);
            DeviceInfo info = getItem(i);

            if(info != null){
                btName.setText(info.getName());
                btAddress.setText(info.getAddress());
            }
            btDevice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    findViewById(R.id.alt).setVisibility(View.VISIBLE);
                    bluetoothSelectLL.setVisibility(View.GONE);
                    findViewById(R.id.print).setVisibility(View.VISIBLE);
                    printFormat.setVisibility(View.VISIBLE);
                    //imv.setVisibility(View.VISIBLE);
                    cusCopy = printFormat.getSelectedItem().toString();
                    //pTypeAdapter.clear();

                    //showToast(cusCopy);
                    print();
                }
            });

            return view;

        }

    }
}