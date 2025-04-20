package com.example.myapplication2;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AlertDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DialogPhraseClickAdd implements View.OnClickListener {

    public interface DialogPhraseAddListener {
        public void update();
    }

    private DialogPhraseAddListener listener;
    private final Fragment_sentence fragmentSentence;
    private List<Sentence> phrases;
    private PhraseAdapter adapter;
    private File externFile;
    private final String filepath = "phrase.txt";

    public DialogPhraseClickAdd(Fragment_sentence fragmentSentence, DialogPhraseAddListener listener) {
        this.fragmentSentence = fragmentSentence;
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        this.phrases = fragmentSentence.getPhrase();
        externFile = fragmentSentence.getExternfile();
        this.adapter = fragmentSentence.getAdapter();
        show_add_dialog();
    }

    public void show_add_dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(fragmentSentence.requireContext());

        LayoutInflater layoutInflater = fragmentSentence.getLayoutInflater();
        View dialogview = layoutInflater.inflate(R.layout.sentence_add_dialog, null);

        builder.setView(dialogview);
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);//点击其他地方关闭
        dialog.show();
        //设置透明背景色
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        EditText input_p = dialogview.findViewById(R.id.inputs1);
        EditText input_m = dialogview.findViewById(R.id.inputs2);

        Button b_sure = dialogview.findViewById(R.id.sure_bs);
        Button b_cancel = dialogview.findViewById(R.id.cancels);

        b_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String en = input_p.getText().toString();
                String s_mean = input_m.getText().toString();
                Sentence phrase = new Sentence(en, s_mean);
                phrases.add(phrase);
                ArrayList<String> str = new ArrayList<>();
                for (int i = 0; i < phrases.size(); i++) {
                    str.add(phrases.get(i).getPhrase() + " -- " + phrases.get(i).getMean());
                }
                str = Str_deal.sort(str);
                phrases = Str_deal.convert_p(str);
                //关闭窗口
                dialog.dismiss();
                File file = new File(externFile, filepath);
                Dfile.write(str, file);

                listener.update();
            }
        });

        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
