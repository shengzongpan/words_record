package com.example.myapplication2;

import androidx.lifecycle.viewmodel.CreationExtras;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

//处理字符串，如随机，排序，搜索
public class Str_deal {

    //随机
    static public ArrayList<String> sui_ji(ArrayList<String> str) {
        Collections.shuffle(str);
        return str;
    }
    //排序sort
    static public ArrayList<String> sort(ArrayList<String> str) {
        Collections.sort(str);
        return str;
    }
    //随机选取10个word
    static public ArrayList<String> choose_10(ArrayList<String> str) {
        if(str.size() < 10) {
            return str;
        }
        ArrayList<String> mem_word = new ArrayList<>();
        Random random = new Random();
        while(mem_word.size() < 10) {
            int index = random.nextInt(str.size());
            mem_word.add(str.get(index));
            str.remove(index);
        }
        return mem_word;
    }
    //将string从1--1--1分开，重新变成Word类
    static public ArrayList<Words> convert_w(ArrayList<String> str) {
        ArrayList<Words> words = new ArrayList<>();
        String en_word; String cn_mean; String cixing;
        for(int i = 0; i < str.size(); i++) {
            String s = str.get(i);
            String [] a =  s.split("--");
            en_word = a[0].trim();
            cixing = a[1].trim();
            cn_mean = a[2].trim();
            Words w = new Words(en_word, cn_mean, cixing);
            words.add(w);
        }
        return words;
    }
    //参数不同，返回值不同重新进行写
    static public Words convert_w(String str) {
        String en_word; String cn_mean; String cixing;
            String [] a =  str.split("--");
            en_word = a[0].trim();
            cixing = a[1].trim();
            cn_mean = a[2].trim();
            Words w = new Words(en_word, cn_mean, cixing);

        return w;
    }
    //将phrase从1--1分开，重新变成Sentence类
    static public ArrayList<Sentence> convert_p(ArrayList<String> str) {
        ArrayList<Sentence> sentences = new ArrayList<>();
        String phrase;  String mean;
        for(int i = 0; i < str.size(); i++) {
            String s = str.get(i);
            String [] a = s.split("--");
            phrase = a[0].trim();
            mean = a[1].trim();
            Sentence sentence = new Sentence(phrase, mean);
            sentences.add(sentence);
        }
        return sentences;
    }

    static public Sentence convert_p(String str) {
        String phrase;  String mean;
            String [] a = str.split("--");
            phrase = a[0].trim();
            mean = a[1].trim();
            Sentence sentence = new Sentence(phrase, mean);
        return sentence;
    }
}
