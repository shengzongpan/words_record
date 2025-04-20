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
import java.util.List;

public class Fragment_sentence extends Fragment {
    private MainActivity mainActivity;
    private ArrayList<String> str_phrase;
    private List<Sentence> phrase;
    private PhraseAdapter adapter;
    private final String filepath = "phrase.txt";
    private File externfile;
    private File file;

    public ArrayList<String> getStr_phrase() {
        return str_phrase;
    }
    public List<Sentence> getPhrase() {
        return phrase;
    }
    public PhraseAdapter getAdapter() {
        return adapter;
    }
    public File getExternfile() {
        return externfile;
    }


    public Fragment_sentence(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.str_phrase = this.mainActivity.getStr_phrase();
        externfile = mainActivity.getExternalFilesDir(null);
        phrase = Str_deal.convert_p(str_phrase);
        file = new File(externfile, filepath);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frag_sen = inflater.inflate(R.layout.sentence_fragment, container, false);

        adapter = new PhraseAdapter(requireContext(), R.layout.list_item, phrase);
        ListView listView = frag_sen.findViewById(R.id.sen_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new MyPhraseClickListener(this));
        listView.setOnItemLongClickListener(new MyPhraseLongClickListener(this, phrase, mainActivity, adapter, filepath, externfile));
        LinearLayout linearLayout = frag_sen.findViewById(R.id.sen_add);
        //给linearlayout设置点击事件
        linearLayout.setOnClickListener(new DialogPhraseClickAdd(this, new DialogPhraseClickAdd.DialogPhraseAddListener() {
            @Override
            public void update() {
                try {
                    ArrayList<String> newStr = Dfile.readx(file);
                    str_phrase = Str_deal.sort(newStr);
                    phrase.clear();
                    phrase.addAll(Str_deal.convert_p(str_phrase));
                    adapter.notifyDataSetChanged();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }));

        return frag_sen;
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            ArrayList<String> newStr = Dfile.readx(file);
            str_phrase = Str_deal.sort(newStr);
            phrase.clear();
            phrase.addAll(Str_deal.convert_p(str_phrase));
            adapter.notifyDataSetChanged();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
