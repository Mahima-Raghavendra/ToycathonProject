package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.app.Dialog;
import android.app.Presentation;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionActivity2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    Button next, player;
    Bundle bundle;
    int here_level;
    int count = 0,score;
    ArrayList<ArrayList<String>> listOfLists = new ArrayList<>(6);
    //ArrayList<ArrayList<String>> score_list = new ArrayList<>(6);
    List<QuestionModel2> questionsList;
    List<String> dragged_list;
    List<songs_list> song_list;
    private Dialog dialog;
    //List<String> questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bundle = getIntent().getExtras();
        here_level = (int) bundle.get("level");
        score = (int) bundle.get("scores");
        next = findViewById(R.id.next_btn);
        player = findViewById(R.id.playBtn);

        getMusicList(here_level);
        dialog = new Dialog(this);

        questionsList = new ArrayList<>();
        dragged_list = new ArrayList<>();
        questionsList.add(new QuestionModel2("Saraswati Namastubhyam", "Siddhirbhavatume Sada", "Vidhyarambham karishyami", "Varade Kamaroopini", "J", "K", "L"));
        questionsList.add(new QuestionModel2("umasutham shoka vinasha karanam", "Gajananam Bhoota ganadhi sevitam", "namami vigneshwara paada pankajam", "Kapitha jamboo phalasaara bhakshitam","M", "N","O"));
        questionsList.add(new QuestionModel2("Ramacandraya Vedhase", "Raghunathaya Nathaya", "Ramaya Ramabhadraya", "Sitayah Pataye Namah", "P", "Q", "R" ));
        questionsList.add(new QuestionModel2("Jitendriyam Buddhimataam Varistham", "Sriramdootam Saranam Prapadhye", "Vaataatmajam Vaanarayoothmukhyam", "Manojavam Maarutatulyavegam", "A", "B", "C"));
        questionsList.add(new QuestionModel2("Aum Trayambakam Yajaamahey", "Urvaarukamiva Bandhanaath", "Mrutyor Muksheeya Maamritaat", "Sugandhim Pusti Vardhanam", "D", "E", "F"));
        questionsList.add(new QuestionModel2("devakī-paramānandaḿ", "vasudevasutaḿ devaḿ", "kṛṣṇaḿ vande jagadgurum", "kaḿsa-cāṇūra-mardanam", "G", "H", "I"));

        for(int i=0;i<7;i++){
            dragged_list.add(i,questionsList.get(here_level).getSentence(i));
        }

        for(int i=0;i<6;i++){
            listOfLists.add(new ArrayList());
        }
        listOfLists.get(0).add(0,"Saraswati Namastubhyam");
        listOfLists.get(0).add(1,"Varade Kamaroopini");
        listOfLists.get(0).add(2,"Vidhyarambham karishyami");
        listOfLists.get(0).add(3,"Siddhirbhavatume Sada");
        listOfLists.get(0).add(4,"K");
        listOfLists.get(0).add(5,"J");
        listOfLists.get(0).add(6,"L");

        listOfLists.get(1).add(0,"Gajananam Bhoota ganadhi sevitam");
        listOfLists.get(1).add(1,"Kapitha jamboo phalasaara bhakshitam");
        listOfLists.get(1).add(2,"umasutham shoka vinasha karanam");
        listOfLists.get(1).add(3,"namami vigneshwara paada pankajam");
        listOfLists.get(1).add(4,"N");
        listOfLists.get(1).add(5,"M");
        listOfLists.get(1).add(6,"O");

        listOfLists.get(2).add(0,"Ramaya Ramabhadraya");
        listOfLists.get(2).add(1,"Ramacandraya Vedhase");
        listOfLists.get(2).add(2,"Raghunathaya Nathaya");
        listOfLists.get(2).add(3,"Sitayah Pataye Namah");
        listOfLists.get(2).add(4,"Q");
        listOfLists.get(2).add(5,"P");
        listOfLists.get(2).add(6,"R");

        listOfLists.get(3).add(0,"Manojavam Maarutatulyavegam");
        listOfLists.get(3).add(1,"Jitendriyam Buddhimataam Varistham");
        listOfLists.get(3).add(2,"Vaataatmajam Vaanarayoothmukhyam");
        listOfLists.get(3).add(3,"Sriramdootam Saranam Prapadhye");
        listOfLists.get(3).add(4,"B");
        listOfLists.get(3).add(5,"A");
        listOfLists.get(3).add(6,"C");

        listOfLists.get(4).add(0,"Aum Trayambakam Yajaamahey");
        listOfLists.get(4).add(1,"Sugandhim Pusti Vardhanam");
        listOfLists.get(4).add(2,"Urvaarukamiva Bandhanaath");
        listOfLists.get(4).add(3,"Mrutyor Muksheeya Maamritaat");
        listOfLists.get(4).add(4,"E");
        listOfLists.get(4).add(5,"D");
        listOfLists.get(4).add(6,"F");

        listOfLists.get(5).add(0,"vasudevasutaḿ devaḿ");
        listOfLists.get(5).add(1,"kaḿsa-cāṇūra-mardanam");
        listOfLists.get(5).add(2,"devakī-paramānandaḿ");
        listOfLists.get(5).add(3,"kṛṣṇaḿ vande jagadgurum");
        listOfLists.get(5).add(4,"H");
        listOfLists.get(5).add(5,"G");
        listOfLists.get(5).add(6,"I");

        /*questionsList = new ArrayList<>();
        questionsList.add("Saraswati Namastubhyam");
        questionsList.add("Siddhirbhavatume Sada");
        questionsList.add("Vidhyarambham karishyami");
        questionsList.add("Varade Kamaroopini");*/

        recyclerView = findViewById(R.id.recyclerview);
        recyclerAdapter = new RecyclerAdapter(questionsList, here_level);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(here_level);
            }
        });
    }

    private void getMusicList(int here_level) {

        song_list = new ArrayList<>();
        song_list.add(new songs_list(MediaPlayer.create(this, R.raw.saraswati)));
        song_list.add(new songs_list(MediaPlayer.create(this, R.raw.ganesha)));
        song_list.add(new songs_list(MediaPlayer.create(this, R.raw.rama)));
        song_list.add(new songs_list(MediaPlayer.create(this, R.raw.hanuman)));
        song_list.add(new songs_list(MediaPlayer.create(this, R.raw.shiva)));
        song_list.add(new songs_list(MediaPlayer.create(this, R.raw.krishna_sloka)));

        setSongs(song_list, here_level);

    }

    private void setSongs(final List<songs_list> songs, final int here_level) {

        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==0){
                    count=1;
                    player.setBackgroundResource(R.drawable.pause_btn);
                    songs.get(here_level).getSong().start();

                }
                else{
                    count=0;
                    player.setBackgroundResource(R.drawable.play_btn);
                    songs.get(here_level).getSong().pause();
                }
            }
        });

    }

    public void checkAnswer(final int here_level) {

        int flag=1;
        for(int i=0;i<7;i++){
            if(!dragged_list.get(i).equals(listOfLists.get(here_level).get(i))){
                flag=0;
                break;
            }
        }
        if(flag==1){
            //correct answer
            popup();
            score++;
            //score_list.get(here_level).set(0, String.valueOf(score));
            next.setText("Next");
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    next.setText("Check");
                    song_list.get(here_level).getSong().stop();
                    player.setBackgroundResource(R.drawable.play_btn);
                    Intent new_intent = new Intent(QuestionActivity2.this, QuestionActivity3.class);
                    new_intent.putExtras(bundle);
                    new_intent.putExtra("scores",score);
                    startActivity(new_intent);
                }
            });
        }
        else{
            //wrong answer
            Toast.makeText(this,"Oops!! Retry...",Toast.LENGTH_LONG).show();
        }
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.START|ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            String sent1, sent2;
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            Collections.swap(questionsList, fromPosition, toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition,toPosition);

            sent1 = dragged_list.get(toPosition);
            sent2 = dragged_list.get(fromPosition);
            dragged_list.set(fromPosition, sent1);
            dragged_list.set(toPosition,sent2);
            return false;
        }
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        }
    };

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
        Intent new_intent = new Intent(QuestionActivity2.this, MainActivity.class);
        startActivity(new_intent);
    }
}
