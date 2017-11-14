package com.example.slee.sqlitedemo.com.example.slee.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.slee.sqlitedemo.bean.StudentBean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by S.Lee on 2017/11/9.
 */

public class DBManager {
    public interface DataBaseWriteListener{
        void writeComplited(boolean success);
    }
    public static void addData(Context mContext, List<?> datas, DataBaseWriteListener listener){
        OrderDBHelper helper = OrderDBHelper.getInstance(mContext, Contant.TABLE_NAME,Contant.DB_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.beginTransaction();
        //insert替换成replace针对含有primarykey的情况
        for(int i = 0 ; i < datas.size();i ++){
            db.execSQL("replace into "+Contant.TABLE_NAME+datas.get(i).toString());
        }
        db.setTransactionSuccessful();
        db.endTransaction();

        db.close();

        listener.writeComplited(true);

    }
    public static List<Object> queryData(Context mContext,String[] colums,String selection,String className){
        OrderDBHelper helper = OrderDBHelper.getInstance(mContext, Contant.TABLE_NAME,Contant.DB_VERSION);
        SQLiteDatabase dbRead = helper.getReadableDatabase();
      //  Cursor mCursor = dbRead.query(Contant.TABLE_NAME,null,"name=\"Jackson\"",null,null,null,null,null);
        Cursor mCursor = dbRead.query(Contant.TABLE_NAME,colums,selection,null,null,null,null,null);
        List<Object> studentBeen = getColumns(mCursor,className,colums);
        mCursor.close();
        dbRead.close();
        return studentBeen;
    }

    public static void deleteData(Context mcontext,String whereClause,String[] args){
        OrderDBHelper helper = OrderDBHelper.getInstance(mcontext,Contant.TABLE_NAME, Contant.DB_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();
        db.delete(Contant.TABLE_NAME,whereClause+" = ?",args);
        db.setTransactionSuccessful();
        db.endTransaction();

    }

    public  static void insertData(Context mContext,String nullColumnHack,StudentBean bean){
        OrderDBHelper helpder = OrderDBHelper.getInstance(mContext,Contant.TABLE_NAME,Contant.DB_VERSION);
        SQLiteDatabase db = helpder.getWritableDatabase();
        db.beginTransaction();

//        insert into Orders(Id, CustomName, OrderPrice, Country) values (7, "Jne", 700, "China");
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", bean.getId());
        contentValues.put("name", bean.getName());
        contentValues.put("age", bean.getAge());
        db.insertOrThrow(Contant.TABLE_NAME, nullColumnHack, contentValues);
   //     db.insert(Contant.TABLE_NAME,nullColumnHack,contentValues);

        db.setTransactionSuccessful();
        db.endTransaction();


    }

    public static void updateDatas(Context mContext,String updateKey,String updateValues,String selects,String[] selectArgs){
        OrderDBHelper helper = OrderDBHelper.getInstance(mContext,Contant.TABLE_NAME,Contant.DB_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();

// update Orders set OrderPrice = 800 where Id = 6
        ContentValues cv = new ContentValues();
        cv.put(updateKey, updateValues);
        db.update(Contant.TABLE_NAME, cv, selects+" = ?", selectArgs);
        db.setTransactionSuccessful();
        db.endTransaction();


    }


    private static List<Object> getColumns(Cursor mCursor,String beanPackage,String[] colums) {
        List<Object> datas = new ArrayList<>();
        try {
            Class<?>  mclass = Class.forName(beanPackage);
            Object mobject = mclass.newInstance();
            Method method = mclass.getMethod("getTypes",String[].class);
            String[] result = (String[]) method.invoke(mobject,(Object)colums);
            for(int i = 0 ; i < mCursor.getCount();i++){
                while (mCursor.moveToNext()){
                    Object mbean = mclass.newInstance();
                    Method method1 = mclass.getMethod("allSets",String.class ,Object.class);
                    for(int j = 0 ; j < result.length;j++){
                        switch (result[j]){
                            case "INTEGER":
                                method1.invoke(mbean,mCursor.getColumnName(j),mCursor.getInt(j));
                                break;
                            case "TEXT":
                                method1.invoke(mbean,mCursor.getColumnName(j),mCursor.getString(j));
                                break;
                        }
                    }
                    Log.i("This Student:",mbean.toString());
                    datas.add(mbean);
                }
            }
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }
}
