package com.example.myapplication2;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class Dialog1ClickAdd implements View.OnClickListener {
    private MainActivity mainActivity;
    private ArrayList<String> strs;
    private File externfile;
    private String filepath;
    private int n;
    private ArrayAdapter<String> adapter;
    private TextView tx2;

    public Dialog1ClickAdd(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
    @Override
    public void onClick(View v) {
        strs = mainActivity.getStrs();
        externfile = mainActivity.getExternfile();
        filepath = mainActivity.getFilepath();
        n = mainActivity.getN();
        adapter = mainActivity.getAdapter();
        tx2 = mainActivity.getTx2();
        showdialog1();
    }

    protected void showdialog1() {

        AlertDialog.Builder dialog1 = new AlertDialog.Builder(mainActivity);
        //设置dialog标题
        //dialog1.setTitle("添加"); 不设置标题了太丑了
        //加载xml布局
        LayoutInflater inflater = mainActivity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog1, null);
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
        //设置按钮
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
                n++;

                //通知适配器数据已经更改
                adapter.notifyDataSetChanged();
                //关闭对话框
                dialog.dismiss();
                //更新record数量
                tx2.setText("Record数量: " + n);
                //获取路径
                File file = new File(externfile, filepath);
                //存入文件
                File_out file_out = new File_out(filepath, n);
                file_out.write(strs, file);
            }
        });
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
        b_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();// dismiss 方法需要先show在关闭，不然打不开
            }
        });
    }
}
