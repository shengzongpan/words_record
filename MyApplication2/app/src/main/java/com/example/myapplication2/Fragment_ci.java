package com.example.myapplication2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Fragment_ci extends Fragment {
    private final MainActivity mainActivity;

    private ArrayAdapter<String> adapter;
    //计数
    private int n = 0;
    //文件路径
    private String filepath = "vocabulary.txt";
    private File externfile;
    //控件
    private Button b_add; //add 按钮
    private TextView tx2; //record 全局变量
    //用AraayList集合来存储
    private ArrayList<String> strs = new ArrayList<>();

    //get set方法
    public TextView getTx2() {
        return tx2;
    }
    public ArrayAdapter<String> getAdapter() {
        return adapter;
    }
    public int getN() {
        return n;
    }
    public Button getB_add() {
        return b_add;
    }
    public ArrayList<String> getStrs() {
        return strs;
    }
    public String getFilepath() {
        return filepath;
    }
    public File getExternfilef_w() {
        return externfile;
    }
    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public Fragment_ci(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View word_frag = inflater.inflate(R.layout.word_fragment, container, false);

        //读取文件路径并读出数据
        externfile = mainActivity.getExternalFilesDir(null);
        if (externfile == null) {
            externfile = mainActivity.getFilesDir();
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
        tx2 = (TextView) word_frag.findViewById(R.id.text2);
        tx2.setText("Record数量: " + n);


        //创建ArrayAdapter
        adapter = new ArrayAdapter<String>(this.mainActivity, android.R.layout.simple_expandable_list_item_1, strs);
        //获取ListView对象，通过调用setAdapter方法为ListView设置Adapter设置适配器
        ListView listview = (ListView) word_frag.findViewById(R.id.list_item);
        listview.setAdapter(adapter);

        //给listview添加点击事件
        listview.setOnItemClickListener(new MyItemClickListener(this, strs));

        //给增加按钮添加事件
        b_add = word_frag.findViewById(R.id.b_add);
        b_add.setOnClickListener(new Dialog1ClickAdd(this));
        return word_frag;
    }
}
