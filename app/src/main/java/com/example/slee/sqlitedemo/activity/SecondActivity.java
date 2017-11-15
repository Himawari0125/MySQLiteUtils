package com.example.slee.sqlitedemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.slee.sqlitedemo.R;

/**
 * Created by S.Lee on 2017/11/15.
 */

public class SecondActivity extends AppCompatActivity {
    private EditText id_edt,age_edt,name_edt,classes_edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
    }

    private void init() {
        id_edt = (EditText)findViewById(R.id.editText6);
        age_edt = (EditText)findViewById(R.id.editText5);
        name_edt = (EditText)findViewById(R.id.editText7);
        classes_edt = (EditText)findViewById(R.id.editText8);
    }

    public void onAddClick(View view){

    }
    public void onDeleteClick(View view){

    }
    public void onQueryClick(View view){

    }
    public void onInsertClick(View view){

    }
    public void onUpdateClick(View view){

    }
    public void onSearchClick(View view){

    }

}
