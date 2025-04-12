package com.example.myapplication2;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.io.File;
import java.util.ArrayList;
//添加按钮显示及实现
public class DialogWordClickAdd implements View.OnClickListener {
    private final Fragment_ci fragment_ci;
    private ArrayList<String> strs;
    private File externfile;
    private String filepath;
    private ArrayAdapter<String> adapter;
    private TextView tx2;

    public DialogWordClickAdd(Fragment_ci fragment_ci){
        this.fragment_ci = fragment_ci;
    }
    @Override
    public void onClick(View v) {
        strs = fragment_ci.getStrs();
        externfile = fragment_ci.getExternfilef_w();
        filepath = fragment_ci.getFilepath();
        adapter = fragment_ci.getAdapter();
        tx2 = fragment_ci.getTx2();
        show_dialog1();
    }

    protected void show_dialog1() {

        AlertDialog.Builder dialog1 = new AlertDialog.Builder(fragment_ci.requireContext());
        //设置dialog标题
        //dialog1.setTitle("添加"); 不设置标题了太丑了
        //加载xml布局
        LayoutInflater inflater = fragment_ci.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add, null);
        dialog1.setView(dialogView);
        //获取布局中的输入框
        //final 定义变量，表示变量值不可改变
        final EditText input1 = dialogView.findViewById(R.id.input1);
        final EditText input2 = dialogView.findViewById(R.id.input2);
        final EditText input3 = dialogView.findViewById(R.id.input3);
        //获取布局中的按钮
        Button b_sure = dialogView.findViewById(R.id.sure_b);
        Button b_can = dialogView.findViewById(R.id.cancel);
        //创建对话框
        AlertDialog dialog = dialog1.create();
        dialog.setCancelable(false);//点击其他地方关闭
        //显示对话框
        dialog.show();
        //将背景色设置透明
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        //设置按钮事件
        b_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框中的文字
                String text1 = input1.getText().toString();
                String text2 = input2.getText().toString();
                String text3 = input3.getText().toString();
                //传入words对象
                Words words = new Words(text1, text3, text2);
                //增加集合
                strs.add(words.getEn_word() + "  --  " + words.getCixing() + "  --  " + words.getCn_mean());
                //更新record数量
                tx2.setText("Record数量: " + strs.size());
                //通知适配器数据已经更改
                adapter.notifyDataSetChanged();
                //关闭对话框
                dialog.dismiss();

                //获取路径
                File file = new File(externfile, filepath);
                //存入文件
                Dfile.write(strs, file);
            }
        });
        b_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();// dismiss 方法需要先show在关闭，不然打不开
            }
        });
    }
}
    //dialog中的button太丑了
    /**dialog1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
    @Override public void onClick(DialogInterface dialog, int which) {
    //获取输入框中的文字
    String text1 = input1.getText().toString();
    String text2 = input2.getText().toString();
    String text3 = input3.getText().toString();
    //传入words对象
    words = new Words(text1, text3, text2);
    //增加集合
    strs.add(words.getEn_word() + "  --  " + words.getCixing() + "  --  " + words.getCn_mean());
    n++;

    //通知适配器数据已经更改
    adapter.notifyDataSetChanged();

    //更新record数量
    tx2.setText("Record数量: " + n);

    File externfile = getExternalFilesDir(null);
    if(externfile == null){
    externfile =getFilesDir();
    }
    File file = new File(externfile, filepath);
    //存入文件
    File_out file_out = new File_out(filepath, n);
    file_out.write(strs, file);
    }
    });
     dialog1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
    @Override public void onClick(DialogInterface dialog, int which) {

    }
    });**/