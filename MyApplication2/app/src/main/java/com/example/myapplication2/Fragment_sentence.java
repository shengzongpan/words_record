package com.example.myapplication2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Fragment_sentence extends Fragment {
    private ArrayList<String> sen_str = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    public ArrayList<String> getSen_str() {
        return sen_str;
    }
    public ArrayAdapter<String> getAdapter() {
        return adapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frag_sen = inflater.inflate(R.layout.sentence_fragment, container, false);

        adapter = new ArrayAdapter<String>(frag_sen.getContext(), android.R.layout.simple_expandable_list_item_1, sen_str);
        ListView listView = (ListView) frag_sen.findViewById(R.id.sen_list);
        listView.setAdapter(adapter);

        LinearLayout linearLayout = frag_sen.findViewById(R.id.sen_add);
        //给linearlayout设置点击事件
        linearLayout.setOnClickListener(new DialogSenClickAdd(this));

        return frag_sen;
    }
}
