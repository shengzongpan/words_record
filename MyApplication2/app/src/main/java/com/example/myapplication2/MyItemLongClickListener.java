package com.example.myapplication2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import java.io.File;
import java.util.ArrayList;

public class MyItemLongClickListener implements AdapterView.OnItemLongClickListener {
    private final Fragment fragment;
    private ArrayList<String> str;
    private MainActivity mainActivity;
    private ArrayAdapter<String> adapter;
    private String filepath;
    private File externfile;

    public MyItemLongClickListener(Fragment fragment, ArrayList<String> str, MainActivity mainActivity, ArrayAdapter<String> adapter, String filepath, File externfile) {
        this.fragment = fragment;
        this.str = str;
        this.mainActivity = mainActivity;
        this.adapter = adapter;
        this.filepath = filepath;
        this.externfile = externfile;
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getContext());
                LayoutInflater inflater = fragment.getLayoutInflater();
                View dialogview = inflater.inflate(R.layout.dialog_delete, null);
                builder.setView(dialogview);

                AlertDialog dialog = builder.create();
                if(dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
                dialog.setCancelable(false);
                dialog.show();
                Button b_sure = dialog.findViewById(R.id.delete_sure);
                Button b_cancel = dialog.findViewById(R.id.delete_cancel);

                b_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        str.remove(position);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();

                        //存入文件
                        File file = new File(externfile, filepath);
                        Dfile.write(str, file);
                    }
                });
                b_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        return true;
    }
}
