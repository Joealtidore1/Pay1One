package com.pay1onet.fmca.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pay1onet.fmca.R;
import com.pay1onet.fmca.database.DBHelper;
import com.pay1onet.fmca.fragments.EmrFragment;
import com.pay1onet.fmca.fragments.HomeFragment;
import com.pay1onet.fmca.fragments.ProcessInvoice;
import com.pay1onet.fmca.fragments.ReportFragment;
import com.pay1onet.fmca.fragments.WalletFragment;
import com.pay1onet.fmca.models.UserModel;

import static com.pay1onet.fmca.R.*;


public class Dashboard extends AppCompatActivity {

    DrawerLayout drawerLayout;
    BottomNavigationView bNV;
    private Fragment selectedFragment;
    private TextView title, tellerName, tellerEmail;

    int selectedFragmentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_dashboard);
        init();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void init(){
        drawerLayout = findViewById(id.drawerLayout);
        bNV = findViewById(id.bottomNavView);
        getSupportFragmentManager().beginTransaction().replace(id.mainFrameLayout, new HomeFragment()).commit();
        selectedFragmentId = bNV.getSelectedItemId();
        bNV.setOnNavigationItemSelectedListener(this.navListener);
        title = findViewById(id.title);
        tellerEmail = findViewById(id.drawerTellerMail);
        tellerName = findViewById(id.drawerTellerName);

        UserModel userModel = new DBHelper(getApplicationContext()).getUsers();
        tellerName.setText(userModel.getName());
        tellerEmail.setText(userModel.getEmail());
    }

    public BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            selectedFragment=null;
            int ids = item.getItemId();
            if(ids == selectedFragmentId){
                return true;
            }
            if (ids == id.home) {
                selectedFragment = new HomeFragment();
                title.setText("Dashboard");
            } else if (ids == id.generateInvoice) {
                selectedFragment = new ProcessInvoice();
                title.setText("Generate Invoice");
            } else if (ids == id.report) {
                selectedFragment = new ReportFragment(0);
                title.setText("Report");
            } else if (ids == id.wallet) {
                selectedFragment = new WalletFragment();
                title.setText("Report");
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(id.mainFrameLayout,
                            selectedFragment).commit();

            selectedFragmentId = ids;
            return true;
        }
    };

    public void clickMenu(View view){
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {

        drawerLayout.openDrawer((GravityCompat.START));
    }

    public  void clickLogo(View view){
        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void clickHome(View view){
        closeDrawer(drawerLayout);
        if(selectedFragmentId == id.home){
            return;
        }
        bNV.setSelectedItemId(id.home);
        title.setText("Dashboard");
        getSupportFragmentManager().beginTransaction().replace(id.mainFrameLayout, new HomeFragment()).commit();

    }

    public void clickReport(View view){
        closeDrawer(drawerLayout);
        if(selectedFragmentId == id.report){
            return;
        }
        selectedFragment = new ReportFragment(0);
        bNV.setSelectedItemId(id.report);
        title.setText("Report");
        getSupportFragmentManager().beginTransaction().replace(id.mainFrameLayout, selectedFragment).commit();

        //finish();
    }

    public void clickProcessInvoice(View view){
        closeDrawer(drawerLayout);
        if(selectedFragmentId == id.generateInvoice){
            return;
        }
        selectedFragment = new ProcessInvoice();
        bNV.setSelectedItemId(id.generateInvoice);
        title.setText("Process Invoice");
        getSupportFragmentManager().beginTransaction().replace(id.mainFrameLayout, selectedFragment).commit();

    }
    public void clickRevHeads(View view){
        closeDrawer(drawerLayout);
        startActivity(new Intent(getApplicationContext(), Departments.class));
    }
    /*public void clickSearch(View view){
        closeDrawer(drawerLayout);
        bNV.setSelectedItemId(id.report);
        title.setText("Search");
        getSupportFragmentManager().beginTransaction().replace(id.mainFrameLayout, new ReportFragment(1)).commit();
    }*/


    public void clickWallet(View view){
        closeDrawer(drawerLayout);
        if(selectedFragmentId == id.wallet){
            return;
        }
        selectedFragment = new WalletFragment();
        bNV.setSelectedItemId(id.wallet);
        title.setText("Wallet");
        getSupportFragmentManager().beginTransaction().replace(id.mainFrameLayout, selectedFragment).commit();
    }

    /*public void clickEMR(View view){
        closeDrawer(drawerLayout);
        selectedFragment = new EmrFragment();
        bNV.setSelectedItemId(id.emr);
        title.setText("Process EMR");
        getSupportFragmentManager().beginTransaction().replace(id.mainFrameLayout, new EmrFragment()).commit();

    }
    public void clickPayment(View view){
        closeDrawer(drawerLayout);
        selectedFragment = new PaymentsFragment();
        bNV.setSelectedItemId(id.payments);
        title.setText("Payment");
        getSupportFragmentManager().beginTransaction().replace(id.mainFrameLayout, new PaymentsFragment()).commit();

    }*/

}