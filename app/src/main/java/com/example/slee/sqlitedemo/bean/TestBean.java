package com.example.slee.sqlitedemo.bean;

import android.content.ContentValues;

/**
 * Created by S.Lee on 2017/11/14.
 */

public class TestBean extends BaseBean {
    @Override
    public String[] getTypes(String[] strs) {
        return new String[0];
    }

    @Override
    public void allSets(String str, Object content) {

    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public ContentValues getContentValues(BaseBean bean) {
        return null;
    }
}
