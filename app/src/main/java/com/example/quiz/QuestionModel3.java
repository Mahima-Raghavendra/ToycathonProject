package com.example.quiz;

public class QuestionModel3 {

    private String english_meaning, hindi_meaning;

    public QuestionModel3(String english_meaning, String hindi_meaning) {
        this.english_meaning = english_meaning;
        this.hindi_meaning = hindi_meaning;
    }

    public String getEnglish_meaning() {
        return english_meaning;
    }

    public void setEnglish_meaning(String english_meaning) {
        this.english_meaning = english_meaning;
    }

    public String getHindi_meaning() {
        return hindi_meaning;
    }

    public void setHindi_meaning(String hindi_meaning) {
        this.hindi_meaning = hindi_meaning;
    }

}
