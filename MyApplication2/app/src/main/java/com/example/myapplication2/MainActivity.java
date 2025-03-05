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
import android.view.View;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    //计数
    private int n = 0;
    //用AraayList集合来存储
    private ArrayList<String> strs = new ArrayList<>();
    //文件路径
    private String filepath = "vocabulary.txt";
    //控件
    private TextView tx2; //record 全局变量
    private Button b_add; //add 按钮
    //Words words;
    private ArrayAdapter<String> adapter;
    private File externfile;

    public TextView getTx2(){
        return tx2;
    }
    public ArrayList<String> getStrs(){
        return strs;
    }
    public ArrayAdapter<String> getAdapter(){
        return adapter;
    }
    public String getFilepath(){
        return filepath;
    }
    public File getExternfile(){
        return externfile;
    }
    public int getN(){
        return n;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //读取
        externfile = getExternalFilesDir(null);
        if (externfile == null) {
            externfile = getFilesDir();
        }
        File file = new File(externfile, filepath);
        File_in file_in = new File_in(filepath);
        try {
            strs = file_in.readx(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        n = file_in.getN();

        //更新record数量
        tx2 = (TextView) findViewById(R.id.text2);
        tx2.setText("Record数量: " + n);


        //创建ArrayAdapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strs);
        //获取ListView对象，通过调用setAdapter方法为ListView设置Adapter设置适配器
        ListView listview = (ListView) findViewById(R.id.list_item);
        listview.setAdapter(adapter);

        //给listview添加点击事件
        listview.setOnItemClickListener(new MyItemClickListener(this, strs));

        //给增加按钮添加事件
        b_add = findViewById(R.id.b_add);
        b_add.setOnClickListener(new Dialog1ClickAdd(this));
    }
}