package com.example.slee.sqlitedemo.com.example.slee.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by S.Lee on 2017/11/8.
 */

public class OrderDBHelper extends SQLiteOpenHelper {
    private String DB_Name;
    private int DB_Version;

    private static OrderDBHelper helper;

    public static OrderDBHelper getInstance(Context context,String DB_NAME,int DB_VERSION){
        if (helper == null){
            synchronized (OrderDBHelper.class){
                if (helper == null){
                    helper = new OrderDBHelper(context,DB_NAME,DB_VERSION);
                }
            }
        }
        return helper;
    }

    private OrderDBHelper(Context context,String DB_NAME,int DB_VERSION) {
        super(context, DB_NAME, null, DB_VERSION);
        this.DB_Name = DB_NAME;
        this.DB_Version = DB_VERSION;
    }


    //Called when the database is created for the first time.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists "+DB_Name+"(id integer primary key,name text,age integer)";
        sqLiteDatabase.execSQL(sql);
        Log.i("----------","Create database"+DB_Name);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i("----------","Upgrade database"+sqLiteDatabase.toString());
    }
}