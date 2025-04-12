package com.example.myapplication2;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;

public class Memory_test extends AppCompatActivity {
    private ArrayList<Words> words;
    private final String filepath = "vocabulary.txt";
    private File externfile;
    //记录第几个词
    private int n = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.memory_test);
        ImageButton back_btn = findViewById(R.id.back_to_mem);
        read();
        TextView textView = findViewById(R.id.test_tv);
        textView.setText(words.get(n).getCn_mean());
        Button next_btn = findViewById(R.id.next_word);
        EditText editText = findViewById(R.id.input_word);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                if(input.equals(words.get(n).getEn_word())) {
                    //颜色从蓝逐渐变黑2秒钟
                    ObjectAnimator colorAnimator = ObjectAnimator.ofObject(
                            editText,"textColor",
                            new ArgbEvaluator(),Color.BLUE, Color.BLACK
                    );
                    colorAnimator.setDuration(2000);
                    colorAnimator.start();
                    textView.setText(words.get(n).getCn_mean());
                }

            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void read() {
        ArrayList<String> str;
        externfile = getExternalFilesDir(null);
        if(externfile == null) {
            externfile = getFilesDir();
        }
        File file = new File(externfile, filepath);
        try {
            str = Dfile.readx(file);
            str = Str_deal.choose_10(str);
            words = Str_deal.convert(str);
        }catch (Exception e) {

        }
    }

}
