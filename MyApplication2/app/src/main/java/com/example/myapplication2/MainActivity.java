package com.example.myapplication2;



import android.os.Bundle;
import android.view.MenuItem;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarView;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    //navigation
    private NavigationBarView navigationBarView;
    //fragment
    private Fragment_ci fragment_ci;
    private Fragment_sentence fragmentSentence;
    private Fragment_memory fragmentMemory;
    //File 这边可以只读一次不用每次都读
    private ArrayList<String> str_w = new ArrayList<>();
    private ArrayList<String> str_p = new ArrayList<>();
    private ArrayList<Words> words = new ArrayList<>();
    private ArrayList<Sentence> sentences = new ArrayList<>();
    private final String filepath_word = "vocabulary.txt";//word
    private final String filepath_phrase = "phrase.txt";//phrase
    private File externFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //先读取所有的文件
        read_all();
        //fragment初始化(需要放在navigation之前，因为下面已经要显示了)
        this.fragment_ci = new Fragment_ci(this);
        this.fragmentSentence = new Fragment_sentence();
        this.fragmentMemory = new Fragment_memory();
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
                return true;
        }
        return true;
    }
    //先读取所有的后面不需要一个个读取
    public void read_all() {
        externFile = getExternalFilesDir(null);
        if(externFile == null) {
            externFile = getFilesDir();
        }
        File file_w = new File(externFile, filepath_word);
        File file_p = new File(externFile, filepath_phrase);

        try {
            str_w = Dfile.readx(file_w);
            words = Str_deal.convert_w(str_w);
            str_p = Dfile.readx(file_p);
            sentences = Str_deal.convert_p(str_p);
        }catch (Exception e) {
        }
    }

    //getter
    public ArrayList<String> getStr_w() {
        return str_w;
    }
    public ArrayList<String> getStr_p() {
        return str_p;
    }
    public ArrayList<Words> getWords() {
        return words;
    }
    public ArrayList<Sentence> getSentences() {
        return sentences;
    }
    public File getExternFile() {
        return externFile;
    }
}