package com.example.myapplication2;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class Detail_interface extends AppCompatActivity {
    private Words word;
    private Sentence sentence;
    private int position;
//    private static String TAG = Detail_interface.class.getSimpleName();
    //File 这边可以只读一次不用每次都读
    private ArrayList<String> str = new ArrayList<>();
    private final String filepath_word = "vocabulary.txt";//word
    private final String filepath_phrase = "phrase.txt";//phrase
    private File externFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_detail);
        EdgeToEdge.enable(this);

        //读取从fragment传递过来的值strs, position

        String type_id = getIntent().getStringExtra("id");
        str = getIntent().getStringArrayListExtra("strs");
        String tem_position = getIntent().getStringExtra("position");
        position = Integer.parseInt(tem_position);
        //Log.d(TAG, str_w.toString() + position);

        TextView text_mean = findViewById(R.id.detail_mean);
        TextView textView = findViewById(R.id.detail_word);
        //返回图片按钮设置返回
        back_button();
        Button bt_change = findViewById(R.id.b_change);
        //公用，不同的划区别
        if(type_id.equals("word")) {
            word = Str_deal.convert_w(str.get(position));
            textView.setText(word.getEn_word());
            text_mean.setText(word.getCixing() + ". " + word.getCn_mean());

            bt_change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogWordChange dialogWordChange = new DialogWordChange(Detail_interface.this, new DialogWordChange.Dialog_changeListener() {
                        @Override
                        public void return_to_activity(Words a) {

                            String a_string = a.getEn_word() + "  --  " + a.getCixing() + "  --  " + a.getCn_mean();
                            str.set(position, a_string);
                            textView.setText(a.getEn_word());
                            text_mean.setText(a.getCixing() + ". " + a.getCn_mean());

                            externFile = getExternalFilesDir(null);
                            if(externFile == null) {
                                externFile = getFilesDir();
                            }
                            File file = new File(externFile, filepath_word);
                            Dfile.write(str, file);
//                        Log.d(TAG, str_w.toString());
                        }
                    });
                    dialogWordChange.show_dialog_change();
                }
            });
        } else if (type_id.equals("phrase")) {
            sentence = Str_deal.convert_p(str.get(position));
            textView.setText(sentence.getPhrase());
            text_mean.setText(sentence.getMean());
            bt_change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogPhraseChange dialogPhraseChange = new DialogPhraseChange(Detail_interface.this, new DialogPhraseChange.DialogChangeListener() {
                        @Override
                        public void return_to_activity(Sentence sentence) {
                            String phrase = sentence.getPhrase() + " -- " + sentence.getMean();
                            str.set(position, phrase);
                            textView.setText(sentence.getPhrase());
                            text_mean.setText(sentence.getMean());

                            externFile = getExternalFilesDir(null);
                            if(externFile == null) {
                                externFile = getFilesDir();
                            }
                            File file = new File(externFile, filepath_phrase);
                            Dfile.write(str, file);
                        }
                    });
                    dialogPhraseChange.show_dialog_change();
                }
            });
        }


    }

    protected void back_button() {
        ImageButton btn_back = findViewById(R.id.back_to_ci);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 finish();
            }
        });
    }
}
