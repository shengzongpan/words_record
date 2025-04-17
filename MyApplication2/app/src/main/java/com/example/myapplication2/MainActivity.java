package com.example.myapplication2;



import android.os.Bundle;
import android.view.MenuItem;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    //navigation
    private NavigationBarView navigationBarView;
    //fragment
    private Fragment_ci fragment_ci;
    private Fragment_sentence fragmentSentence;
    private Fragment_memory fragmentMemory;
    private AboutWe aboutWe;
    //file
    private File externfile;
    private final String filepath = "vocabulary.txt";
    private ArrayList<String> str_word = new ArrayList<>();
    private final String filepath_phrase = "phrase.txt";
    private ArrayList<String> str_phrase = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        read_all();
        //fragment初始化(需要放在navigation之前，因为下面已经要显示了)
        this.fragment_ci = new Fragment_ci(this);
        this.fragmentSentence = new Fragment_sentence(this);
        this.fragmentMemory = new Fragment_memory();
        this.aboutWe = new AboutWe();
        //navigation
        navigationBarView = findViewById(R.id.nav_view);
        navigationBarView.setOnItemSelectedListener(this::onNavigationItemSelected);
        navigationBarView.setSelectedItemId(R.id.nav_ci);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //这里没有new因为上面已经new过了，直接使用上面的实例，不然会浪费内存需要释放掉，且返回不能回到用户所滑位置
        switch (item.getItemId()) {
            case R.id.nav_ci:
                fragmentTransaction.replace(R.id.frame_home, this.fragment_ci).commit();
                return true;
            case R.id.nav_sentence:
                fragmentTransaction.replace(R.id.frame_home, this.fragmentSentence).commit();
                return true;
            case R.id.nav_mem:
                fragmentTransaction.replace(R.id.frame_home, this.fragmentMemory).commit();
                return true;
            case R.id.about_we:
                fragmentTransaction.replace(R.id.frame_home, this.aboutWe).commit();
                return true;
        }
        return true;
    }

    public void read_all() {
        externfile = getExternalFilesDir(null);
        if(externfile == null) {
            externfile = getFilesDir();
        }
        File file = new File(externfile, filepath);
        File file1 = new File(externfile, filepath_phrase);
        try {
            str_word = Dfile.readx(file);
            str_phrase = Dfile.readx(file1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Str_deal.sui_ji(str_word);
        Dfile.write(str_word, file);
        Str_deal.sort(str_phrase);
    }

    public ArrayList<String> getStr_word() {
        return str_word;
    }

    public ArrayList<String> getStr_phrase() {
        return str_phrase;
    }
}