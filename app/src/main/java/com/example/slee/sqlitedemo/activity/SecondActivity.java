package com.example.slee.sqlitedemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slee.sqlitedemo.Myexceptions.InputException;
import com.example.slee.sqlitedemo.R;
import com.example.slee.sqlitedemo.bean.TeacherBean;
import com.example.slee.sqlitedemo.com.example.slee.helper.Contant;
import com.example.slee.sqlitedemo.com.example.slee.helper.DBManager;
import com.example.slee.sqlitedemo.utils.ConvertUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by S.Lee on 2017/11/15.
 */

public class SecondActivity extends AppCompatActivity {
    private EditText id_edt,age_edt,name_edt,classes_edt;
    private TextView textView;

    private static final int IDEDITREQ = 10001;
    private static final int NAMEEDITREQ = 10002;
    private static final int AGEEDITREQ = 10003;
    private static final int CLASSESSREQ = 10004;

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
        textView = (TextView)findViewById(R.id.textView);
    }

    public void onAddClick(View view){

        List<TeacherBean> beans = new ArrayList<>();
        TeacherBean bean = new TeacherBean();
        try {
            bean.setId(ConvertUtils.StringParseInteger(id_edt.getText().toString(),IDEDITREQ));
            bean.setAge(ConvertUtils.StringParseInteger(age_edt.getText().toString(),AGEEDITREQ));
            bean.setName(ConvertUtils.StringParseString(name_edt.getText().toString(),NAMEEDITREQ));
            bean.setClasses(ConvertUtils.StringParseString(classes_edt.getText().toString(),CLASSESSREQ));
        } catch (InputException e) {
            e.printStackTrace();
            dealwithError(e);
        }
        beans.add(bean);
        DBManager.addData(SecondActivity.this,Contant.DB_NAME_TEACHER,  Contant.TEACHER_TABLE_CLAUSE, beans,new DBManager.DataBaseWriteListener() {
            @Override
            public void writeComplited(boolean success) {
                if(success) textView.setText("success");
            }
        });

    }
    public void onDeleteClick(View view){
        DBManager.deleteData(this, Contant.DB_NAME_TEACHER, Contant.TEACHER_TABLE_CLAUSE, "id",new String[]{"1"});
    }
    public void onQueryClick(View view){
        List<Object> beans = DBManager.queryData(SecondActivity.this,Contant.DB_NAME_TEACHER,
                Contant.TEACHER_TABLE_CLAUSE, new String[]{},null,"com.example.slee.sqlitedemo.bean.TeacherBean");
        String s = "";
        for(int i = 0 ; i < beans.size(); i ++){
            s+=beans.get(i).toString()+"\n";
        }
        textView.setText(s);

    }
    public void onInsertClick(View view){
        TeacherBean bean = new TeacherBean();
        try {
            bean.setId(ConvertUtils.StringParseInteger(id_edt.getText().toString(),IDEDITREQ));
            bean.setAge(ConvertUtils.StringParseInteger(age_edt.getText().toString(),AGEEDITREQ));
            bean.setName(ConvertUtils.StringParseString(name_edt.getText().toString(),NAMEEDITREQ));
            bean.setClasses(ConvertUtils.StringParseString(classes_edt.getText().toString(),CLASSESSREQ));
        } catch (InputException e) {
            e.printStackTrace();
            dealwithError(e);
        }

        try{
            DBManager.insertData(this,Contant.DB_NAME_TEACHER, Contant.TEACHER_TABLE_CLAUSE, "name",bean);
        }catch (Exception exception){
            Log.e("Exception:",exception.getMessage());
            Toast.makeText(this,exception.getMessage(),Toast.LENGTH_SHORT).show();
            id_edt.setText("");
        }

    }
    public void onUpdateClick(View view){
        DBManager.updateDatas(this,Contant.DB_NAME_TEACHER, Contant.TEACHER_TABLE_CLAUSE,"name",name_edt.getText().toString(),"id",new String[]{id_edt.getText().toString()});
    }
    public void onSearchClick(View view){

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
            case CLASSESSREQ:
                classes_edt.setText("");
                Toast.makeText(this, "Classes"+exception.getMessage(), Toast.LENGTH_SHORT).show();
                break;
        }
        return;
    }

}
