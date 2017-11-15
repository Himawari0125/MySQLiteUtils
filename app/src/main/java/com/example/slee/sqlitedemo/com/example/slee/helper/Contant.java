package com.example.slee.sqlitedemo.com.example.slee.helper;

/**
 * Created by S.Lee on 2017/11/8.
 */

public class Contant {
    public static final String DB_NAME = "Test.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Orders";
    public static final String STUDENT_TABLE_CLAUSE = "(id integer primary key,name text,age integer)";

    public static final String TEACHER_TABLE_CLAUSE = "(id integer primary key,name text,age integer,classes text)";
}
