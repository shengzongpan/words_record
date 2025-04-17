package com.example.myapplication2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Fragment_sentence extends Fragment {
    private MainActivity mainActivity;
    private ArrayList<String> str_phrase = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private final String filepath = "phrase.txt";
    private File externfile;

    public ArrayList<String> getSen_str() {
        return str_phrase;
    }
    public ArrayAdapter<String> getAdapter() {
        return adapter;
    }
    public File getExternfile() {
        return externfile;
    }


    public Fragment_sentence(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.str_phrase = this.mainActivity.getStr_phrase();
        externfile = mainActivity.getExternalFilesDir(null);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frag_sen = inflater.inflate(R.layout.sentence_fragment, container, false);

        adapter = new ArrayAdapter<>(frag_sen.getContext(), android.R.layout.simple_expandable_list_item_1, str_phrase);
        ListView listView = frag_sen.findViewById(R.id.sen_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new MyItemClickListener(this, str_phrase, 1));
        listView.setOnItemLongClickListener(new MyItemLongClickListener(this, str_phrase, mainActivity, adapter, filepath, externfile));
        LinearLayout linearLayout = frag_sen.findViewById(R.id.sen_add);
        //给linearlayout设置点击事件
        linearLayout.setOnClickListener(new DialogPhraseClickAdd(this));

        return frag_sen;
    }

    @Override
    public void onStart() {
        super.onStart();
        File file = new File(externfile, filepath);
        try {
            ArrayList<String> newStr = Dfile.readx(file);
            str_phrase.clear();
            str_phrase.addAll(newStr);
            adapter.notifyDataSetChanged();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
