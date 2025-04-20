package com.example.myapplication2;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class MyPhraseClickListener implements AdapterView.OnItemClickListener {
    private final Fragment_sentence fragment;
    private ArrayList<String> strs;

    public MyPhraseClickListener(Fragment_sentence fragment){
        this.fragment = fragment;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(fragment.getContext(), Detail_interface.class);
        strs = fragment.getStr_phrase();
        intent.putStringArrayListExtra("strs", strs);
        intent.putExtra("position", position + "");
        intent.putExtra("id", "phrase");
        fragment.startActivity(intent);
    }
}
