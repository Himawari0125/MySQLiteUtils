package com.example.slee.sqlitedemo.com.example.slee.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by S.Lee on 2017/11/8.
 */

public class OrderDBHelper extends SQLiteOpenHelper {
    private String TABLE_NAME;
    private String table_Clause;

    private static Map<String,OrderDBHelper> helper_Map = new HashMap<>();
    private static OrderDBHelper helper;

    public static OrderDBHelper getInstance(Context context,String TABLE_NAME,int DB_VERSION,String tableClause){
        if(!helper_Map.containsKey(tableClause)){
            if (helper == null){
                synchronized (OrderDBHelper.class){
                    if (helper == null){
                        helper = new OrderDBHelper(context,TABLE_NAME,DB_VERSION,tableClause);
                    }
                }
            }
            helper_Map.put(tableClause,helper);
            helper = null;
        }
        return helper_Map.get(tableClause);
    }

    private OrderDBHelper(Context context,String TABLE_NAME,int DB_VERSION,String tableClause) {
        super(context, Contant.DB_NAME, null, DB_VERSION);
        this.TABLE_NAME = TABLE_NAME;
        this.table_Clause = tableClause;
    }


    //Called when the database is created for the first time.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists "+TABLE_NAME+table_Clause;
        sqLiteDatabase.execSQL(sql);
        Log.i("----------","Create database"+TABLE_NAME);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
        Log.i("----------","Upgrade database"+sqLiteDatabase.toString());
    }
}