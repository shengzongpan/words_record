package com.example.myapplication2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class DialogSenClickAdd implements View.OnClickListener{

    private final Fragment_sentence fragmentSentence;
    private ArrayList<String> str;
    private ArrayAdapter<String> adapter;

    public DialogSenClickAdd(Fragment_sentence fragmentSentence) {
        this.fragmentSentence = fragmentSentence;
    }
    @Override
    public void onClick(View v) {
        this.str = fragmentSentence.getSen_str();
        this.adapter = fragmentSentence.getAdapter();
        show_add_dialog();
    }

    public void show_add_dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(fragmentSentence.getActivity());

        LayoutInflater layoutInflater = fragmentSentence.getLayoutInflater();
        View dialogview = layoutInflater.inflate(R.layout.sentence_add_dialog, null);

        builder.setView(dialogview);
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);//点击其他地方关闭
        dialog.show();
        //设置透明背景色
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        EditText input_p = dialogview.findViewById(R.id.inputs1);
        EditText input_m = dialogview.findViewById(R.id.inputs2);

        Button b_sure = dialogview.findViewById(R.id.sure_bs);
        Button b_cancel = dialogview.findViewById(R.id.cancels);

        b_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phrase = input_p.getText().toString();
                String s_mean = input_m.getText().toString();
                Sentence sentence = new Sentence(phrase, s_mean);
                str.add(sentence.getPhrase() + " -- " + sentence.getMean());
                //通知适配器数据更改
                adapter.notifyDataSetChanged();
                //关闭窗口
                dialog.dismiss();
            }
        });

        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
