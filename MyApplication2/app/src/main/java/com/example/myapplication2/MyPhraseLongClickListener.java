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
import java.util.List;

public class MyPhraseLongClickListener implements AdapterView.OnItemLongClickListener {
    private final Fragment fragment;
    private List<Sentence> phrases;
    private MainActivity mainActivity;
    private PhraseAdapter adapter;
    private String filepath;
    private File externfile;

    public MyPhraseLongClickListener(Fragment fragment, List<Sentence> phrases, MainActivity mainActivity, PhraseAdapter adapter, String filepath, File externfile) {
        this.fragment = fragment;
        this.phrases = phrases;
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
                        phrases.remove(position);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                        ArrayList<String> str = new ArrayList<>();
                        for(int i = 0; i < phrases.size(); i++) {
                            str.add(phrases.get(i).getPhrase() + " -- " + phrases.get(i).getMean());
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
