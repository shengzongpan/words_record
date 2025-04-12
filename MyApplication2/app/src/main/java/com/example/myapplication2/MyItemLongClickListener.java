package com.example.myapplication2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;

public class MyItemLongClickListener implements AdapterView.OnItemLongClickListener {
    private final Fragment_ci fragment_ci;
    private ArrayList<String> str;

    public MyItemLongClickListener(Fragment_ci fragment_ci) {
        this.fragment_ci = fragment_ci;
        this.str = this.fragment_ci.getStrs();
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        fragment_ci.getMainActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(fragment_ci.getContext());
                LayoutInflater inflater = fragment_ci.getLayoutInflater();
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
                        ArrayAdapter<String> adapter = fragment_ci.getAdapter();
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                        //获取路径
                        File externfile = fragment_ci.getExternfilef_w();
                        String filepath = fragment_ci.getFilepath();
                        File file = new File(externfile, filepath);
                        //存入文件
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
        return false;
    }
}
