package com.example.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PhraseAdapter extends ArrayAdapter<Sentence> {


    public PhraseAdapter(@NonNull Context context, int resource, @NonNull List<Sentence> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Sentence phrase  = getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        TextView item_word = view.findViewById(R.id.list_item_word);
        TextView item_mean = view.findViewById(R.id.list_item_mean);

        item_word.setText(phrase.getPhrase());
        item_mean.setText(phrase.getMean());
        return view;
    }
}
