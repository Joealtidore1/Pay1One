package com.pay1onet.fmca.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pay1onet.fmca.R;
import com.pay1onet.fmca.database.DBHelper;
import com.pay1onet.fmca.models.AppDefaultsModel;
import com.pay1onet.fmca.models.CategoriesModel;
import com.pay1onet.fmca.models.DepartmentsModel;
import com.pay1onet.fmca.models.RevHeadsModel;
import com.pay1onet.fmca.models.UserModel;
import com.pay1onet.fmca.models.WalletModel;
import com.pay1onet.fmca.network.ApiCall;

import java.util.List;

public class SignInActivity extends AppCompatActivity {

    EditText uname, pword;
    Button button;
    ProgressBar progressBar;
    DBHelper db;

    List<CategoriesModel> categories;
    List<DepartmentsModel> departments;
    List<RevHeadsModel> revHeads;
    UserModel userModel;
    WalletModel walletModel;
    AppDefaultsModel appDefaultsModel;
    ProgressDialog dialog;

    @Override
    protected void onPause() {
        super.onPause();
        if(dialog != null){
            dialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("shared_preference", 0);
        if (preferences.getString("loggedIn", "").equalsIgnoreCase("true")){
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
            finish();
            return;
        }


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dialog = new ProgressDialog(SignInActivity.this);

        uname = findViewById(R.id.username);
        pword = findViewById(R.id.password);
        button = findViewById(R.id.submit);
        progressBar = findViewById(R.id.progressBar);
        db = new DBHelper(getApplicationContext());

        button.setOnClickListener(view -> {

            if (uname.length() != 0 && pword.length() != 0) {
                progressBar.setVisibility(View.VISIBLE);
                showToast("logging in");

                new ApiCall().getLoginResponse(SignInActivity.this, uname.getText().toString(), pword.getText().toString(), new ApiCall.NetworkCallBack() {
                    @Override
                    public void onSuccess(AppDefaultsModel appDefaults, List<CategoriesModel> categories, List<DepartmentsModel> departments, UserModel userDetails, List<RevHeadsModel> revHeads,  WalletModel wallet, boolean status) {
                        showToast("Successful Login");

                        SignInActivity.this.categories = categories;
                        SignInActivity.this.departments = departments;
                        SignInActivity.this.revHeads = revHeads;

                        SignInActivity.this.walletModel = wallet;
                        SignInActivity.this.userModel = userDetails;

                        SignInActivity.this.appDefaultsModel = appDefaults;

                        dialog.setMessage("Please wait while data is downloaded");
                        dialog.setCancelable(false);
                        dialog.show();
                        new loadData().execute();
                    }

                    @Override
                    public void onFailure(String message) {
                        showToast(message);
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }


        });
    }

    public void showToast(String message){
        Toast.makeText(SignInActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public boolean category(){
        if(categories != null&&categories.size() != 0){
            showToast("Downloading Category");
            boolean a = db.addCategory(categories.get(0));
            if(a){
                categories.remove(0);
                if(categories.size() > 0)
                    return true && category();
            }else{
                return false;
            }
        }

        return true;
    }

    public boolean depts(){
        showToast("Downloading department");
        boolean a = db.addDepartment(departments.get(0));
        if(a){
            departments.remove(0);
            if(departments.size() > 0)
                return true && depts();
            else{
                progressBar.setVisibility(View.GONE);
            }
        }
        return false;
    }

    public boolean revHead(){
        showToast("Downloading Revenue Head");
        boolean a = db.addRevHeads(revHeads.get(0));
        if(a){
            revHeads.remove(0);
            if(revHeads.size() > 0)
                return revHead();
            else{
                progressBar.setVisibility(View.GONE);
            }
        }
        return false;
    }

    public class loadData extends AsyncTask<Void, Integer, Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            int n = 1 + departments.size() + revHeads.size();

            boolean a = db.addUser(userModel);
            if(a){
                publishProgress(1, n);
            }else{
                return false;
            }
            a = db.addWallet(walletModel);
            if(a){
                publishProgress(2, n);
            }else{
                return false;
            }

            a = db.addAppDefaults(appDefaultsModel);
            if(a){
                publishProgress(3, n);
            }else{
                return false;
            }

            for(int i = 0; i < departments.size(); i++){
                a = db.addDepartment(departments.get(i));
                if(a){
                    publishProgress(4+i, n);
                }else{
                    return false;
                }
            }
            int s = 3 + departments.size();

            for(int i = 0; i < revHeads.size(); i++){
                a = db.addRevHeads(revHeads.get(i));
                if(a){
                    publishProgress(s+i+1, n);
                }else{
                    return false;
                }
            }

            s += revHeads.size();

            if(categories != null) {
                n += categories.size();
                for (int i = 0; i < categories.size(); i++) {
                    a = db.addCategory(categories.get(i));
                    if (a) {
                        publishProgress(s+i+1, n);
                    } else {
                        return false;
                    }
                }
            }

            return true;
        }

        @SuppressLint("DefaultLocale")
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            dialog.setMessage(String.format("Downloading items %d of %d ", values[0], values[1]));
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                SignInActivity.this.showToast("Download Completed");
                dialog.setMessage("Download Successful");
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("shared_preference", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("loggedIn", "true");
                editor.apply();
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
                finish();
            }else{
                SignInActivity.this.showToast("Download Failed");
                progressBar.setVisibility(View.GONE);
                dialog.dismiss();
            }
        }
    }

}