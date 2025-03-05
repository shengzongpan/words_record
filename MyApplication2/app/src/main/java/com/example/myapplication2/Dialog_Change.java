package com.example.myapplication2;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import java.io.File;
import java.util.ArrayList;

public class Dialog_Change {
    private MainActivity mainActivity;
    ArrayList<String> strs;

    public Dialog_Change(MainActivity mainActivity, ArrayList<String> strs){
        this.mainActivity = mainActivity;
        this.strs = strs;
    }


    public void showdialog_change(int position) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(mainActivity);

        LayoutInflater inflater = mainActivity.getLayoutInflater();
        View dialogview2 = inflater.inflate(R.layout.dialog_change, null);
        builder1.setView(dialogview2);

        Dialog dialog2 = builder1.create();
        dialog2.setCancelable(false);
        //背景设为透明
        if(dialog2.getWindow() != null){
            dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog2.show();
        EditText editw = dialogview2.findViewById(R.id.input4);
        EditText editx = dialogview2.findViewById(R.id.input5);
        EditText editm = dialogview2.findViewById(R.id.input6);

        Button sure = dialogview2.findViewById(R.id.sure_b2);
        Button cancel = dialogview2.findViewById(R.id.cancel2);

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputw = editw.getText().toString();
                String inputx = editx.getText().toString();
                String inputm = editm.getText().toString();
                Words words = new Words(inputw, inputm, inputx);
                String a = words.getEn_word() + "  --  " + words.getCixing() + "  --  " + words.getCn_mean();
                strs.set(position, a);
                ArrayAdapter<String> adapter = mainActivity.getAdapter();
                //通知适配器数据已经更改
                adapter.notifyDataSetChanged();
                dialog2.dismiss();
                //获取路径
                File externfile = mainActivity.getExternfile();
                String filepath = mainActivity.getFilepath();
                int n = mainActivity.getN();
                File file = new File(externfile, filepath);
                //存入文件
                File_out file_out = new File_out(filepath, n);
                file_out.write(strs, file);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
    }
}
