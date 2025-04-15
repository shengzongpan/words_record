package com.example.myapplication2;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

public class DialogWordChange extends Dialog{

    public interface Dialog_changeListener {
        public void return_to_activity(Words a);
    }

    private Dialog_changeListener listener;
    private final Detail_interface activity;
    public DialogWordChange(Context context, Dialog_changeListener listener){
        super(context);
        this.activity= (Detail_interface) context;
        this.listener = listener;
    }


    public void show_dialog_change() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogview2 = inflater.inflate(R.layout.dialog_word_change, null);
        builder1.setView(dialogview2);

        Dialog dialog2 = builder1.create();
        dialog2.setCancelable(false);
        //背景设为透明
        if(dialog2.getWindow() != null){
            dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog2.show();
        EditText editw = dialogview2.findViewById(R.id.input4);
        EditText editx = dialogview2.findViewById(R.id.input5);
        EditText editm = dialogview2.findViewById(R.id.input6);

        Button sure = dialogview2.findViewById(R.id.sure_b2);
        Button cancel = dialogview2.findViewById(R.id.cancel2);

        //v -> {}相当于new View.OnClickListener() { 必要方法 }
        sure.setOnClickListener(v -> {
            String inputw = editw.getText().toString();
            String inputx = editx.getText().toString();
            String inputm = editm.getText().toString();
            Words words = new Words(inputw, inputm, inputx);
            boolean yn = true;
            listener.return_to_activity(words);
            dialog2.dismiss();
//            strs.set(position, a);
//            ArrayAdapter<String> adapter = activity.getAdapter();
//            //通知适配器数据已经更改
//            adapter.notifyDataSetChanged();
//            //获取路径
//            File externfile = fragment_ci.getExternfilef_w();
//            String filepath = fragment_ci.getFilepath();
//            File file = new File(externfile, filepath);
//            //存入文件
//            Dfile.write(strs, file);
        });

        cancel.setOnClickListener(v -> dialog2.dismiss());
    }
}
