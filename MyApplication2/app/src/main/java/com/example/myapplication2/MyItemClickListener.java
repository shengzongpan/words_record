package com.example.myapplication2;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class MyItemClickListener implements AdapterView.OnItemClickListener {
    private MainActivity mainActivity;
    private ArrayList<String> strs;

    public MyItemClickListener(MainActivity mainActivity, ArrayList<String> strs){
        this.mainActivity = mainActivity;
        this.strs = strs;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //textView的显示,子线程里不能改变ui
        //tx1 = (TextView) findViewById(R.id.d2_t1);
        //tx1.setText(strs.get(position));
        //所以得加个runOnUiThread
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                /**getLayoutInflater在除了mainactivity中无法直接使用
                 用下面方法进行使用**/
                //dialog2转成view
                LayoutInflater inflater = mainActivity.getLayoutInflater();
                View dialogview = inflater.inflate(R.layout.dialog2, null);
                //添加布局，设置是否可以中止
                builder.setView(dialogview);
                //builder.setCancelable(false);

                //显示dialog框
                AlertDialog dialog = builder.create();
                //设置透明色
                dialog.setCancelable(true);
                if (dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
                //textview 进行更新
                TextView tx = dialogview.findViewById(R.id.d2_t1);
                tx.setText(strs.get(position));
                dialog.show();
                Button td = dialogview.findViewById(R.id.b_change);
                //点击"修改"之后打开dialog_change.xml
                td.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Dialog_Change dialog_change = new Dialog_Change(mainActivity, strs);
                        dialog_change.showdialog_change(position);
                    }
                });
            }
        });
    }
}
