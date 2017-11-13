package com.example.slee.sqlitedemo;

import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.slee.sqlitedemo.bean.StudentBean;
import com.example.slee.sqlitedemo.com.example.slee.helper.Contant;
import com.example.slee.sqlitedemo.com.example.slee.helper.DBManager;
import com.example.slee.sqlitedemo.com.example.slee.helper.OrderDBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editText,id_edt,name_edt,age_edt;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();



    }



    public void onAddClick(View view){

        List<StudentBean> beans = new ArrayList<>();
        StudentBean bean = new StudentBean();
        bean.setId(Integer.parseInt(id_edt.getText().toString()));
        bean.setAge(Integer.parseInt(age_edt.getText().toString()));
        bean.setName(name_edt.getText().toString());
        beans.add(bean);


        DBManager.addData(MainActivity.this, beans,new DBManager.DataBaseWriteListener() {
            @Override
            public void writeComplited(boolean success) {
                if(success) textview.setText("success");
            }
        });

    }

    public void onQueryClick(View view){
        //new String[]{"id"},"name=\"Julian\""
        List<StudentBean> beans = DBManager.queryData(MainActivity.this,null,null);
        String s = "";
        for(int i = 0 ; i < beans.size(); i ++){
            s+=beans.get(i).toString();
        }
        textview.setText(s);

    }

    public void onDeleteClick(View view){
        DBManager.deleteData(MainActivity.this,"id",new String[]{"1"});

    }

    /**
     * 插入数据 同じなprimarykeyじゃ駄目、
     * @param view
     */
    public void onInsertClick(View view){
        StudentBean bean = new StudentBean();
        bean.setId(Integer.parseInt(id_edt.getText().toString()));
        bean.setAge(Integer.parseInt(age_edt.getText().toString()));
        bean.setName(name_edt.getText().toString());

        DBManager.insertData(this,"name",bean);
    }

    public void onUpdateClick(View view){
        DBManager.updateDatas(this);

    }
    private void init(){
        editText = (EditText)findViewById(R.id.editText);
        textview = (TextView)findViewById(R.id.textview);
        id_edt = (EditText)findViewById(R.id.editText3 );
        name_edt = (EditText)findViewById(R.id.editText2);
        age_edt = (EditText)findViewById(R.id.editText4);
    }
}
