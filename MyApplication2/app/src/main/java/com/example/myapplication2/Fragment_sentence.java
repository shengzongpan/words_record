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
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Fragment_sentence extends Fragment {
    private ArrayList<String> sen_str = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private final String filepath = "phrase.txt";
    private File externfile;
    private File file;

    public ArrayList<String> getSen_str() {
        return sen_str;
    }
    public ArrayAdapter<String> getAdapter() {
        return adapter;
    }
    public File getFile() {
        return file;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frag_sen = inflater.inflate(R.layout.sentence_fragment, container, false);

        externfile = requireContext().getExternalFilesDir(null);
        if (externfile == null) {
            externfile = requireContext().getFilesDir();
        }
        file = new File(externfile, filepath);
        try {
            sen_str = Dfile.readx(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        adapter = new ArrayAdapter<>(frag_sen.getContext(), android.R.layout.simple_expandable_list_item_1, sen_str);
        ListView listView = frag_sen.findViewById(R.id.sen_list);
        listView.setAdapter(adapter);

        LinearLayout linearLayout = frag_sen.findViewById(R.id.sen_add);
        //给linearlayout设置点击事件
        linearLayout.setOnClickListener(new DialogSenClickAdd(this));

        return frag_sen;
    }
}
