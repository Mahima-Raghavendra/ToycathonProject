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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity1 extends AppCompatActivity {

    private TextView question, score;
    private FloatingActionButton bookmark, homeBtn;
    private Button nextBtn,option1, option2, option3, option4;
    private LinearLayout optionsContainer;
    private List<QuestionModel> questionList;
    private List<String> bundles_list;
    Dialog dialog;
    int here_level;
    String result;
    Bundle bundle;
    int position;
    String selectedOption;

    public QuestionActivity1() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dialog = new Dialog(this);

        bundles_list = new ArrayList<>();

        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        bookmark = findViewById(R.id.bookmark_btn);
        nextBtn = findViewById(R.id.next_btn);
        optionsContainer = findViewById(R.id.options_container);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        bundle = getIntent().getBundleExtra("level_bundle");
        for(String key: bundle.keySet()){
            bundles_list.add((String) bundle.get(key));
        }
        here_level = Integer.parseInt(bundles_list.get(1));

        /*for(String i:bundle.keySet()){
            result = (String) bundle.get(i);
        }*/

        /*result = bundle.getString("level");

        here_level = Integer.parseInt(result);*/

        getQuestionsList();

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = questionList.get(here_level).getOption1();
                checkAnswer(selectedOption, v);
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = questionList.get(here_level).getOption2();
                checkAnswer(selectedOption, v);
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = questionList.get(here_level).getOption3();
                checkAnswer(selectedOption, v);
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = questionList.get(here_level).getOption4();
                checkAnswer(selectedOption, v);
            }
        });
    }

    private void getQuestionsList(){
        questionList = new ArrayList<>();
        questionList.add(new QuestionModel("Before meal", "Before Studying", "Before sleeping", "Before bathing", "When is Saraswathi Shloka chanted?", "Before Studying"));
        questionList.add(new QuestionModel("Peacock","Lion", "Mouse", "Owl", "Which animal is the vehicle of Lord Ganesha","Mouse"));
        questionList.add(new QuestionModel("Shalishuka", "Nahapana", "Rajadhiraj", "Dasaratha", "Who was Lord Rama's father?", "Dasaratha"));
        questionList.add(new QuestionModel("Ramayana", "Yogiji", "Yogananda", "Sanskrit", "Hanuman is an important character in?", "Ramayana"));
        questionList.add(new QuestionModel("Creation","Happiness", "Preservation", "Destruction", "In Hindu mythology, Lord Shiva is known as the lord of","Destruction"));
        questionList.add(new QuestionModel("Defender", "Warrior", "Dark", "Light", "What does Krishna's name mean?", "Dark"));
        setQuestion();
    }

    private void setQuestion(){

        question.setText(questionList.get(here_level).getQuestion());
        option1.setText(questionList.get(here_level).getOption1());
        option2.setText(questionList.get(here_level).getOption2());
        option3.setText(questionList.get(here_level).getOption3());
        option4.setText(questionList.get(here_level).getOption4());
    }

    private void checkAnswer(String selectedOption, View view)
    {
        if(selectedOption.equals(questionList.get(here_level).getCorrectAnswer())){
            //right answer

            popup();


            //((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            //Intent new_intent = new  Intent(QuestionActivity.this,QuestionActivity2.class);
        }
        else{
            //wrong answer
            view.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

            if(questionList.get(here_level).getCorrectAnswer().equals(questionList.get(here_level).getOption1())){
                option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            }
            else if(questionList.get(here_level).getCorrectAnswer().equals(questionList.get(here_level).getOption2())){
                option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            }
            else if(questionList.get(here_level).getCorrectAnswer().equals(questionList.get(here_level).getOption3())){
                option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            }
            else if(questionList.get(here_level).getCorrectAnswer().equals(questionList.get(here_level).getOption4())){
                option4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            }

        }

        /*(option1).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
        (option2).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
        (option3).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
        (option4).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));*/

        /*nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_intent = new Intent(QuestionActivity.this,QuestionActivity2.class);
                new_intent.putExtra("level_bundle",bundle);
                startActivity(new_intent);
            }
        });*/
    }

    public void popup(){
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
}
