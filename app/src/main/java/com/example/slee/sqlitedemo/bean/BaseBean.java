package com.example.slee.sqlitedemo.bean;

import android.content.ContentValues;

/**
 * Created by S.Lee on 2017/11/9.
 */

public abstract class BaseBean {
       public abstract String[] getTypes(String[] strs);
       public abstract void allSets(String str,Object content);
       public abstract String toString();
       public abstract ContentValues getContentValues(BaseBean bean);
}
