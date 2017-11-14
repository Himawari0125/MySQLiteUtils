package com.example.slee.sqlitedemo.Myexceptions;

/**
 * Created by S.Lee on 2017/11/14.
 */

public class InputException extends Exception {
    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public InputException(String s,int eCode){
        super(s);
        setErrorCode(eCode);

    }
}
