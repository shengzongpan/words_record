package com.example.myapplication2;


import static android.widget.AdapterView.*;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    //navigation
    private NavigationBarView navigationBarView;
    //fragment
    private Fragment_ci fragment_ci;
    private Fragment_sentence fragmentSentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //fragment初始化(需要放在navigation之前，因为下面已经要显示了)
        this.fragment_ci = new Fragment_ci(this);
        this.fragmentSentence = new Fragment_sentence();

        //navigation
        navigationBarView = findViewById(R.id.nav_view);
        navigationBarView.setOnItemSelectedListener(this::onNavigationItemSelected);
        navigationBarView.setSelectedItemId(R.id.nav_ci);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction  fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //这里没有new因为上面已经new过了，直接使用上面的实例，不然会浪费内存需要释放掉，且返回不能回到用户所滑位置
        switch (item.getItemId()) {
            case R.id.nav_ci:
                fragmentTransaction.replace(R.id.frame_home, this.fragment_ci).commit();
                return true;
            case R.id.nav_sentence:
                fragmentTransaction.replace(R.id.frame_home, this.fragmentSentence).commit();
                return true;
        }
        return true;
    }
}