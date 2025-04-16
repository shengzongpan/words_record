package com.example.myapplication2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Error_Detail extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_detail);
        EdgeToEdge.enable(this);

        String word = getIntent().getStringExtra("word");
        String mean = getIntent().getStringExtra("mean");

        TextView textView = findViewById(R.id.detail_word);
        TextView textView1 = findViewById(R.id.detail_mean);

        textView.setText(word);
        textView1.setText(mean);
        Button button = findViewById(R.id.b_change);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
