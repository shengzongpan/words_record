package com.example.myapplication2;

public class Sentence {
    private String phrase;
    private String mean;

    public Sentence(String phrase, String mean) {
        this.phrase = phrase;
        this.mean = mean;
    }

    public String getPhrase() {
        return phrase;
    }

    public String getMean() {
        return mean;
    }
}
