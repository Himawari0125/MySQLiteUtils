package com.example.slee.sqlitedemo.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;


/**
 * Created by 25646 on 2017/8/8.
 */

public class SeveralDialogs {
    private AlertDialog.Builder inputDialog;
    private Context mContext;
    public SeveralDialogs(Context context){
        this.mContext = context;
    }

    /**
     * 确认Dialog
     */
    public void showNormalDialog(int drawable,String title,String detail,DialogInterface.OnClickListener listener){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mContext);
        normalDialog.setIcon(drawable);
        normalDialog.setTitle(title);
        normalDialog.setMessage(detail);
        normalDialog.setPositiveButton("确定", listener);
        normalDialog.setNegativeButton("关闭",listener);
        normalDialog.show();

    }

    /**
     * 列表Dialog
     */
    public void showListDialog(String[] items,DialogInterface.OnClickListener listener) {
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(mContext);
        listDialog.setTitle("我是一个列表Dialog");
        listDialog.setItems(items,listener);
        listDialog.show();
    }

    /**
     * 单选Dialog
     */
    public void showSingleChoiceDialog(String[] items,DialogInterface.OnClickListener listener){
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(mContext);
        singleChoiceDialog.setTitle("我是一个单选Dialog");
        // 第二个参数是默认选项，此处设置为0
        singleChoiceDialog.setSingleChoiceItems(items, 0,listener);
        singleChoiceDialog.setPositiveButton("确定",listener);
        singleChoiceDialog.show();
    }

    /**
     * 多选Dialog
     * initChoiceSets:设置默认选中的选项，全为false默认均未选中
     */
    public void showMultiChoiceDialog(String[] items,boolean[] initChoiceSets,
                                       DialogInterface.OnMultiChoiceClickListener multilistener,
                                       DialogInterface.OnClickListener listener) {
        AlertDialog.Builder multiChoiceDialog =
                new AlertDialog.Builder(mContext);
        multiChoiceDialog.setTitle("我是一个多选Dialog");
        multiChoiceDialog.setMultiChoiceItems(items, initChoiceSets,multilistener);
        multiChoiceDialog.setPositiveButton("确定",listener);
        multiChoiceDialog.show();
    }

    /**
     * 进度条dialog
     */
    public void showWaitingDialog(String title,String message) {
    /* 等待Dialog具有屏蔽其他控件的交互能力
     * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
     * 下载等事件完成后，主动调用函数关闭该Dialog
     */
        ProgressDialog waitingDialog=
                new ProgressDialog(mContext);
        waitingDialog.setTitle(title);
        waitingDialog.setMessage(message);
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();
    }

    /**
     * 进度条dialog
     */
    public void showProgressDialog(String title) {
        final int MAX_PROGRESS = 100;
        final ProgressDialog progressDialog =
                new ProgressDialog(mContext);
        progressDialog.setProgress(0);
        progressDialog.setTitle(title);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.show();
    /* 模拟进度增加的过程
     * 新开一个线程，每个100ms，进度增加1
     */
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress= 0;
                while (progress < MAX_PROGRESS){
                    try {
                        Thread.sleep(100);
                        progress++;
                        progressDialog.setProgress(progress);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                // 进度达到最大值后，窗口消失
                progressDialog.cancel();
            }
        }).start();
    }

    /**
     * 编辑dialog
     */
    public void showInputDialog( String title, final DialogClick listener) {
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(mContext);
        final EditText editText = new EditText(mContext);
        inputDialog.setTitle(title).setView(editText);
        listener.setEditText(editText);
        inputDialog.setPositiveButton("确定",listener).show();


    }

    /**
     * 编辑Dialog里EditText内容获取
     */
    public abstract class DialogClick implements DialogInterface.OnClickListener{

        private EditText editText;
        private void setEditText(EditText editText){
            this.editText = editText;
        }
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            onClickSecond(dialogInterface,i,editText.getText().toString());
        }

        public abstract void onClickSecond(DialogInterface dialogInterface, int i, String EditStr) ;
    }





}
