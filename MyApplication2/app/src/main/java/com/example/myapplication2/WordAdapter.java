package com.example.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Words> {

    public WordAdapter(@NonNull Context context, int resource, @NonNull List<Words> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Words words  = getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        TextView item_word = view.findViewById(R.id.list_item_word);
        TextView item_mean = view.findViewById(R.id.list_item_mean);

        item_word.setText(words.getEn_word());
        item_mean.setText(words.getCn_mean());
        return view;
    }
}

