package com.example.myapplication2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class Fragment_ci extends Fragment{

//    private static String TAG = Fragment_ci.class.getSimpleName();
    private final MainActivity mainActivity;

    //listview 适配器
    private ArrayAdapter<String> adapter;
    //文件路径
    private final String filepath = "vocabulary.txt";
    private File externfile;
    private File file;
    //控件
    private Button b_add; //add 按钮
    private TextView tx_rd; //record 全局变量
    //用ArrayList集合来存储
    private ArrayList<String> strs;
    private ArrayList<Words> words;

    //get set方法
    public TextView getTx2() {
        return tx_rd;
    }
    public ArrayAdapter<String> getAdapter() {
        return adapter;
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
        externfile = mainActivity.getExternalFilesDir(null);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View word_frag = inflater.inflate(R.layout.word_fragment, container, false);

        //Log.d(TAG, "OnCreateView");//只执行一次OnCreateView
        strs = mainActivity.getStr_word();
        //更新record数量
        tx_rd = word_frag.findViewById(R.id.text2);
        tx_rd.setText("Record数量: " + strs.size());


        //创建ArrayAdapter
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_expandable_list_item_1, strs);
        //获取ListView对象，通过调用setAdapter方法为ListView设置Adapter设置适配器
        ListView listview = word_frag.findViewById(R.id.list_item);
        listview.setAdapter(adapter);

        //给listview添加点击事件
        listview.setOnItemClickListener(new MyItemClickListener(this, strs, 0));
        listview.setOnItemLongClickListener(new MyItemLongClickListener(this, strs, mainActivity, adapter, filepath, externfile));
        //给增加按钮添加事件
        b_add = word_frag.findViewById(R.id.b_add);
        b_add.setOnClickListener(new DialogWordClickAdd(this));

        return word_frag;
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            file = new File(externfile, filepath);
            ArrayList<String> newStr = Dfile.readx(file);
            strs.clear();
            strs.addAll(newStr);
            adapter.notifyDataSetChanged();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        Log.d(TAG, "onStart: " + strs);//每次打开都会调用
    }
}
