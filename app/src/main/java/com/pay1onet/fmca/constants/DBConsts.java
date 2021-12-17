package com.pay1onet.fmca.constants;

public class DBConsts {

    public static final String DB_NAME = "payone.db";

    private static final String create =  "CREATE TABLE IF NOT EXISTS  " ;
    public static final String DEPT = "dept";
    public static  final String DEPARTMENT = "department";
    public static final String CAT = "cate";
    public static final String CATEGORY = "category";


    //ID COLUMN
    public static final String ID =  "id";
    public static final String EMR = "emr";
    public static final String SHIFT = "shift";
    public static final String PRICETYPE = "pricetype";
    private static final String createId =  "(  "  + ID +  "  INTEGER PRIMARY KEY AUTOINCREMENT,  " ;
    private static final String text =  "  TEXT,  ";


    public static final String PAYMENT_TABLE_NAME =  "payments";
    public static final String REV_HEADS_TABLE_NAME =  "revheads";
    public static final String WALLET_TABLE_NAME =  "wallet";
    public static final String USER_TABLE_NAME =  "userdata";
    public static final String CHEC_TABLE_NAME =  "checktable";
    public static final String APP_DEFAULTS_TABLE_NAME =  "appdefaults";
    public static final String DEPARTMENT_TABLE_NAME =  "departments";
    public static final String CATEGORIES_TABLE_NAME =  "categories";




    //user table columns
    public static final String USER_NAME =  "name";
    public static final String USER_PHONE_NO =  "phone";
    public static final String USER_EMAIL =  "email";
    public static final String USER_ADDRESS =  "address";
    public static final String USER_ORGANIZATION =  "organization";
    public static final String USER_ID =  "userId";
    public static final String USER_USERNAME =  "username";
    public static final String USER_IS_LOGGED_IN =  "isloggedin";
    public static final String USER_LAST_LOGIN =  "lastLogin";
    public static final String USER_LOGIN_TIME =  "logintime";
    public static final String USER_MDA_ID =  "mdaid";
    public static final String USER_BALANCE =  "balance";
    public static final String USER_DEF_PHONE =  "defphone";
    public static final String USER_DEF_EMAIL =  "defemail";
    public static final String USER_QUANTITY =  "quantity";
    public static final String USER_MDA_CODE =  "mdacode";
    public static final String USER_SERIAL_NO =  "serialno";
    public static final String USER_API_KEY =  "apikey";
    public static final String USER_AIRPORT =  "airport";
    public static final String USER_PAY_POINT =  "paypoint";
    public static final String USER_PAY_POINT_ID =  "paypointID";
    public static final String USER_DEVICE =  "device";
    public static final String USER_LOCATION =  "location";





   // public static final String USER_LOCATION =  "location";

    //wallet table columns
    public static final String WALLET_AGENT =  "agent";
    public static final String WALLET_BALANCE =  "balance";
    public static  final String WALLET_AGENT_ID =  "agentId";

    //payment table columns
    public static final String PAYMENT_AMOUNT =  "amount";
    public static final String PAYMENT_PAYER_NAME =  "payername";
    public static final String PAYMENT_PAYER_PHONE =  "payerphone";
    public static final String PAYMENT_PAYER_EMAIL =  "payeremail";
    public static final String PAYMENT_PAYMENT_DATE =  "paymentdate";
    public static final String PAYMENT_PAYMENT_TIME =  "paymenttime";
    public static final String PAYMENT_AGENT =  "agent";
    public static final String PAYMENT_REVENUE_HEAD =  "revenuehead";
    public static final String PAYMENT_SYNCED =  "synced";
    public static final String PAYMENT_REF =  "ref";
    public static final String PAYMENT_PREVIOUS_BALANCE =  "previousbalance";
    public static final String PAYMENT_CURRENT_BALANCE =  "currentbalance";
    public static final String PAYMENT_RRR =  "rrr";
    public static final String PAYMENT_LOCATION =  "location";
    public static final String PAYMENT_MDA_ID =  "mdaID";
    public static final String PAYMENT_REV_ID =  "revID";
    public static final String PAYMENT_AGENT_ID =  "agentID";
    public static final String PAYMENT_QUANTITY =  "quantity";
    public static final String PAYMENT_TRANS_FEE =  "transfee";
    public static final String PAYMENT_TOTAL =  "total";
    public static final String PAYMENT_METHOD =  "method";
    public static final String PAYMENT_REV_CODE =  "revcode";
    public static final String PAYMENT_LAST_SERIAL =  "lastserial";
    public static final String PAYMENT_DISCOUNT =  "discount";
    public static final String PAYMENT_MAIN_AMT =  "mainamt";
    public static final String PAYMENT_SUBS =  "subs";
    public static final String PAYMENT_DESC =  "desc";
    public static final String PAYMENT_PAY_POINT =  "paypoint";
    public static final String PAYMENT_PAY_POINT_ID =  "paypointid";
    public static final String PAYMENT_AIRPORT =  "airport";


    //revhead table columns
    public static final String REV_ID =  "revenueID";
    public static final String REV_SERVICE_TYPE_ID =  "servicetypeid";
    public static final String REV_CODE =  "revenuecode";
    public static final String REV_HEAD =  "revenuehead";
    public static final String REV_TARGET_VALID_FROM =  "targetvalidfrom";
    public static final String REV_MDA_ID =  "mdaid";
    public static final String REV_ACCOUNT_OWNER =  "accountowner";
    public static final String REV_ACCOUNT_NAME =  "accountname";
    public static final String REV_ACCOUNT_NUMBER =  "accountnumber";
    public static final String REV_BANK_NAME =  "bankname";
    public static final String REV_ACCOUNT_TYPE =  "accounttype";
    public static final String REV_BANK_CODE =  "bankcode";
    public static final String REV_READY =  "ready";
    public static final String REV_AMOUNT =  "amount";
    public static final String REV_TYPE =  "type";
    public static final String REV_AIRPORT =  "airport";
    public static final String REV_SUBS = "subs";
    public static final String REV_RRN = "rrn";
    public static final String REV_ACCT = "acct";
    public static final String REV_REM_SEV_TYPE = "remsevtype";
    public static final String REV_REPORT_SEV_TYPE = "reportsevtype";
    public static final String REV_SC = "sc";
    public static final String REV_MOD = "mod";

    //chec table columns
    public static final String CHEC_VALUE =  "value";

    //appdefaults table column
    public static final String APP_DEF_MDA_ID =  "mdaID";
    public static final String APP_DEF_EMAIL =  "email";
    public static final String APP_DEF_PHONE =  "phone";
    public static final String APP_DEF_CHARGE_TYPE =  "chargetype";
    public static final String APP_DEF_CHARGE =  "charge";
    
    //department table columns
    public static final String DEPT_ID =  "deptID ";
    public static final String DEPT_ABBR =  "deptabbr";
    
    //categories table columns
    public static final String CAT_ID =  "categoryId";

    
    //create categories table
    public static  final String CREATE_CATEGORY_TABLE = create + CATEGORIES_TABLE_NAME + createId +
            CAT_ID + text +
            CATEGORY+ text +
            DEPT + text +
            DEPARTMENT +  "  TEXT ); " ;
    
    
    //create department table
    public static final String CREATE_DEPT_TABLE = create + DEPARTMENT_TABLE_NAME + createId + 
            DEPT_ID + text +
            DEPT + text +
            DEPT_ABBR +  "  TEXT ); " ; 

    //create check table
    public static final String CREATE_CHEC_TABLE = create + CHEC_TABLE_NAME + createId +
            CHEC_VALUE +  " TEXT ); " ;


    //create app defaults table
    public static final String CREATE_APP_DEF_TABLE = create + APP_DEFAULTS_TABLE_NAME + createId +
            APP_DEF_MDA_ID + text +
            APP_DEF_EMAIL + text +
            APP_DEF_PHONE + text +
            APP_DEF_CHARGE_TYPE + text +
            APP_DEF_CHARGE +  "  TEXT ); " ;


    //create revheads table
    public static final String CREATE_REV_HEADS_TABLE = create + REV_HEADS_TABLE_NAME + createId +
            REV_ID + text +
            REV_SERVICE_TYPE_ID + text +
            REV_CODE + text +
            REV_HEAD + text +
            REV_TARGET_VALID_FROM + text +
            REV_MDA_ID + text +
            REV_ACCOUNT_OWNER + text +
            REV_ACCOUNT_NAME + text +
            REV_ACCOUNT_NUMBER + text +
            REV_BANK_NAME + text +
            REV_ACCOUNT_TYPE + text +
            REV_BANK_CODE + text +
            REV_READY + text +
            REV_AMOUNT + text +
            REV_TYPE + text +
            DEPARTMENT + text +
            DEPT + text +
            CAT + text +
            CATEGORY + text +
            REV_SUBS + text +
            REV_RRN + text +
            REV_ACCT + text +
            REV_REM_SEV_TYPE + text +
            REV_REPORT_SEV_TYPE + text +
            REV_SC + text +
            REV_MOD + text +
            REV_AIRPORT + text +
            EMR + text +
            PRICETYPE + " TEXT ); " ;




    //create payment table
    public static final String CREATE_PAYMENT_TABLE = create + PAYMENT_TABLE_NAME + createId +
            PAYMENT_AMOUNT + text +
            PAYMENT_PAYER_NAME + text +
            PAYMENT_PAYER_PHONE + text +
            PAYMENT_PAYER_EMAIL + text +
            PAYMENT_PAYMENT_DATE + text +
            PAYMENT_PAYMENT_TIME + text +
            PAYMENT_AGENT + text +
            PAYMENT_REVENUE_HEAD + text +
            PAYMENT_SYNCED + text +
            PAYMENT_REF + text +
            PAYMENT_PREVIOUS_BALANCE + text +
            PAYMENT_CURRENT_BALANCE + text +
            PAYMENT_RRR + text +
            PAYMENT_LOCATION + text +
            PAYMENT_MDA_ID + text +
            PAYMENT_REV_ID + text +
            PAYMENT_AGENT_ID + text +
            PAYMENT_QUANTITY + text +
            PAYMENT_TRANS_FEE + text +
            PAYMENT_TOTAL + text +
            PAYMENT_METHOD + text +
            PAYMENT_REV_CODE + text +
            PAYMENT_LAST_SERIAL + text +
            DEPT + text +
            DEPARTMENT + text +
            CAT + text +
            CATEGORY + text +
            PAYMENT_DISCOUNT + text +
            PAYMENT_MAIN_AMT + text +
            PAYMENT_DESC + text +
            EMR + text +
            PRICETYPE + text +
            SHIFT + text +
            PAYMENT_SUBS +  text +
            PAYMENT_PAY_POINT +  text +
            PAYMENT_PAY_POINT_ID + " INTEGER, " +
            PAYMENT_AIRPORT + " TEXT);";









    //create wallet table
    public static final String CREATE_WALLET_TABLE =  " CREATE TABLE IF NOT EXISTS  "  + WALLET_TABLE_NAME +  "  (  "  +
            ID +  "  INTEGER PRIMARY KEY AUTOINCREMENT,  "  +
            WALLET_AGENT +  "  TEXT, "  +
            WALLET_BALANCE +  "  TEXT,  "  +
            WALLET_AGENT_ID +  "  TEXT  "  +
             " ); " ;



    //create user table
    public static final String CREATE_USER_TABLE =  create + USER_TABLE_NAME + createId  +
            USER_NAME +  text  +
            USER_PHONE_NO +  text  +
            USER_EMAIL +  text  +
            USER_ADDRESS +  text  +
            USER_ORGANIZATION +  text  +
            USER_ID +  text  +
            USER_USERNAME +  text  +
            USER_IS_LOGGED_IN + text +
            USER_LAST_LOGIN +  text  +
            USER_LOGIN_TIME + text +
            USER_MDA_ID + text +
            USER_BALANCE + " REAL, " +
            USER_DEF_PHONE + text +
            USER_DEF_EMAIL + text +
            USER_QUANTITY + text +
            USER_MDA_CODE +  text  +
            USER_SERIAL_NO +  text  +
            USER_API_KEY +  text  +
            USER_AIRPORT +  text  +
            USER_PAY_POINT +  text  +
            USER_LOCATION +  text  +
            USER_PAY_POINT_ID +  " INTEGER, "  +
            USER_DEVICE +  " TEXT ); " ;



}
