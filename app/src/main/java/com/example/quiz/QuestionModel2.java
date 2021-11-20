package com.example.quiz;

class QuestionModel2 {
    private String sentence1, sentence2, sentence3, sentence4, sentence5, sentence6, sentence7;

    public QuestionModel2(String sentence1, String sentence2, String sentence3, String sentence4, String sentence5, String sentence6, String sentence7) {
        this.sentence1 = sentence1;
        this.sentence2 = sentence2;
        this.sentence3 = sentence3;
        this.sentence4 = sentence4;
        this.sentence5 = sentence5;
        this.sentence6 = sentence6;
        this.sentence7 = sentence7;
    }

    public String getSentence(int position){

        if(position==0){
            return sentence1;
        }
        else if(position==1){
            return sentence2;
        }
        else if(position==2){
            return sentence3;
        }
        else if(position==3){
            return sentence4;
        }
        else if(position==4){
            return sentence5;
        }
        else if(position==5){
            return sentence6;
        }
        else{
            return sentence7;
        }
    }
}