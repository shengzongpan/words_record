package com.example.myapplication2;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DialogPhraseChange extends Dialog {

    public interface DialogChangeListener {
        public void return_to_activity(Sentence sentence);
    }
    private AppCompatActivity activity;
    private DialogChangeListener dialogChangeListener;
    public DialogPhraseChange(@NonNull Context context, DialogChangeListener dialogChangeListener) {
        super(context);
        this.activity = (AppCompatActivity) context;
        this.dialogChangeListener = dialogChangeListener;
    }

    public void show_dialog_change() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogview = inflater.inflate(R.layout.dialog_phrase_change, null);
        builder.setView(dialogview);

        Dialog dialog = builder.create();
        dialog.setCancelable(false);
        //背景设为透明
        if(dialog.getWindow() != null){
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.show();
        EditText editp = dialogview.findViewById(R.id.input_phrase);
        EditText editm = dialogview.findViewById(R.id.input_mean);

        Button sure = dialogview.findViewById(R.id.sure_b2);
        Button cancel = dialogview.findViewById(R.id.cancel2);

        //v -> {}相当于new View.OnClickListener() { 必要方法 }
        sure.setOnClickListener(v -> {
            String inputp = editp.getText().toString();
            String inputm = editm.getText().toString();
            Sentence sentence = new Sentence(inputp, inputm);
            boolean yn = true;
            dialogChangeListener.return_to_activity(sentence);
            dialog.dismiss();
        });

        cancel.setOnClickListener(v -> dialog.dismiss());
    }
}
