package com.example.slee.sqlitedemo.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slee.sqlitedemo.Myexceptions.InputException;
import com.example.slee.sqlitedemo.R;
import com.example.slee.sqlitedemo.bean.StudentBean;
import com.example.slee.sqlitedemo.com.example.slee.helper.Contant;
import com.example.slee.sqlitedemo.com.example.slee.helper.DBManager;
import com.example.slee.sqlitedemo.utils.ConvertUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText editText,id_edt,name_edt,age_edt;
    private TextView textview;
    private static final int IDEDITREQ = 10001;
    private static final int NAMEEDITREQ = 10002;
    private static final int AGEEDITREQ = 10003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void onAddClick(View view){
        List<StudentBean> beans = new ArrayList<>();
        StudentBean bean = new StudentBean();
        try {
            bean.setId(ConvertUtils.StringParseInteger(id_edt.getText().toString(),IDEDITREQ));
            bean.setAge(ConvertUtils.StringParseInteger(age_edt.getText().toString(),AGEEDITREQ));
            bean.setName(ConvertUtils.StringParseString(name_edt.getText().toString(),NAMEEDITREQ));
        } catch (InputException e) {
            e.printStackTrace();
            dealwithError(e);
        }
        beans.add(bean);
        DBManager.addData(MainActivity.this,Contant.DB_NAME_STUDENT,  Contant.STUDENT_TABLE_CLAUSE, beans,new DBManager.DataBaseWriteListener() {
            @Override
            public void writeComplited(boolean success) {
                if(success) textview.setText("success");
            }
        });
    }

    public void onQueryClick(View view){
        //new String[]{"id"},"name=\"Julian\""
        List<Object> beans = DBManager.queryData(MainActivity.this,Contant.DB_NAME_STUDENT,  Contant.STUDENT_TABLE_CLAUSE, new String[]{},null,"com.example.slee.sqlitedemo.bean.StudentBean");
        String s = "";
        for(int i = 0 ; i < beans.size(); i ++){
            s+=beans.get(i).toString()+"\n";
        }
        textview.setText(s);
    }

    public void onDeleteClick(View view){
        DBManager.deleteData(MainActivity.this, Contant.DB_NAME_STUDENT, Contant.STUDENT_TABLE_CLAUSE, "id",new String[]{"1"});
    }

    /**
     * 插入数据 同じなprimarykeyじゃ駄目、
     * @param view
     */
    public void onInsertClick(View view){
        StudentBean bean = new StudentBean();
        try {
            bean.setId(ConvertUtils.StringParseInteger(id_edt.getText().toString(),IDEDITREQ));
            bean.setAge(ConvertUtils.StringParseInteger(age_edt.getText().toString(),AGEEDITREQ));
            bean.setName(ConvertUtils.StringParseString(name_edt.getText().toString(),NAMEEDITREQ));
        } catch (InputException e) {
            e.printStackTrace();
            dealwithError(e);
        }

        try{
            DBManager.insertData(this,Contant.DB_NAME_STUDENT, Contant.STUDENT_TABLE_CLAUSE, "name",bean);
        }catch (Exception exception){
            Log.e("Exception:",exception.getMessage());
            Toast.makeText(this,exception.getMessage(),Toast.LENGTH_SHORT).show();
            id_edt.setText("");
        }

    }

    public void onUpdateClick(View view){
        DBManager.updateDatas(this,Contant.DB_NAME_STUDENT, Contant.STUDENT_TABLE_CLAUSE,"name",name_edt.getText().toString(),"id",new String[]{id_edt.getText().toString()});

    }
    private void init(){
        editText = (EditText)findViewById(R.id.editText);
        textview = (TextView)findViewById(R.id.textview);
        id_edt = (EditText)findViewById(R.id.editText3 );
        name_edt = (EditText)findViewById(R.id.editText2);
        age_edt = (EditText)findViewById(R.id.editText4);
    }

    /**
     * 处理用户输入错误 UI层面展示
     * @param exception
     */
    private void dealwithError(InputException exception){
        switch(exception.getErrorCode()){
            case IDEDITREQ:
                id_edt.setText("");
                Toast.makeText(this,"ID"+exception.getMessage(),Toast.LENGTH_SHORT).show();
                break;
            case NAMEEDITREQ:
                name_edt.setText("");
                Toast.makeText(this,"NAME"+exception.getMessage(),Toast.LENGTH_SHORT).show();
                break;
            case AGEEDITREQ:
                age_edt.setText("");
                Toast.makeText(this,"AGE"+exception.getMessage(),Toast.LENGTH_SHORT).show();
                break;
        }
        return;
    }


    public void onIntentClick(View view){
        startActivity(new Intent(this,SecondActivity.class));
    }

}
