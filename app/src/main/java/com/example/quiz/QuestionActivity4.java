package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity4 extends AppCompatActivity implements View.OnClickListener {

    Bundle bundle;
    ImageView image1, image2;
    int here_level,score;
    private List<QuestionModel4> questionList;
    //ArrayList<ArrayList<String>> score_list = new ArrayList<>(6);
    Button option1,option2,option3,option4,nextBtn;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextBtn = findViewById(R.id.next_btn);

        bundle = getIntent().getExtras();
        here_level = (int) bundle.get("level");
        score = (int) bundle.get("scores");

        getQuestionsList(here_level);
        dialog = new Dialog(this);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);



    }

    private void getQuestionsList(int here_level) {

        questionList = new ArrayList<>();
        questionList.add(new QuestionModel4("Saraswati Namastubhyam Varade Kaamaroopini", "AnnaPoorne Sada Poorne Shankara Prana Vallabe","Jata Taveega Lajala Pravaha paavi tastale","Hanuman Chalisa","Saraswati Namastubhyam Varade Kaamaroopini",R.drawable.level1_1,R.drawable.level1_2));
        questionList.add(new QuestionModel4("Ramaya Ramabhadraya Ramachandraya Vedase","Gajananam Bhoota Ganadi Sevitam","Karagre Vasate Lakshmi","Poojyaya Raghavendraya","Gajananam Bhoota Ganadi Sevitam",R.drawable.level2_1,R.drawable.level2_2));
        questionList.add(new QuestionModel4("Jata Taveega Lajala Pravaha Paavi tastale","Aigiri Nandini Nanditha Medini","Hanuman Chalisa","Ramaya Ramabhadraya Ramachandraya Vedase","Ramaya Ramabhadraya Ramachandraya Vedase",R.drawable.level3_1,R.drawable.level3_2));
        questionList.add(new QuestionModel4("Krishna","Hanuman","Shiva","Ganesha","Hanuman",R.drawable.level4_1,R.drawable.level4_2));
        questionList.add(new QuestionModel4("Saraswathi","Rama","Ganesha","Shiva","Shiva",R.drawable.level5_1,R.drawable.level5_2));
        questionList.add(new QuestionModel4("Krishna","Rama","Raghavendra","Ganesha","Krishna",R.drawable.level6_1,R.drawable.level6_2));

        setQuestion(here_level);
    }

    private void setQuestion(int here_level) {

        option1.setText(questionList.get(here_level).getOption1());
        option2.setText(questionList.get(here_level).getOption2());
        option3.setText(questionList.get(here_level).getOption3());
        option4.setText(questionList.get(here_level).getOption4());
        image1.setImageResource(questionList.get(here_level).getImage1());
        image2.setImageResource(questionList.get(here_level).getImage2());


    }

    @Override
    public void onClick(View v) {
        String selectedOption = "None";
        switch (v.getId())
        {
            case R.id.option1:
                selectedOption = questionList.get(here_level).getOption1();
                break;
            case R.id.option2:
                selectedOption = questionList.get(here_level).getOption2();
                break;
            case R.id.option3:
                selectedOption = questionList.get(here_level).getOption3();
                break;
            case R.id.option4:
                selectedOption = questionList.get(here_level).getOption4();
                break;
        }

        checkAnswer(selectedOption, v);
    }

    private void checkAnswer(String selectedOption, View view)
    {
        if(selectedOption.equals(questionList.get(here_level).getCorrectAns())){
            //right answer

            popup();
            view.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            //view.setBackgroundColor(Color.GREEN);

            option1.setEnabled(false);
            option2.setEnabled(false);
            option3.setEnabled(false);
            option4.setEnabled(false);

            score++;
            //score_list.get(here_level).set(0, String.valueOf(score));
        }
        else{
            //wrong answer
            view.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            //view.setBackgroundColor(Color.RED);

            if(questionList.get(here_level).getCorrectAns().equals(questionList.get(here_level).getOption1())){
                option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                //option1.setBackgroundColor(Color.GREEN);
            }
            else if(questionList.get(here_level).getCorrectAns().equals(questionList.get(here_level).getOption2())){
                option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                //option2.setBackgroundColor(Color.GREEN);
            }
            else if(questionList.get(here_level).getCorrectAns().equals(questionList.get(here_level).getOption3())){
                option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                //option3.setBackgroundColor(Color.GREEN);
            }
            else if(questionList.get(here_level).getCorrectAns().equals(questionList.get(here_level).getOption4())){
                option4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                //option4.setBackgroundColor(Color.GREEN);
            }

            option1.setEnabled(false);
            option2.setEnabled(false);
            option3.setEnabled(false);
            option4.setEnabled(false);

        }
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_intent = new Intent(QuestionActivity4.this, ScoreActivity.class);
                new_intent.putExtras(bundle);
                new_intent.putExtra("scores",score);
                startActivity(new_intent);
            }
        });

    }
    private void popup(){
        Button close;
        dialog.setContentView(R.layout.popup);
        close = dialog.findViewById(R.id.close_btn);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent new_intent = new Intent(QuestionActivity4.this, MainActivity.class);
        startActivity(new_intent);
        //finish();
        return;
    }
}
