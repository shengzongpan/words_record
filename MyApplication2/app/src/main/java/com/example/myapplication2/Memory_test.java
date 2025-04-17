package com.example.myapplication2;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
    private int sum;
    private Button next_btn;
    private EditText editText;
    private TextView count_textview;
    private TextView textView;
    @Override
    protected void onRestart() {
        super.onRestart();
        textView.setText(words.get(n).getCn_mean());
        editText.setText("");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.memory_test);
        ImageButton back_btn = findViewById(R.id.back_to_mem);
        read();
        next_btn = findViewById(R.id.next_word);
        editText = findViewById(R.id.input_word);
        count_textview = findViewById(R.id.mo_n);
        textView = findViewById(R.id.test_tv);
        sum = words.size();
        textView.setText(words.get(n).getCn_mean());
        count_textview.setText(n + 1 + "/" + sum);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                //判断输入的单词是否正确
                if (input.equals(words.get(n).getEn_word())) {

                    //颜色从蓝逐渐变黑2秒钟
                    ObjectAnimator colorAnimator = ObjectAnimator.ofObject(
                            editText,"textColor",
                            new ArgbEvaluator(),Color.BLUE, Color.BLACK
                    );
                    colorAnimator.setDuration(1000);
                    colorAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            n++;
                            if(n == sum) {
                                finish();
                                Intent intent = new Intent(Memory_test.this, Celebration.class);
                                startActivity(intent);
                            }
                            else {

                                textView.setText(words.get(n).getCn_mean());
                                editText.setText("");
                                count_textview.setText(n + 1 + "/" + sum);
                                Toast.makeText(Memory_test.this,"正确!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    colorAnimator.start();
                }
                else {
                    ObjectAnimator colorAnimator = ObjectAnimator.ofObject(
                            editText, "textColor",new ArgbEvaluator(),
                            Color.RED, Color.BLACK
                    );
                    colorAnimator.setDuration(1000);
                    colorAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            Intent intent = new Intent(Memory_test.this, Error_Detail.class);
                            intent.putExtra("word", words.get(n).getEn_word());
                            intent.putExtra("mean", words.get(n).getCixing() + ". " + words.get(n).getCn_mean());
                            startActivity(intent);
                            Words word = words.get(n);
                            words.remove(n);
                            words.add(word);

                        }
                    });
                    colorAnimator.start();
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
            words = Str_deal.convert_w(str);
        }catch (Exception e) {

        }
    }

}
