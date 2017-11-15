package com.example.slee.sqlitedemo.bean;

import android.content.ContentValues;

/**
 * Created by S.Lee on 2017/11/15.
 */

public class TeacherBean extends BaseBean {
    private int id;
    private String name;
    private int age;
    private String classes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    @Override
    public String[] getTypes(String[] strs) {
        if(strs.length == 0)
            return new String[]{"INTEGER","TEXT","INTEGER","TEXT"};
        String[] returns = new String[4];
        for(int i = 0 ; i < strs.length;i++){
            switch (strs[i]){
                case "id":
                    returns[0] = "INTEGER";
                    break;
                case "name":
                    returns[1] = "TEXT";
                    break;
                case "age":
                    returns[2] = "INTEGER";
                    break;
                case "classes":
                    returns[3] = "TEXT";
                    break;
            }
        }
        if(strs.length < 4){
            String[] strings = new String[strs.length];
            int i = 0;
            for(String str:returns){
                if(str!=null&&i<strs.length){
                    strings[i++] = str;
                }
            }
            return strings;
        }
        return returns;
    }

    @Override
    public void allSets(String str, Object content) {
        switch (str){
            case "id":
                setId((Integer) content);
                break;
            case "name":
                setName((String) content);
                break;
            case "age":
                setAge((Integer) content);
                break;
            case "classes":
                setClasses((String)content);
                break;
        }
    }

    @Override
    public String toString() {
        return String.format(" (id,name,age,classes) values (%d,\'%s\',%d,\'%s\')",id,name,age,classes);
    }

    @Override
    public ContentValues getContentValues(BaseBean bean) {
        TeacherBean mbean = (TeacherBean)bean;
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",mbean.getId());
        contentValues.put("name",mbean.getName());
        contentValues.put("age",mbean.getAge());
        contentValues.put("classes",mbean.getClasses());
        return contentValues;
    }
}
