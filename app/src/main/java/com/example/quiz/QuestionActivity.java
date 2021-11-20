package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{

    //public ArrayList<ArrayList<String>> level_score = new ArrayList<>(6);
    /*FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("Choose");*/

    private TextView question, score;
    private FloatingActionButton bookmark, homeBtn;
    private Button nextBtn,option1, option2, option3, option4;
    private LinearLayout optionsContainer;
    private List<QuestionModel> questionList;
    Dialog dialog;
    int here_level;
    Bundle bundle;
    int scores=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dialog = new Dialog(this, R.style.AnimateDialog);

        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        bookmark = findViewById(R.id.bookmark_btn);
        nextBtn = findViewById(R.id.next_btn);
        optionsContainer = findViewById(R.id.options_container);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        bundle = getIntent().getExtras();
        here_level = (int) bundle.get("level");

        getQuestionsList(here_level);

        /*for(int i=0;i<6;i++){
            level_score.add(new ArrayList());
        }*/

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

    }

    private void getQuestionsList(int here_level){
        questionList = new ArrayList<>();
        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String option1 = dataSnapshot.child("option1").getValue(String.class);
                    String option2 = dataSnapshot.child("option2").getValue(String.class);
                    String option3 = dataSnapshot.child("option3").getValue(String.class);
                    String option4 = dataSnapshot.child("option4").getValue(String.class);
                    String question = dataSnapshot.child("question").getValue(String.class);
                    String correctAnswer = dataSnapshot.child("correctAnswer").getValue(String.class);
                    questionList.add(new QuestionModel(option1, option2, option3, option4, question, correctAnswer));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(QuestionActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/
        questionList.add(new QuestionModel("Before meal", "Before Studying", "Before sleeping", "Before bathing", "When is Saraswati Shloka chanted?", "Before Studying"));
        questionList.add(new QuestionModel("Peacock","Lion", "Mouse", "Owl", "Which animal is the vehicle of Lord Ganesha?","Mouse"));
        questionList.add(new QuestionModel("Shalishuka", "Nahapana", "Rajadhiraj", "Dasaratha", "Who was Lord Rama's father?", "Dasaratha"));
        questionList.add(new QuestionModel("Hanuman Chalisa", "Shiv Tandav Stotra", "Vishnu Sahasranama", "Krishna Sahasranamam", "Which of the following Shlokas is associated with Lord Hanuman?", "Hanuman Chalisa"));
        questionList.add(new QuestionModel("Shankara","Nataraja", "Trinetra", "Bolenath", "The dancing posture of Lord Shiva is represented as?","Nataraja"));
        questionList.add(new QuestionModel("Sudama", "Vasudeva", "Balarama", "Krishna has no brother", "Who was Lord Krishna's brother?", "Balarama"));

        setQuestion(here_level);
    }

    private void setQuestion(int here_level){

        question.setText(questionList.get(here_level).getQuestion());
        option1.setText(questionList.get(here_level).getOption1());
        option2.setText(questionList.get(here_level).getOption2());
        option3.setText(questionList.get(here_level).getOption3());
        option4.setText(questionList.get(here_level).getOption4());
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
        if(selectedOption.equals(questionList.get(here_level).getCorrectAnswer())){
            //right answer

            popup();
            view.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            //view.setBackgroundColor(Color.GREEN);

            option1.setEnabled(false);
            option2.setEnabled(false);
            option3.setEnabled(false);
            option4.setEnabled(false);

            scores = 1;
            //level_score.get(here_level).add(0,String.valueOf(scores));
        }
        else{
            //wrong answer
            scores = 0;
            view.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            //view.setBackgroundColor(Color.RED);

            if(questionList.get(here_level).getCorrectAnswer().equals(questionList.get(here_level).getOption1())){
                option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                //option1.setBackgroundColor(Color.GREEN);
            }
            else if(questionList.get(here_level).getCorrectAnswer().equals(questionList.get(here_level).getOption2())){
                option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                //option2.setBackgroundColor(Color.GREEN);
            }
            else if(questionList.get(here_level).getCorrectAnswer().equals(questionList.get(here_level).getOption3())){
                option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                //option3.setBackgroundColor(Color.GREEN);
            }
            else if(questionList.get(here_level).getCorrectAnswer().equals(questionList.get(here_level).getOption4())){
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
                Intent new_intent = new Intent(QuestionActivity.this, QuestionActivity2.class);
                new_intent.putExtras(bundle);
                new_intent.putExtra("scores",scores);
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
}
