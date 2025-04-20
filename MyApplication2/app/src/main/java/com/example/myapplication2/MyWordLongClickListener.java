package com.example.myapplication2;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.File;
import java.util.ArrayList;

public class MyWordLongClickListener implements AdapterView.OnItemLongClickListener {
    private final Fragment fragment;
    private ArrayList<Words> words;
    private MainActivity mainActivity;
    private WordAdapter adapter;
    private String filepath;
    private File externfile;
    private TextView textRecord;

    public MyWordLongClickListener(Fragment fragment, ArrayList<Words> words, MainActivity mainActivity, WordAdapter adapter, String filepath, File externfile, TextView textRecord) {
        this.fragment = fragment;
        this.words = words;
        this.mainActivity = mainActivity;
        this.adapter = adapter;
        this.filepath = filepath;
        this.externfile = externfile;
        this.textRecord = textRecord;
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
                        words.remove(position);
                        adapter.notifyDataSetChanged();
                        textRecord.setText("记录数量: " + words.size());
                        dialog.dismiss();
                        ArrayList<String> str = new ArrayList<>();
                        for(int i = 0; i < words.size(); i++) {
                            str.add(words.get(i).getEn_word() + "  --  " + words.get(i).getCixing() + "  --  " + words.get(i).getCn_mean());
                        }
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
