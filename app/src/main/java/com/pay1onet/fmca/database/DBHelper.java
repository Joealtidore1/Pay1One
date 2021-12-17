package com.pay1onet.fmca.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.pay1onet.fmca.models.AppDefaultsModel;
import com.pay1onet.fmca.models.CategoriesModel;
import com.pay1onet.fmca.models.CheckModel;
import com.pay1onet.fmca.models.DepartmentsModel;
import com.pay1onet.fmca.models.PaymentModel;
import com.pay1onet.fmca.models.RevHeadsModel;
import com.pay1onet.fmca.models.UserModel;
import com.pay1onet.fmca.models.WalletModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.pay1onet.fmca.constants.DBConsts.*;

public class DBHelper extends SQLiteOpenHelper {

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use for locating paths to the the database
     */
    Context context;
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    SQLiteDatabase db;
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_WALLET_TABLE);
        db.execSQL(CREATE_REV_HEADS_TABLE);
        db.execSQL(CREATE_APP_DEF_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_PAYMENT_TABLE);
        db.execSQL(CREATE_DEPT_TABLE);
        db.execSQL(CREATE_CHEC_TABLE);

    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addUser(UserModel user){
        db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(USER_NAME, user.getName());
        cv.put(USER_PHONE_NO, user.getPhoneNumber());
        cv.put(USER_EMAIL, user.getEmail());
        cv.put(USER_ADDRESS, user.getAddress());
        cv.put(USER_ORGANIZATION, user.getOrganization());
        cv.put(USER_ID, user.getUserId());
        cv.put(USER_USERNAME, user.getUsername());
        cv.put(USER_IS_LOGGED_IN, user.isLoggedIn());
        cv.put(USER_LAST_LOGIN, user.getLastLogin());
        cv.put(USER_LOGIN_TIME, user.getLoginTime());
        cv.put(USER_MDA_ID, user.getMdaId());
        cv.put(USER_BALANCE, user.getBalance());
        cv.put(USER_DEF_PHONE, user.getDefPhone());
        cv.put(USER_DEF_EMAIL, user.getDefEmail());
        cv.put(USER_QUANTITY, user.getQuantity());
        cv.put(USER_MDA_CODE, user.getMdaCode());
        cv.put(USER_SERIAL_NO, user.getSerialNo());
        cv.put(USER_API_KEY, user.getApiKey());
        cv.put(USER_AIRPORT, user.getAirport());
        cv.put(USER_PAY_POINT, user.getPayPoint());
        cv.put(USER_PAY_POINT_ID, user.getPayPointId());
        cv.put(USER_DEVICE, user.getDevice());


        return db.insert(USER_TABLE_NAME, null, cv) != -1;
    }

    public boolean addWallet(WalletModel wallet){
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(WALLET_AGENT, wallet.getAgent());
        cv.put(WALLET_AGENT_ID, wallet.getAgentId());
        cv.put(WALLET_BALANCE, wallet.getBalance());

        return db.insert(WALLET_TABLE_NAME, null, cv) != -1;

    }

    public boolean addPayment(PaymentModel payment){
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PAYMENT_AMOUNT, payment.getAmount());
        cv.put(PAYMENT_PAYER_NAME, payment.getPayerName());
        cv.put(PAYMENT_PAYER_PHONE, payment.getPayerPhone());
        cv.put(PAYMENT_PAYER_EMAIL, payment.getPayerEmail());
        cv.put(PAYMENT_PAYMENT_DATE, payment.getPaymentDate());
        cv.put(PAYMENT_PAYMENT_TIME, payment.getPaymentTime());
        cv.put(PAYMENT_AGENT, payment.getAgent());
        cv.put(PAYMENT_REVENUE_HEAD, payment.getRevenueHead());
        cv.put(PAYMENT_SYNCED, payment.getSynced());
        cv.put(PAYMENT_REF, payment.getRef());
        cv.put(PAYMENT_PREVIOUS_BALANCE, payment.getPreviousBalance());
        cv.put(PAYMENT_CURRENT_BALANCE, payment.getCurrentBalance());
        cv.put(PAYMENT_RRR, payment.getRrr());
        cv.put(PAYMENT_LOCATION, payment.getLocation());
        cv.put(PAYMENT_MDA_ID, payment.getMdaId());
        cv.put(PAYMENT_REV_ID, payment.getRevId());
        cv.put(PAYMENT_AGENT_ID, payment.getAgentId());
        cv.put(PAYMENT_QUANTITY, payment.getQuantity());
        cv.put(PAYMENT_TRANS_FEE, payment.getTransFee());
        cv.put(PAYMENT_TOTAL, payment.getTotal());
        cv.put(PAYMENT_METHOD, payment.getMethod());
        cv.put(PAYMENT_REV_CODE, payment.getRevCode());
        cv.put(PAYMENT_LAST_SERIAL, payment.getLastSerial());
        cv.put(PAYMENT_DISCOUNT, payment.getDiscount());
        cv.put(PAYMENT_MAIN_AMT, payment.getMainAmt());
        cv.put(PAYMENT_SUBS, payment.getSubs());
        cv.put(PAYMENT_DESC, payment.getDesc());
        cv.put(DEPT, payment.getDept());
        cv.put(DEPARTMENT, payment.getDepartment());
        cv.put(CAT, payment.getCate());
        cv.put(CATEGORY, payment.getCategory());
        cv.put(EMR, payment.getEmr());
        cv.put(PRICETYPE, payment.getPriceType());
        cv.put(SHIFT, payment.getShift());
        cv.put(PAYMENT_PAY_POINT, payment.getPayPoint());
        cv.put(PAYMENT_PAY_POINT_ID, payment.getPayPointId());
        cv.put(PAYMENT_AIRPORT, payment.getAirport());


        return db.insert(PAYMENT_TABLE_NAME, null, cv) != -1;
    }

    public boolean addRevHeads(RevHeadsModel r) {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(REV_ID, r.getRevenueId());
        cv.put(REV_SERVICE_TYPE_ID, r.getServiceTypeId());
        cv.put(REV_CODE, r.getRevenueCode());
        cv.put(REV_HEAD, r.getRevenueHead());
        cv.put(REV_TARGET_VALID_FROM, r.getTargetValidFrom());
        cv.put(REV_MDA_ID, r.getMdaId());
        cv.put(REV_ACCOUNT_OWNER, r.getAccountOwner());
        cv.put(REV_ACCOUNT_NAME, r.getAccountName());
        cv.put(REV_ACCOUNT_NUMBER, r.getAccountNumber());
        cv.put(REV_BANK_NAME, r.getBankName());
        cv.put(REV_ACCOUNT_TYPE, r.getAccountType());
        cv.put(REV_BANK_CODE, r.getBankCode());
        cv.put(REV_READY, (String) r.getReady());
        cv.put(REV_AMOUNT, r.getAmount());
        cv.put(REV_TYPE, r.getType());
        cv.put(DEPT, r.getDept());
        cv.put(DEPARTMENT, r.getDepartment());
        cv.put(CAT, r.getCate());
        cv.put(CATEGORY, r.getCategory());
        cv.put(REV_SUBS, r.getSubs());
        cv.put(REV_RRN, r.getRrn());
        cv.put(REV_ACCT, r.getAcct());
        cv.put(REV_REM_SEV_TYPE, r.getRemSevType());
        cv.put(REV_REPORT_SEV_TYPE, r.getReportSevType());
        cv.put(REV_SC, r.getSc());
        cv.put(REV_MOD, r.getMod());
        cv.put(EMR, /*r.getEmr()*/"");
        cv.put(PRICETYPE, /*r.getPriceType()*/"");
        cv.put(REV_AIRPORT, r.getAirport());



        /*cv.put(REV_AMOUNT, r.getAmount());
        cv.put(REV_SUBS, r.getSubs());
        cv.put(DEPT, r.getDept());
        cv.put(DEPARTMENT, r.getDepartment());
        cv.put(CAT, r.getCate());
        cv.put(CATEGORY, r.getCategory());
        cv.put(EMR, r.getEmr());
        cv.put(PRICETYPE, r.getPriceType());*/

        return db.insert(REV_HEADS_TABLE_NAME, null, cv) != -1;
    }

    public boolean updateRevHeads(RevHeadsModel r) {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(REV_SERVICE_TYPE_ID, r.getServiceTypeId());
        cv.put(REV_CODE, r.getRevenueCode());
        cv.put(REV_HEAD, r.getRevenueHead());
        cv.put(REV_TARGET_VALID_FROM, r.getTargetValidFrom());
        cv.put(REV_MDA_ID, r.getMdaId());
        cv.put(REV_ACCOUNT_OWNER, r.getAccountOwner());
        cv.put(REV_ACCOUNT_NAME, r.getAccountName());
        cv.put(REV_ACCOUNT_NUMBER, r.getAccountNumber());
        cv.put(REV_BANK_NAME, r.getBankName());
        cv.put(REV_ACCOUNT_TYPE, r.getAccountType());
        cv.put(REV_BANK_CODE, r.getBankCode());
        cv.put(REV_READY, (String) r.getReady());
        cv.put(REV_AMOUNT, r.getAmount());
        cv.put(REV_TYPE, r.getType());
        cv.put(DEPT, r.getDept());
        cv.put(DEPARTMENT, r.getDepartment());
        cv.put(CAT, r.getCate());
        cv.put(CATEGORY, r.getCategory());
        cv.put(REV_SUBS, r.getSubs());
        cv.put(REV_RRN, r.getRrn());
        cv.put(REV_ACCT, r.getAcct());
        cv.put(REV_REM_SEV_TYPE, r.getRemSevType());
        cv.put(REV_REPORT_SEV_TYPE, r.getReportSevType());
        cv.put(REV_SC, r.getSc());
        cv.put(REV_MOD, r.getMod());
        cv.put(EMR, /*r.getEmr()*/"");
        cv.put(PRICETYPE, /*r.getPriceType()*/"");
        cv.put(REV_AIRPORT, r.getAirport());

        String where = REV_ID + " = ?";
        String[] args = {String.valueOf(r.getRevenueId())};

        return db.update(REV_HEADS_TABLE_NAME,  cv, where, args) > 0;
    }

    public boolean addAppDefaults(AppDefaultsModel defs){
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(APP_DEF_MDA_ID, defs.getMdaId());
        cv.put(APP_DEF_EMAIL, defs.getEmail());
        cv.put(APP_DEF_PHONE, defs.getPhone());
        cv.put(APP_DEF_CHARGE_TYPE, defs.getChargeType());
        cv.put(APP_DEF_CHARGE, defs.getCharge());

        return db.insert(APP_DEFAULTS_TABLE_NAME, null,cv) != -1;
    }

    public boolean addCheck(CheckModel check){
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CHEC_VALUE, check.getValue());

        return db.insert(CHEC_TABLE_NAME, null, cv) != -1;
    }

    public boolean addDepartment(DepartmentsModel d) {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();

            cv.put(DEPT_ID, d.getDeptId());
            cv.put(DEPT, d.getDeptName());
            cv.put(DEPT_ABBR, d.getDeptAbbr());

           return db.insert(DEPARTMENT_TABLE_NAME, null, cv) != -1;
    }

    public boolean updateDepartment(DepartmentsModel d) {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DEPT, d.getDeptName());
        cv.put(DEPT_ABBR, d.getDeptAbbr());

        String where = DEPT_ID + " = ?";
        String[] args = {String.valueOf(d.getDeptId())};

           return db.update(DEPARTMENT_TABLE_NAME, cv, where, args) > 1;
    }

    public boolean addCategory(CategoriesModel c) {
        db = getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(CAT_ID, c.getCategoryId());
            cv.put(CATEGORY, c.getCategory());
            cv.put(DEPT, c.getDept());
            cv.put(DEPARTMENT, c.getDepartment());

            return  db.insert(CATEGORIES_TABLE_NAME, null, cv) != -1;
    }

    public boolean updateCategory(CategoriesModel c) {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CATEGORY, c.getCategory());
        cv.put(DEPT, c.getDept());
        cv.put(DEPARTMENT, c.getDepartment());

        String where = CAT_ID + " = ?";
        String[] args = {String.valueOf(c.getCategoryId())};

        return  db.update(CATEGORIES_TABLE_NAME, cv, where, args) > 1;
    }

    //query methods

    public AppDefaultsModel getAppDefaults(String mdaId){
        String whereClause = APP_DEF_MDA_ID + " = ?";
        String[] args = {mdaId};

        db = getReadableDatabase();
        Cursor query = db.query(APP_DEFAULTS_TABLE_NAME, null, whereClause, args, null, null, null);

        AppDefaultsModel model = null;

        while(query.moveToNext()){
            model = new AppDefaultsModel(query.getInt(query.getColumnIndex(ID)),
                    query.getInt(query.getColumnIndexOrThrow(APP_DEF_MDA_ID)),
                    query.getString(query.getColumnIndexOrThrow(APP_DEF_EMAIL)),
                    query.getString(query.getColumnIndexOrThrow(APP_DEF_PHONE)),
                    query.getString(query.getColumnIndexOrThrow(APP_DEF_CHARGE_TYPE)),
                    query.getString(query.getColumnIndexOrThrow(APP_DEF_CHARGE))
                    );
        }
        return model;
    }

    public void deleteRecs(){
        db = getWritableDatabase();
        db.delete(PAYMENT_TABLE_NAME, null, null);
    }

    public AppDefaultsModel getAppDefaults(){
        db = getReadableDatabase();
        Cursor query = db.query(APP_DEFAULTS_TABLE_NAME, null, null, null, null, null, null);

        AppDefaultsModel model = null;

        while(query.moveToNext()){
            model = new AppDefaultsModel(query.getInt(query.getColumnIndex(ID)),
                    query.getInt(query.getColumnIndexOrThrow(APP_DEF_MDA_ID)),
                    query.getString(query.getColumnIndexOrThrow(APP_DEF_EMAIL)),
                    query.getString(query.getColumnIndexOrThrow(APP_DEF_PHONE)),
                    query.getString(query.getColumnIndexOrThrow(APP_DEF_CHARGE_TYPE)),
                    query.getString(query.getColumnIndexOrThrow(APP_DEF_CHARGE)));
        }
        return model;
    }

    public List<CheckModel> getCheck(){
        db = getReadableDatabase();
        Cursor query = db.query(CHEC_TABLE_NAME, null, null, null, null, null, null);
        List<CheckModel> models = new ArrayList<>();

        while(query.moveToNext()){
            models.add(new CheckModel(
                    query.getInt(query.getColumnIndexOrThrow(CHEC_VALUE)),
                    query.getString(query.getColumnIndexOrThrow(CHEC_VALUE))
            ));
        }
        return models;
    }

    public List<DepartmentsModel> getDepts(){
        db = getReadableDatabase();
        List<DepartmentsModel> models = new ArrayList<>();
        Cursor query = db.query(DEPARTMENT_TABLE_NAME, null, null, null, null, null, DEPT + " ASC");

        while(query.moveToNext()){
            models.add(new DepartmentsModel(
                    gI("id", query),
                    gI("deptID", query),
                    gS("dept", query),
                    gS("deptabbr", query))
            );
        }

        return models;
    }
    public List<DepartmentsModel> getDeptsOrder(){
        db = getReadableDatabase();
        List<DepartmentsModel> models = new ArrayList<>();
        Cursor query = db.query(DEPARTMENT_TABLE_NAME, null, null, null, null, null, DEPT_ID + " ASC");

        while(query.moveToNext()){
            models.add(new DepartmentsModel(
                    gI("id", query),
                    gI("deptID", query),
                    gS("dept", query),
                    gS("deptabbr", query))
            );
        }

        return models;
    }

    public List<RevHeadsModel> getRevHeadsByDept(String deptName, String type){
        db = getReadableDatabase();
        List<RevHeadsModel> models = new ArrayList<>();

        /*String whereClause = DEPARTMENT + " = ? AND " + PRICETYPE + " = ?";
        String select = "SELECT * FROM " + REV_HEADS_TABLE_NAME + " WHERE " + whereClause;
        String[] args = {deptName, type};*/

        Cursor query = db.query(REV_HEADS_TABLE_NAME, null, null,null, null, null, REV_HEAD + " ASC");
        while(query.moveToNext()){
            models.add(new RevHeadsModel(
                    gI(ID, query),
                    gI(REV_ID, query),
                    gS(REV_SERVICE_TYPE_ID, query),
                    gS(REV_CODE, query),
                    gS(REV_HEAD, query),
                    gS(REV_TARGET_VALID_FROM, query),
                    gI(REV_MDA_ID, query),
                    gS(REV_ACCOUNT_OWNER, query),
                    gS(REV_ACCOUNT_NAME, query),
                    gS(REV_ACCOUNT_NUMBER, query),
                    gS(REV_BANK_NAME, query),
                    gS(REV_ACCOUNT_TYPE, query),
                    gS(REV_BANK_CODE, query),
                    gS(REV_READY, query),
                    gS(REV_AMOUNT, query),
                    gS(REV_TYPE, query),
                    gS(REV_AIRPORT, query)

                    /*gI("revenueID", query),

                    gS("dept", query),
                    gS("department", query),
                    gS("cate", query),
                    gS("category", query),
                    gS("subs", query),
                    gS(EMR, query),
                    gS(PRICETYPE,query)*/
            ));
        }
        return models;
    }

    public List<String> getRevHeadByDept(String deptName, String type){
        db = getReadableDatabase();
        List<String> models = new ArrayList<>();

        String whereClause = DEPARTMENT + " = ? AND " + PRICETYPE + " = ?";
        String select = "SELECT * FROM " + REV_HEADS_TABLE_NAME + " WHERE " + whereClause;
        String[] args = {deptName, type};

        Cursor query = db.query(REV_HEADS_TABLE_NAME, new String[]{REV_HEAD}, whereClause,args, null, null, REV_HEAD + " ASC");
        while(query.moveToNext()){
            models.add(gS("revenuehead", query));
        }
        return models;
    }
    public List<String> getRevHeadByDept(String deptName){
        db = getReadableDatabase();
        List<String> models = new ArrayList<>();

        String whereClause = DEPARTMENT + " = ?";
        //String select = "SELECT * FROM " + REV_HEADS_TABLE_NAME + " WHERE " + whereClause;
        String[] args = {deptName};
        Cursor query = db.query(true, REV_HEADS_TABLE_NAME, new String[]{REV_HEAD}, whereClause, args, null, null, REV_HEAD + " ASC", null);

        while(query.moveToNext()){
            models.add(
                    gS(REV_HEAD, query));
        }
        return models;
    }

    public RevHeadsModel getRevHeadsByEmr(String type, String emr){
        db = getReadableDatabase();
        RevHeadsModel models = null;


        String whereClause = EMR + " = ? AND " + PRICETYPE + " = ?";
        String select = "SELECT * FROM " + REV_HEADS_TABLE_NAME + " WHERE " + whereClause;
        String[] args = {emr, type};

        Log.d("Query", type + "  " + emr);

        Cursor query = db.query(REV_HEADS_TABLE_NAME, null, whereClause,args, null, null, REV_HEAD + " ASC");
        if(query.moveToNext()){
            models = (new RevHeadsModel(
                    gI(ID, query),
                    gI(REV_ID, query),
                    gS(REV_SERVICE_TYPE_ID, query),
                    gS(REV_CODE, query),
                    gS(REV_HEAD, query),
                    gS(REV_TARGET_VALID_FROM, query),
                    gI(REV_MDA_ID, query),
                    gS(REV_ACCOUNT_OWNER, query),
                    gS(REV_ACCOUNT_NAME, query),
                    gS(REV_ACCOUNT_NUMBER, query),
                    gS(REV_BANK_NAME, query),
                    gS(REV_ACCOUNT_TYPE, query),
                    gS(REV_BANK_CODE, query),
                    gS(REV_READY, query),
                    gS(REV_AMOUNT, query),
                    gS(REV_TYPE, query),
                    gS(REV_AIRPORT, query)
            ));
        }
        return models;
    }

    public RevHeadsModel getRevById(int revId){
        db = getReadableDatabase();
        RevHeadsModel models = null;


        String whereClause = REV_ID + " = ?";
        String select = "SELECT * FROM " + REV_HEADS_TABLE_NAME + " WHERE " + whereClause;
        String[] args = {String.valueOf(revId)};

        Log.d("Query", revId + "  ");

        Cursor query = db.query(REV_HEADS_TABLE_NAME, null, whereClause,args, null, null, REV_HEAD + " ASC");
        if(query.moveToNext()){
            models = (new RevHeadsModel(
                    gI(ID, query),
                    gI(REV_ID, query),
                    gS(REV_SERVICE_TYPE_ID, query),
                    gS(REV_CODE, query),
                    gS(REV_HEAD, query),
                    gS(REV_TARGET_VALID_FROM, query),
                    gI(REV_MDA_ID, query),
                    gS(REV_ACCOUNT_OWNER, query),
                    gS(REV_ACCOUNT_NAME, query),
                    gS(REV_ACCOUNT_NUMBER, query),
                    gS(REV_BANK_NAME, query),
                    gS(REV_ACCOUNT_TYPE, query),
                    gS(REV_BANK_CODE, query),
                    gS(REV_READY, query),
                    gS(REV_AMOUNT, query),
                    gS(REV_TYPE, query),
                   gS(DEPT, query),
                    gS(DEPARTMENT, query),
                    gS(CAT, query),
                    gS(CATEGORY, query),
                    gS(REV_SUBS, query),
                    gS(REV_RRN, query),
                    gS(REV_ACCT, query),
                    gS(REV_REM_SEV_TYPE, query),
                    gS(REV_REPORT_SEV_TYPE, query),
                    gS(REV_SC, query),
                    gS(REV_MOD,query)

            ));
        }
        return models;
    }



    public List<CategoriesModel> getCategories(){
        db = getReadableDatabase();
        List<CategoriesModel> models = new ArrayList<>();

        Cursor query = db.query(CATEGORIES_TABLE_NAME, null, null, null, null, null, null);
        while(query.moveToNext()){
            models.add(new CategoriesModel(
                    gI(ID, query),
                    gI(CAT_ID, query),
                    gS(CATEGORY, query),
                    gI(DEPT, query),
                    gS(DEPARTMENT, query)
            ));
        }

        return models;
    }

    public List<RevHeadsModel> getHead(){
        db = getReadableDatabase();
        List<RevHeadsModel> models = new ArrayList<>();


        Cursor query = db.query(true, REV_HEADS_TABLE_NAME, new  String[]{REV_HEAD, REV_CODE}, null, null, null, null, "\""+ REV_HEAD +"\"" + " ASC", null);
        while(query.moveToNext()){
            models.add(new RevHeadsModel(
                    gS(REV_HEAD, query),
                    gS(REV_CODE, query)));

        }
        return models;

    }

    public List<RevHeadsModel> getRevHeads(){
        db = getReadableDatabase();
        List<RevHeadsModel> models = new ArrayList<>();


        Cursor query = db.query( REV_HEADS_TABLE_NAME, null, null, null, null, null, "\""+ REV_HEAD +"\"" + " ASC", null);
        while(query.moveToNext()){
            models.add(new RevHeadsModel(
                    gI(ID, query),
                    gI(REV_ID, query),
                    gS(REV_SERVICE_TYPE_ID, query),
                    gS(REV_CODE, query),
                    gS(REV_HEAD, query),
                    gS(REV_TARGET_VALID_FROM, query),
                    gI(REV_MDA_ID, query),
                    gS(REV_ACCOUNT_OWNER, query),
                    gS(REV_ACCOUNT_NAME, query),
                    gS(REV_ACCOUNT_NUMBER, query),
                    gS(REV_BANK_NAME, query),
                    gS(REV_ACCOUNT_TYPE, query),
                    gS(REV_BANK_CODE, query),
                    gS(REV_READY, query),
                    gS(REV_AMOUNT, query),
                    gS(REV_TYPE, query),
                    gS(REV_AIRPORT, query)
            ));
        }
        return models;
    }

    public WalletModel getWallet(){
        db = getReadableDatabase();
        WalletModel models = null;

        Cursor query = db.query(WALLET_TABLE_NAME, null, null, null, null, null, null);
        while(query.moveToNext()){
            models=new WalletModel(
                    gI(ID, query),
                    gS(WALLET_AGENT, query),
                    gD(WALLET_BALANCE, query),
                    gI(WALLET_AGENT_ID, query)
            );
        }

        return models;
    }

    public List<PaymentModel> getPaymentsByDateAndSearch(String search, String date){
        db = getReadableDatabase();
        String where;
        String[] args;
        if(date==null){
            where = PAYMENT_PAYER_NAME + " LIKE ? OR " +
                    REV_HEAD  + " LIKE ? ";
            args = new String[]{"%"+search+"%", "%"+search+"%"};
        } else if (search == null || search.isEmpty()) {
            where = PAYMENT_PAYMENT_DATE + " = ?";
            args = new String[]{date};
        }else{
            where = PAYMENT_PAYMENT_DATE + " = ? AND " + PAYMENT_PAYER_NAME + " LIKE ? OR " +
                    REV_HEAD  + " LIKE ? ";
            args = new String[]{date, "%"+search+"%", "%"+search+"%"};
        }
        Cursor query = db.query(PAYMENT_TABLE_NAME, null, where, args, null, null, PAYMENT_PAYMENT_DATE + " DESC, " + PAYMENT_PAYMENT_TIME + " DESC", null);
        Cursor cursor = db.query(true, PAYMENT_TABLE_NAME, new String[]{PAYMENT_RRR}, where, args, null, null, PAYMENT_PAYMENT_DATE + " DESC, "+ PAYMENT_PAYMENT_TIME + " DESC", null);
        List<PaymentModel> models = new ArrayList<>();
        while(cursor.moveToNext()){
            while(query.moveToNext()){
                if(gS(PAYMENT_RRR, cursor).equals(gS(PAYMENT_RRR, query))){
                    models.add(new PaymentModel(
                            gI(ID, query),
                            gD(PAYMENT_AMOUNT, query),
                            gS(PAYMENT_PAYER_NAME, query),
                            gS(PAYMENT_PAYER_PHONE, query),
                            gS(PAYMENT_PAYER_EMAIL, query),
                            gS(PAYMENT_PAYMENT_DATE, query),
                            gS(PAYMENT_PAYMENT_TIME, query),
                            gS(PAYMENT_AGENT, query),
                            gS(PAYMENT_REVENUE_HEAD, query),
                            gS(PAYMENT_SYNCED, query),
                            gS(PAYMENT_REF, query),
                            gS(PAYMENT_PREVIOUS_BALANCE, query),
                            gS(PAYMENT_CURRENT_BALANCE, query),
                            gS(PAYMENT_RRR, query),
                            gS(PAYMENT_LOCATION, query),
                            gS(PAYMENT_MDA_ID, query),
                            gS(PAYMENT_REV_ID, query),
                            gS(PAYMENT_AGENT_ID, query),
                            gS(PAYMENT_QUANTITY, query),
                            gS(PAYMENT_TRANS_FEE, query),
                            gS(PAYMENT_TOTAL, query),
                            gS(PAYMENT_METHOD, query),
                            gS(PAYMENT_REV_CODE, query),
                            gS(PAYMENT_LAST_SERIAL, query),
                            gS(DEPT, query),
                            gS(DEPARTMENT, query),
                            gS(CAT, query),
                            gS(CATEGORY, query),
                            gS(PAYMENT_DISCOUNT, query),
                            gS(PAYMENT_MAIN_AMT, query),
                            gS(PAYMENT_SUBS, query),
                            gS(EMR, query),
                            gS(SHIFT, query),
                            gS(PRICETYPE, query),
                            gS(PAYMENT_PAY_POINT, query),
                            gI(PAYMENT_PAY_POINT_ID, query),
                            gS(PAYMENT_AIRPORT, query)
                    ));
                    break;
                }
            }
        }
        return models;

    }

    public UserModel getUsers(){
        db = getReadableDatabase();
        UserModel models = null;

        Cursor query = db.query(USER_TABLE_NAME, null, null, null, null, null, null);
        while(query.moveToNext()){
            models = new UserModel(
                    gI(ID, query),
                    gS(USER_NAME, query),
                    gS(USER_PHONE_NO, query),
                    gS(USER_EMAIL, query),
                    gS(USER_ADDRESS, query),
                    gS(USER_ORGANIZATION, query),
                    gS(USER_ID, query),
                    gS(USER_USERNAME, query),
                    gB(USER_IS_LOGGED_IN, query),
                    gS(USER_LAST_LOGIN, query),
                    gI(USER_LOGIN_TIME, query),
                    gS(USER_MDA_ID, query),
                    (float)gD(USER_BALANCE, query),
                    gS(USER_DEF_PHONE, query),
                    gS(USER_DEF_EMAIL, query),
                    gS(USER_QUANTITY, query),
                    gS(USER_MDA_CODE, query),
                    gS(USER_SERIAL_NO, query),
                    gS(USER_API_KEY, query),
                    gS(USER_AIRPORT, query),
                    gS(USER_PAY_POINT, query),
                    gI(USER_PAY_POINT_ID, query),
                    gS(USER_DEVICE, query),
                    gS(USER_LOCATION, query)
                    /*gI(ID, query),
                    gS(USER_ID, query),
                    gS(USER_NAME, query),
                    gS(USER_EMAIL, query),
                    gS(USER_ADDRESS, query),
                    gS(USER_ORGANIZATION, query),
                    gS(USER_USERNAME, query),
                    gS(USER_MDA_CODE, query),
                    gS(USER_LAST_LOGIN, query),
                    gS(USER_LOCATION, query),
                    gS(USER_PHONE_NO, query)*/
            );
        }
        return models;
    }

    public List<String> getPaymentDates(){

        String[] columns = {PAYMENT_PAYMENT_DATE};
        List<String> dates = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor query = db.query(true, PAYMENT_TABLE_NAME, columns, null, null, null, null, "\"" + ID + "\"" + " DESC", null);
        while(query.moveToNext()){
            dates.add(gS(PAYMENT_PAYMENT_DATE, query));
        }

        return dates;
    }

    public List<PaymentModel> getPayment(){
        db = getReadableDatabase();
        List<PaymentModel> models = new ArrayList<>();

        String where = PAYMENT_SYNCED + " = ?";
        String[] args = {"0"};

        Cursor query = db.query(PAYMENT_TABLE_NAME, null, where, args, null, null, null);
        while(query.moveToNext()){
            models.add(new PaymentModel(
                    gI(ID, query),
                    gD(PAYMENT_AMOUNT, query),
                    gS(PAYMENT_PAYER_NAME, query),
                    gS(PAYMENT_PAYER_PHONE, query),
                    gS(PAYMENT_PAYER_EMAIL, query),
                    gS(PAYMENT_PAYMENT_DATE, query),
                    gS(PAYMENT_PAYMENT_TIME, query),
                    gS(PAYMENT_AGENT, query),
                    gS(PAYMENT_REVENUE_HEAD, query),
                    gS(PAYMENT_SYNCED, query),
                    gS(PAYMENT_REF, query),
                    gS(PAYMENT_PREVIOUS_BALANCE, query),
                    gS(PAYMENT_CURRENT_BALANCE, query),
                    gS(PAYMENT_RRR, query),
                    gS(PAYMENT_LOCATION, query),
                    gS(PAYMENT_MDA_ID, query),
                    gS(PAYMENT_REV_ID, query),
                    gS(PAYMENT_AGENT_ID, query),
                    gS(PAYMENT_QUANTITY, query),
                    gS(PAYMENT_TRANS_FEE, query),
                    gS(PAYMENT_TOTAL, query),
                    gS(PAYMENT_METHOD, query),
                    gS(PAYMENT_REV_CODE, query),
                    gS(PAYMENT_LAST_SERIAL, query),
                    gS(DEPT, query),
                    gS(DEPARTMENT, query),
                    gS(CAT, query),
                    gS(CATEGORY, query),
                    gS(PAYMENT_DISCOUNT, query),
                    gS(PAYMENT_MAIN_AMT, query),
                    gS(PAYMENT_SUBS, query),
                    gS(EMR, query),
                    gS(SHIFT, query),
                    gS(PRICETYPE, query),
                    gS(PAYMENT_PAY_POINT, query),
                    gI(PAYMENT_PAY_POINT_ID, query),
                    gS(PAYMENT_AIRPORT, query)
            ));
        }
        return models;
    }

    public List<PaymentModel> getPaymentByBillNo(String billNo){
        db = getReadableDatabase();
        List<PaymentModel> models = new ArrayList<>();

        String where = PAYMENT_RRR + " = ?";
        String[] args = {billNo};

        Cursor query = db.query(PAYMENT_TABLE_NAME, null, where, args, null, null, null);
        while(query.moveToNext()){
            models.add(new PaymentModel(
                    gI(ID, query),
                    gD(PAYMENT_AMOUNT, query),
                    gS(PAYMENT_PAYER_NAME, query),
                    gS(PAYMENT_PAYER_PHONE, query),
                    gS(PAYMENT_PAYER_EMAIL, query),
                    gS(PAYMENT_PAYMENT_DATE, query),
                    gS(PAYMENT_PAYMENT_TIME, query),
                    gS(PAYMENT_AGENT, query),
                    gS(PAYMENT_REVENUE_HEAD, query),
                    gS(PAYMENT_SYNCED, query),
                    gS(PAYMENT_REF, query),
                    gS(PAYMENT_PREVIOUS_BALANCE, query),
                    gS(PAYMENT_CURRENT_BALANCE, query),
                    gS(PAYMENT_RRR, query),
                    gS(PAYMENT_LOCATION, query),
                    gS(PAYMENT_MDA_ID, query),
                    gS(PAYMENT_REV_ID, query),
                    gS(PAYMENT_AGENT_ID, query),
                    gS(PAYMENT_QUANTITY, query),
                    gS(PAYMENT_TRANS_FEE, query),
                    gS(PAYMENT_TOTAL, query),
                    gS(PAYMENT_METHOD, query),
                    gS(PAYMENT_REV_CODE, query),
                    gS(PAYMENT_LAST_SERIAL, query),
                    gS(DEPT, query),
                    gS(DEPARTMENT, query),
                    gS(CAT, query),
                    gS(CATEGORY, query),
                    gS(PAYMENT_DISCOUNT, query),
                    gS(PAYMENT_MAIN_AMT, query),
                    gS(PAYMENT_SUBS, query),
                    gS(EMR, query),
                    gS(SHIFT, query),
                    gS(PRICETYPE, query),
                    gS(PAYMENT_PAY_POINT, query),
                    gI(PAYMENT_PAY_POINT_ID, query),
                    gS(PAYMENT_AIRPORT, query)
            ));
        }
        return models;
    }

    public List<PaymentModel> getAllPayments(){

        db = getReadableDatabase();
        List<PaymentModel> models = new ArrayList<>();

        Cursor query = db.query(PAYMENT_TABLE_NAME, null, null, null,null, null, PAYMENT_PAYMENT_DATE + " DESC, "+ PAYMENT_PAYMENT_TIME + " DESC");
        Cursor cursor = db.query(true, PAYMENT_TABLE_NAME, new String[]{PAYMENT_RRR}, null, null, null, null, PAYMENT_PAYMENT_DATE + " DESC, "+ PAYMENT_PAYMENT_TIME + " DESC", null);

        while(cursor.moveToNext()){
            while(query.moveToNext()){
                if(gS(PAYMENT_RRR, cursor).equals(gS(PAYMENT_RRR, query))){
                    models.add(new PaymentModel(
                            gI(ID, query),
                            gD(PAYMENT_AMOUNT, query),
                            gS(PAYMENT_PAYER_NAME, query),
                            gS(PAYMENT_PAYER_PHONE, query),
                            gS(PAYMENT_PAYER_EMAIL, query),
                            gS(PAYMENT_PAYMENT_DATE, query),
                            gS(PAYMENT_PAYMENT_TIME, query),
                            gS(PAYMENT_AGENT, query),
                            gS(PAYMENT_REVENUE_HEAD, query),
                            gS(PAYMENT_SYNCED, query),
                            gS(PAYMENT_REF, query),
                            gS(PAYMENT_PREVIOUS_BALANCE, query),
                            gS(PAYMENT_CURRENT_BALANCE, query),
                            gS(PAYMENT_RRR, query),
                            gS(PAYMENT_LOCATION, query),
                            gS(PAYMENT_MDA_ID, query),
                            gS(PAYMENT_REV_ID, query),
                            gS(PAYMENT_AGENT_ID, query),
                            gS(PAYMENT_QUANTITY, query),
                            gS(PAYMENT_TRANS_FEE, query),
                            gS(PAYMENT_TOTAL, query),
                            gS(PAYMENT_METHOD, query),
                            gS(PAYMENT_REV_CODE, query),
                            gS(PAYMENT_LAST_SERIAL, query),
                            gS(DEPT, query),
                            gS(DEPARTMENT, query),
                            gS(CAT, query),
                            gS(CATEGORY, query),
                            gS(PAYMENT_DISCOUNT, query),
                            gS(PAYMENT_MAIN_AMT, query),
                            gS(PAYMENT_SUBS, query),
                            gS(EMR, query),
                            gS(SHIFT, query),
                            gS(PRICETYPE, query),
                            gS(PAYMENT_PAY_POINT, query),
                            gI(PAYMENT_PAY_POINT_ID, query),
                            gS(PAYMENT_AIRPORT, query)
                    ));
                    break;
                }
            }
        }


        return models;
    }

    public String getTransTotal(String billNo){
        db = getReadableDatabase();
        String where = " where " + PAYMENT_RRR + " = ?";
        String[] args = {billNo};

        Cursor cursor = db.rawQuery("SELECT SUM( " + PAYMENT_TOTAL + ") FROM " + PAYMENT_TABLE_NAME + " where " + PAYMENT_RRR + " = " + billNo, null);
        if(cursor.moveToFirst()){
            return String.valueOf(cursor.getDouble(0));
        }
        return "null vl";

    }

    public String[] getNTransactions(String date){
        db = getReadableDatabase();
        String whereClause;
        String[] args = new String[1];

        if(date.equalsIgnoreCase("all")){
            whereClause = null;
            args = null;
        }else{
            whereClause = PAYMENT_PAYMENT_DATE + " = ?";
            args[0] = date;
        }

        Cursor cursor = db.query(true, PAYMENT_TABLE_NAME, new String[]{PAYMENT_RRR}, whereClause, args, null, null, null, null);
        int n =  cursor.getCount();
        if(n > 0) {
            double amount = 0.0;
            Cursor query = db.query(PAYMENT_TABLE_NAME, new String[]{PAYMENT_TOTAL}, whereClause, args, null, null, null);
            while (query.moveToNext()) {
                amount += Double.parseDouble(gS(PAYMENT_TOTAL, query));
            }
            String[] val = {String.valueOf(n), String.valueOf(amount)};
            return val;
        }

        return new String[]{"0", "0"};
    }

    public List<String> getDeptNames(){
        db = getReadableDatabase();
        List<String> depts = new ArrayList<>();
        Cursor query = db.query(true, DEPARTMENT_TABLE_NAME, new String[]{DEPT}, null, null, null, null, DEPT, null);
        while(query.moveToNext()){
            depts.add(gS(DEPT, query));
        }
        Collections.sort(depts);
        depts.add(0, "--SELECT DEPARTMENT--");

        return  depts;
    }

    public List<PaymentModel> getUnsynced(){
        db = getReadableDatabase();
        String whereClause = PAYMENT_SYNCED + " = ?";
        String[] args = {"0"};
        List<PaymentModel> models = new ArrayList<>();

        Cursor query = db.query(REV_HEADS_TABLE_NAME, null, null, null, null, null, null);
        while(query.moveToNext()){
            models.add(new PaymentModel(
                    gI(ID, query),
                    gD(PAYMENT_AMOUNT, query),
                    gS(PAYMENT_PAYER_NAME, query),
                    gS(PAYMENT_PAYER_PHONE, query),
                    gS(PAYMENT_PAYER_EMAIL, query),
                    gS(PAYMENT_PAYMENT_DATE, query),
                    gS(PAYMENT_PAYMENT_TIME, query),
                    gS(PAYMENT_AGENT, query),
                    gS(PAYMENT_REVENUE_HEAD, query),
                    gS(PAYMENT_SYNCED, query),
                    gS(PAYMENT_REF, query),
                    gS(PAYMENT_PREVIOUS_BALANCE, query),
                    gS(PAYMENT_CURRENT_BALANCE, query),
                    gS(PAYMENT_RRR, query),
                    gS(PAYMENT_LOCATION, query),
                    gS(PAYMENT_MDA_ID, query),
                    gS(PAYMENT_REV_ID, query),
                    gS(PAYMENT_AGENT_ID, query),
                    gS(PAYMENT_QUANTITY, query),
                    gS(PAYMENT_TRANS_FEE, query),
                    gS(PAYMENT_TOTAL, query),
                    gS(PAYMENT_METHOD, query),
                    gS(PAYMENT_REV_CODE, query),
                    gS(PAYMENT_LAST_SERIAL, query),
                    gS(DEPT, query),
                    gS(DEPARTMENT, query),
                    gS(CAT, query),
                    gS(CATEGORY, query),
                    gS(PAYMENT_DISCOUNT, query),
                    gS(PAYMENT_MAIN_AMT, query),
                    gS(PAYMENT_SUBS, query),
                    gS(EMR, query),
                    gS(SHIFT, query),
                    gS(PRICETYPE, query),
                    gS(PAYMENT_PAY_POINT, query),
                    gI(PAYMENT_PAY_POINT_ID, query),
                    gS(PAYMENT_AIRPORT, query)
            ));
        }
        return models;
    }

    public String sum(){
        db = getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT SUM( "+ PAYMENT_TOTAL +") FROM " + PAYMENT_TABLE_NAME, null);
        if(cur.moveToFirst())
        {
            return String.valueOf(cur.getDouble(0));
        }
        return "-1";
    }

    public String getBalance(){
         db = getReadableDatabase();
        Cursor query = db.query(WALLET_TABLE_NAME, null, null, null, null, null, null);
        if(query.moveToNext())
            return String.format("\u20a6 %.2f",query.getDouble(query.getColumnIndexOrThrow("balance")));
        else
            return "0.0";
    }

    public String getRevenueAmount(String dept, String priceType, String revHead){
        String where = DEPARTMENT + " = ? AND " + PRICETYPE + " = ? AND " + REV_HEAD + " = ?";
        String que = "select " + REV_AMOUNT + " from " + REV_HEADS_TABLE_NAME + " where " +
                DEPARTMENT + " = " + dept  + " AND " + PRICETYPE + " = "+ priceType + " AND " + REV_HEAD + " = " + revHead;
        String[] args = {dept, priceType, revHead};
        db = getReadableDatabase();

        Cursor query = db.query(REV_HEADS_TABLE_NAME, new String[]{REV_AMOUNT}, where, args, null, null, null, null);
        if(query.moveToNext()){
            return gS(REV_AMOUNT, query);
        }
        return null;
    }
    public RevHeadsModel getRevenueHead(String dept, String priceType, String revHead){
        String where = DEPARTMENT + " = ? AND " + PRICETYPE + " = ? AND " + REV_HEAD + " = ?";
        String[] args = {dept, priceType, revHead};


        //String whereClause = DEPARTMENT + " = ? AND " + PRICETYPE + " = ?";
        String select = "SELECT * FROM " + REV_HEADS_TABLE_NAME + " WHERE " + where;
        //String[] args = {deptName, type};

        Cursor query = db.query(REV_HEADS_TABLE_NAME, null, where,args, null, null, REV_HEAD + " ASC");
        db = getReadableDatabase();
        if(query.moveToNext()) {
            return new RevHeadsModel(
                    gI(ID, query),
                    gI(REV_ID, query),
                    gS(REV_SERVICE_TYPE_ID, query),
                    gS(REV_CODE, query),
                    gS(REV_HEAD, query),
                    gS(REV_TARGET_VALID_FROM, query),
                    gI(REV_MDA_ID, query),
                    gS(REV_ACCOUNT_OWNER, query),
                    gS(REV_ACCOUNT_NAME, query),
                    gS(REV_ACCOUNT_NUMBER, query),
                    gS(REV_BANK_NAME, query),
                    gS(REV_ACCOUNT_TYPE, query),
                    gS(REV_BANK_CODE, query),
                    gS(REV_READY, query),
                    gS(REV_AMOUNT, query),
                    gS(REV_TYPE, query),
                    gS(REV_AIRPORT, query)
            );
        }
        return null;
    }

    public String getAmount(String pricetype, String revhead){
        db = getReadableDatabase();

        String select = "SELECT * from " + REV_HEADS_TABLE_NAME + " WHERE " + REV_HEAD + " = ? AND " + REV_AMOUNT + " = ?";
        Cursor query = db.rawQuery(select, new String[]{revhead, pricetype});
        if(query.getCount() == 0)
            return null;
        return gS(REV_AMOUNT, query);
    }

    public boolean updateWallet(double amount){
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(WALLET_BALANCE, amount);
        String whereClause = ID + "=?";
        String[] args = {"1"};

        return db.update(WALLET_TABLE_NAME, cv, whereClause, args) != 0;
    }

    public boolean updatePayment(String ref){
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PAYMENT_SYNCED, "1");

        String whereClause = PAYMENT_REF + "=?";
        String[] args = {ref};

        return db.update(PAYMENT_TABLE_NAME, cv, whereClause, args) != 0;
    }

    public List<PaymentModel> getPaymentByRef(String ref){
        db = getReadableDatabase();
        List<PaymentModel> models= new ArrayList<>();

        String where = PAYMENT_RRR + "=?";
        String[] args = {ref};

        Cursor query = db.query(PAYMENT_TABLE_NAME, null, where, args, null, null, null);
        while(query.moveToNext()){
            models.add(new PaymentModel(
                    gI(ID, query),
                    gD(PAYMENT_AMOUNT, query),
                    gS(PAYMENT_PAYER_NAME, query),
                    gS(PAYMENT_PAYER_PHONE, query),
                    gS(PAYMENT_PAYER_EMAIL, query),
                    gS(PAYMENT_PAYMENT_DATE, query),
                    gS(PAYMENT_PAYMENT_TIME, query),
                    gS(PAYMENT_AGENT, query),
                    gS(PAYMENT_REVENUE_HEAD, query),
                    gS(PAYMENT_SYNCED, query),
                    gS(PAYMENT_REF, query),
                    gS(PAYMENT_PREVIOUS_BALANCE, query),
                    gS(PAYMENT_CURRENT_BALANCE, query),
                    gS(PAYMENT_RRR, query),
                    gS(PAYMENT_LOCATION, query),
                    gS(PAYMENT_MDA_ID, query),
                    gS(PAYMENT_REV_ID, query),
                    gS(PAYMENT_AGENT_ID, query),
                    gS(PAYMENT_QUANTITY, query),
                    gS(PAYMENT_TRANS_FEE, query),
                    gS(PAYMENT_TOTAL, query),
                    gS(PAYMENT_METHOD, query),
                    gS(PAYMENT_REV_CODE, query),
                    gS(PAYMENT_LAST_SERIAL, query),
                    gS(DEPT, query),
                    gS(DEPARTMENT, query),
                    gS(CAT, query),
                    gS(CATEGORY, query),
                    gS(PAYMENT_DISCOUNT, query),
                    gS(PAYMENT_MAIN_AMT, query),
                    gS(PAYMENT_SUBS, query),
                    gS(EMR, query),
                    gS(SHIFT, query),
                    gS(PRICETYPE, query),
                    gS(PAYMENT_PAY_POINT, query),
                    gI(PAYMENT_PAY_POINT_ID, query),
                    gS(PAYMENT_AIRPORT, query)
            ));
        }
        return models;

    }

    public List<String> getPriceType(){
        db = getReadableDatabase();
        List<String> list = new ArrayList<>();

        Cursor query = db.query(true, REV_HEADS_TABLE_NAME, new String[]{PRICETYPE}, null, null, null, null, null, null);
        while(query.moveToNext()){
            list.add(gS(PRICETYPE, query));
        }

        return list;
    }

    public List<PaymentModel> getPaymentByDate(String date){
        db = getReadableDatabase();
        List<PaymentModel> models= new ArrayList<>();

        String where = PAYMENT_PAYMENT_DATE + "=?";
        String[] args = {date};

        Cursor query = db.query(PAYMENT_TABLE_NAME, null, where, args,null, null, PAYMENT_PAYMENT_DATE + " DESC, "+ PAYMENT_PAYMENT_TIME + " DESC");
        Cursor cursor = db.query(true, PAYMENT_TABLE_NAME, new String[]{PAYMENT_RRR}, where, args, null, null, PAYMENT_PAYMENT_DATE + " DESC, "+ PAYMENT_PAYMENT_TIME + " DESC", null);

        while(cursor.moveToNext()){
            while(query.moveToNext()){
                if(gS(PAYMENT_RRR, cursor).equals(gS(PAYMENT_RRR, query))){
                    models.add(new PaymentModel(
                            gI(ID, query),
                            gD(PAYMENT_AMOUNT, query),
                            gS(PAYMENT_PAYER_NAME, query),
                            gS(PAYMENT_PAYER_PHONE, query),
                            gS(PAYMENT_PAYER_EMAIL, query),
                            gS(PAYMENT_PAYMENT_DATE, query),
                            gS(PAYMENT_PAYMENT_TIME, query),
                            gS(PAYMENT_AGENT, query),
                            gS(PAYMENT_REVENUE_HEAD, query),
                            gS(PAYMENT_SYNCED, query),
                            gS(PAYMENT_REF, query),
                            gS(PAYMENT_PREVIOUS_BALANCE, query),
                            gS(PAYMENT_CURRENT_BALANCE, query),
                            gS(PAYMENT_RRR, query),
                            gS(PAYMENT_LOCATION, query),
                            gS(PAYMENT_MDA_ID, query),
                            gS(PAYMENT_REV_ID, query),
                            gS(PAYMENT_AGENT_ID, query),
                            gS(PAYMENT_QUANTITY, query),
                            gS(PAYMENT_TRANS_FEE, query),
                            gS(PAYMENT_TOTAL, query),
                            gS(PAYMENT_METHOD, query),
                            gS(PAYMENT_REV_CODE, query),
                            gS(PAYMENT_LAST_SERIAL, query),
                            gS(DEPT, query),
                            gS(DEPARTMENT, query),
                            gS(CAT, query),
                            gS(CATEGORY, query),
                            gS(PAYMENT_DISCOUNT, query),
                            gS(PAYMENT_MAIN_AMT, query),
                            gS(PAYMENT_SUBS, query),
                            gS(EMR, query),
                            gS(SHIFT, query),
                            gS(PRICETYPE, query),
                            gS(PAYMENT_PAY_POINT, query),
                            gI(PAYMENT_PAY_POINT_ID, query),
                            gS(PAYMENT_AIRPORT, query)
                    ));
                    break;
                }
            }
        }
        return models;

    }

    public List<PaymentModel> getAllPaymentModels(){
        db = getReadableDatabase();
        List<PaymentModel> models= new ArrayList<>();


        Cursor query = db.query(PAYMENT_TABLE_NAME, null, null, null,null, null, PAYMENT_PAYMENT_DATE + " DESC, "+ PAYMENT_PAYMENT_TIME + " DESC");
        Cursor cursor = db.query(true, PAYMENT_TABLE_NAME, new String[]{PAYMENT_RRR}, null, null, null, null, PAYMENT_PAYMENT_DATE + " DESC, "+ PAYMENT_PAYMENT_TIME + " DESC", null);

        while(cursor.moveToNext()){
            while(query.moveToNext()){
                if(gS(PAYMENT_RRR, cursor).equals(gS(PAYMENT_RRR, query))){
                    models.add(new PaymentModel(
                            gI(ID, query),
                            gD(PAYMENT_AMOUNT, query),
                            gS(PAYMENT_PAYER_NAME, query),
                            gS(PAYMENT_PAYER_PHONE, query),
                            gS(PAYMENT_PAYER_EMAIL, query),
                            gS(PAYMENT_PAYMENT_DATE, query),
                            gS(PAYMENT_PAYMENT_TIME, query),
                            gS(PAYMENT_AGENT, query),
                            gS(PAYMENT_REVENUE_HEAD, query),
                            gS(PAYMENT_SYNCED, query),
                            gS(PAYMENT_REF, query),
                            gS(PAYMENT_PREVIOUS_BALANCE, query),
                            gS(PAYMENT_CURRENT_BALANCE, query),
                            gS(PAYMENT_RRR, query),
                            gS(PAYMENT_LOCATION, query),
                            gS(PAYMENT_MDA_ID, query),
                            gS(PAYMENT_REV_ID, query),
                            gS(PAYMENT_AGENT_ID, query),
                            gS(PAYMENT_QUANTITY, query),
                            gS(PAYMENT_TRANS_FEE, query),
                            gS(PAYMENT_TOTAL, query),
                            gS(PAYMENT_METHOD, query),
                            gS(PAYMENT_REV_CODE, query),
                            gS(PAYMENT_LAST_SERIAL, query),
                            gS(DEPT, query),
                            gS(DEPARTMENT, query),
                            gS(CAT, query),
                            gS(CATEGORY, query),
                            gS(PAYMENT_DISCOUNT, query),
                            gS(PAYMENT_MAIN_AMT, query),
                            gS(PAYMENT_SUBS, query),
                            gS(EMR, query),
                            gS(SHIFT, query),
                            gS(PRICETYPE, query),
                            gS(PAYMENT_PAY_POINT, query),
                            gI(PAYMENT_PAY_POINT_ID, query),
                            gS(PAYMENT_AIRPORT, query)
                    ));
                    break;
                }
            }
        }
        return models;

    }

    public String countSynced(String date){
        db = getReadableDatabase();
        String where;
        String[] args = new String[1];
        if(date.equalsIgnoreCase("all")){
            where = null;
            args = null;
        }else{
            where = PAYMENT_PAYMENT_DATE + " = ?";
            args[0] = date;
        }
        Cursor query = db.query(true, PAYMENT_TABLE_NAME, new String[]{PAYMENT_RRR}, where, args, null, null, null, null);
        int tot = query.getCount();
        if(tot > 0){
            String[] arg;
            String wh;
            if(date.equalsIgnoreCase("all")){
                arg = new String[]{"0"};
                wh = PAYMENT_SYNCED + " = ?";
            }else {
                arg = new String[]{"0", date};
                wh = PAYMENT_SYNCED + " = ? AND " +
                        PAYMENT_PAYMENT_DATE + " = ?";
            }

            Cursor cursor = db.query(true, PAYMENT_TABLE_NAME, new String[]{PAYMENT_RRR}, wh, arg, null, null, null, null);
            return cursor.getCount() +"/"+tot;

        }
        return "0/0";

    }




    public int gI(String columnName, Cursor cursor){
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName));
    }

    public String gS(String columnName, Cursor cursor){
        return cursor.getString(cursor.getColumnIndexOrThrow(columnName));
    }
    public double gD(String columnName, Cursor cursor){
        return cursor.getDouble(cursor.getColumnIndexOrThrow(columnName));
    }

    public boolean gB(String columnName, Cursor cursor){
        return Boolean.parseBoolean(cursor.getString(cursor.getColumnIndexOrThrow(columnName)));
    }








}
