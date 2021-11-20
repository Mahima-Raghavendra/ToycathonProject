package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class QuizActivity extends AppCompatActivity {

    /*FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();*/

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Levels");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rv = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(layoutManager);
        List<LevelModel> list = new ArrayList<>();
        list.add(new LevelModel("Lord Saraswati"));
        list.add(new LevelModel("Lord Ganesha"));
        list.add(new LevelModel("Lord Rama"));
        list.add(new LevelModel("Lord Hanuman"));
        list.add(new LevelModel("Lord Shiva"));
        list.add(new LevelModel("Lord Krishna"));
        final LevelAdapter adapter = new LevelAdapter(list);
        rv.setAdapter(adapter);

        /*myRef.child("Level").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    list.add(dataSnapshot.getValue(LevelModel.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(QuizActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent new_intent = new Intent(QuizActivity.this, MainActivity.class);
        startActivity(new_intent);
    }
}