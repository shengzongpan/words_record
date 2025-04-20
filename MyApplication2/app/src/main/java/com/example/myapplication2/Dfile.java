package com.example.myapplication2;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Dfile {
    @NonNull
    static public ArrayList<String> readx(File file) throws IOException {
        ArrayList<String> strs = new ArrayList<>();
        //new AlertDialog.Builder(new MainActivity()).setTitle("1").setMessage("找不到文件").setPositiveButton("ok",null).show();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                strs.add(line);
            }
        }catch (Exception e){

        }
        return strs;
    }

    static public void write(ArrayList<String> strs, File file){
        try(FileWriter writer = new FileWriter(file)){
            for (int i = 0; i < strs.size(); i++){
                writer.write(strs.get(i) + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
