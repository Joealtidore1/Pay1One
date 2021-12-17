package com.pay1onet.fmca.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pay1onet.fmca.JSONSchema.CategorySchema;
import com.pay1onet.fmca.JSONSchema.DepartmentSchema;
import com.pay1onet.fmca.JSONSchema.RevenueHeads;
import com.pay1onet.fmca.R;
import com.pay1onet.fmca.database.DBHelper;
import com.pay1onet.fmca.models.CategoriesModel;
import com.pay1onet.fmca.models.DepartmentsModel;
import com.pay1onet.fmca.models.RevHeadsModel;
import com.pay1onet.fmca.network.ApiCall;

import java.util.ArrayList;
import java.util.List;

public class Departments extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView departments;
    LinearLayout empty;
    DBHelper db;
    static ProgressBar progressBar;

    List<DepartmentsModel> departmentsModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);
        init();
    }

    public void init(){
        db = new DBHelper(getApplicationContext());
        departments = findViewById(R.id.departmentList);
        empty = findViewById(R.id.empty);
        progressBar = findViewById(R.id.progressBar);

        departments.setOnItemClickListener(this);

        new GetDepartments().start();
    }

    public void pressBack(View view) {
        super.onBackPressed();
    }

    public void downloadDepartments(View view) {
        progressBar.setVisibility(View.VISIBLE);
        new ApiCall().syncDepartments(getApplicationContext(), db.getUsers().getMdaId(), new ApiCall.syncDepartmentCallBack() {
            @Override
            public void onSuccess(List<DepartmentSchema> departments, List<RevenueHeads> revenueHeads, List<CategorySchema> categories) {

                new SyncRevHeads(departments, categories, revenueHeads).start();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(Departments.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<DepartmentsModel> schemaToModel(List<DepartmentSchema> deptSchema){

        List<DepartmentsModel> models = new ArrayList<>();

        for(DepartmentSchema ds : deptSchema){
            models.add(
                    new DepartmentsModel(0, ds.getDeptID(), ds.getDeptName(), ds.getDeptAbbr())
            );
        }

        return models;
    }

    public List<RevHeadsModel> revHeadSchemaToModel(List<RevenueHeads> schema){
        List<RevHeadsModel> models = new ArrayList<>();
        for(RevenueHeads rs : schema){
            models.add(new RevHeadsModel(
                    0,
                    rs.getId(),
                    rs.getServiceTypeId(),
                    rs.getCode(),
                    rs.getRevenueHead(),
                    rs.getTargetValidFrom(),
                    rs.getMdaId(),
                    rs.getAccountOwner(),
                    rs.getAccountName(),
                    rs.getAccountNumber(),
                    rs.getBankName(),
                    rs.getAccountType(),
                    rs.getBankCode(),
                    rs.getReady(),
                    rs.getAmount(),
                    rs.getType(),
                    rs.getDept(),
                    rs.getDepartment(),
                    rs.getCate(),
                    rs.getCategory(),
                    rs.getSubs(),
                    rs.getRrn(),
                    rs.getAcct(),
                    rs.getRemSevType(),
                    rs.getReportSevType(),
                    rs.getSc(),
                    rs.getMod()
            ));
        }
        return models;
    }

    public List<CategoriesModel> catSchemaToModel(List<CategorySchema> schema){
        List<CategoriesModel> models = new ArrayList<>();
        for(CategorySchema cs : schema){
            models.add(new CategoriesModel(
                    0,
                    cs.getCategoryId(),
                    cs.getCategory(),
                    cs.getDept(),
                    cs.getDepartment()
            ));
        }
        return models;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getApplicationContext(), RevenueHeadsActivity.class);
        intent.putExtra("dept", departmentsModels.get(i).getDeptName());
        startActivity(intent);
    }

    public class SyncRevHeads extends Thread{
        List<DepartmentsModel> deptModel, deptModelFromDb;
        List<CategoriesModel> catModel, catModelFromDb;
        List<RevHeadsModel> revModel, revModelFromDb;
        List<RevenueHeads> revSchema;
        List<CategorySchema> catSchema;
        List<DepartmentSchema> deptSchema;
        public SyncRevHeads(List<DepartmentSchema> deptSchema, List<CategorySchema> catSchema, List<RevenueHeads> revSchema){
            this.catSchema = catSchema;
            this.deptSchema = deptSchema;
            this.revSchema = revSchema;
        }

        public void syncDepartments(DepartmentsModel deptModel){
            boolean found = false;
            int i = 0;
            if(deptModelFromDb.size() == 0 && this.deptModel.size() > 0){
                db.addDepartment(deptModel);
                this.deptModel.remove(0);
                if(this.deptModel.size() > 0){
                    syncDepartments(this.deptModel.get(0));
                }else{
                    return;
                }
            }
            for(DepartmentsModel dm : deptModelFromDb){
                if(dm.getDeptId() == deptModel.getDeptId()){
                    db.updateDepartment(deptModel);
                    deptModelFromDb.remove(i);
                    found = true;
                    break;
                }
                ++i;
            }
            if(!found){
                db.addDepartment(deptModel);
            }
            this.deptModel.remove(0);
            if(this.deptModel.size() > 0){
                syncDepartments(this.deptModel.get(0));
            }
        }

        public void syncCategories(CategoriesModel catModel){
            boolean found = false;
            int i = 0;
            if(catModelFromDb.size() == 0 && this.catModel.size() > 0){
                db.addCategory(catModel);
                this.catModel.remove(0);
                if(this.catModel.size() > 0){
                    syncCategories(this.catModel.get(0));
                }else{
                    return;
                }
            }
            for(CategoriesModel cm : catModelFromDb){
                if(cm.getCategoryId() == catModel.getCategoryId()){
                    db.updateCategory(catModel);
                    catModelFromDb.remove(i);
                    found = true;
                    break;
                }
                ++i;
            }
            if(!found){
                db.addCategory(catModel);
            }
            this.catModel.remove(0);
            if(this.catModel.size() > 0){
                syncCategories(this.catModel.get(0));
            }
        }

        public void syncRevenueHeads(RevHeadsModel revModel){
            boolean found = false;
            int i = 0;
            if(revModelFromDb.size() == 0 && this.revModel.size() > 0){
                db.addRevHeads(revModel);
                this.revModel.remove(0);
                if(this.revModel.size() > 0){
                    syncRevenueHeads(this.revModel.get(0));
                }else{
                    return;
                }

            }
            for(RevHeadsModel rm : revModelFromDb){
                if(rm.getRevenueId() == revModel.getRevenueId()){
                    db.updateRevHeads(revModel);
                    revModelFromDb.remove(i);
                    found = true;
                    break;
                }
                ++i;
            }
            if(!found){
                db.addRevHeads(revModel);
            }
            this.revModel.remove(0);
            if(this.revModel.size() > 0){
                syncRevenueHeads(this.revModel.get(0));
            }
        }



        @Override
        public void run() {
            super.run();
            this.catModel = catSchemaToModel(catSchema);
            this.deptModel = schemaToModel(deptSchema);
            this.revModel = revHeadSchemaToModel(revSchema);
            this.deptModelFromDb = db.getDeptsOrder();
            this.catModelFromDb = db.getCategories();
            this.revModelFromDb = db.getRevHeads();

            if(catModel.size()>0)
                syncCategories(catModel.get(0));
            if(deptModel.size() > 0)
                syncDepartments(deptModel.get(0));
            if(revModel.size() > 0)
                syncRevenueHeads(revModel.get(0));

            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Departments.this, "Sync finished", Toast.LENGTH_SHORT).show();
            });
        }
    }

    public class GetDepartments extends Thread{
        @Override
        public void run() {
            super.run();
            departmentsModels = db.getDepts();

            if(departmentsModels.size() > 0) {
                empty.setVisibility(View.GONE);
                DepartmentsAdapter adapter = new DepartmentsAdapter(getApplicationContext(), departmentsModels);
                departments.setAdapter(adapter);
            }else{
                empty.setVisibility(View.VISIBLE);
            }
        }
    }

    private class DepartmentsAdapter extends ArrayAdapter<DepartmentsModel> {
        public DepartmentsAdapter(Context context, List<DepartmentsModel> departmentsModels) {
            super(context, 0, departmentsModels);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

            if(view == null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.custom_departments, parent, false);
            }

            TextView deptName = view.findViewById(R.id.deptName);

            DepartmentsModel model = departmentsModels.get(position);
            if(model != null){
                deptName.setText(model.getDeptName());
            }

            return view;
        }

    }
}