package com.example.slee.sqlitedemo.bean;


/**
 * Created by S.Lee on 2017/11/9.
 */

public class StudentBean extends BaseBean{
    private int id;
    private String name;
    private int age;

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

    public String toString(){
        return  String.format(" (id,name,age) values (%d,\'%s\',%d)",id,name,age);
    }

    public String[] getTypes(String[] strs){
        if(strs.length == 0)
            return new String[]{"INTEGER","TEXT","INTEGER"};
        String[] returns = new String[3];
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
            }
        }
        if(strs.length < 3){
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

    public void allSets(String str,Object content){
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
        }
    }
}
