package com.example.myapplication2;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Detail_interface extends AppCompatActivity {
    private Words word;
//    private static String TAG = Detail_interface.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_detail);
        EdgeToEdge.enable(this);
        String str = getIntent().getStringExtra("str");
        word = Str_deal.convert_w(str);
//        Log.d(TAG, word.getEn_word() + word.getCn_mean());
        back_button();
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
