package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static com.example.quiz.R.drawable.*;

public class QuestionActivity3 extends AppCompatActivity{

    Button next;
    TextView eng_meaning, hindi_meaning;
    private List<QuestionModel3> questionList;
    //ArrayList<ArrayList<String>> score_list = new ArrayList<>(6);
    int here_level;
    Button player;
    Bundle bundle;
    List<songs_list> song_list;
    int count = 0,score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        next = findViewById(R.id.next_btn);
        eng_meaning = findViewById(R.id.englishMeaning);
        hindi_meaning = findViewById(R.id.hindiMeaning);
        player = findViewById(R.id.playBtn);

        bundle = getIntent().getExtras();
        here_level = (int) bundle.get("level");
        score = (int) bundle.get("scores");

        getMeaningsList(here_level);
        getMusicList(here_level);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                //score_list.get(here_level).set(0, String.valueOf(score));
                song_list.get(here_level).getSong().stop();
                player.setBackgroundResource(R.drawable.play_btn);
                Intent nextIntent = new Intent(QuestionActivity3.this, QuestionActivity4.class);
                nextIntent.putExtras(bundle);
                nextIntent.putExtra("scores",score);
                startActivity(nextIntent);
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

    private void getMeaningsList(int here_level) {

        questionList = new ArrayList<>();
        questionList.add(new QuestionModel3("Oh Goddess Saraswati; salutations to you, the giver of boons, the one who fulfils desires. I shall begin my studies. May there always be accomplishment for me.", "ज्ञान की देवी माँ सरस्वती को मेरा नमस्कार, वर दायिनी माँ भगवती को मेरा प्रणाम |अपनी विद्या आरम्भ करने से पूर्व आपका नमन करती हूँ , मुझ पर अपनी सिद्धि की कृपा बनाये रखें|"));
        questionList.add(new QuestionModel3("Oh Lord with the elephant face, He who is served by the Celestial beings,He who eats the essence of kaith & Jamun fruits, Oh son of Mother Uma(Parvati), destroyer of suffering, we bow down to your Lotus feet","हे हाथी के मुख वाले,भूत गणों के द्वारा सेवा किए जाने वाले,आप कपिथा जामुन को ग्रहण करने वाले,जो उमा के पुत्र हैं। आप समस्त दुखो को समाप्त करते हैं। मैं विघ्न को दूर करने वाले श्री गणेश जी को,जिनके चरण कमल के समान हैं,नमन करता हूँ।"));
        questionList.add(new QuestionModel3("To Rama, Ramabhadra, Ramachandra, Brahma-swarupa, Raghunatha, the Lord, the consort of Sita to him, we offer salutations.","में उस सीतापति को नमन करता हूं जो (श्री) राम रामभद्र, रामचंद्र के रूप में समस्त वेदों के आधार हूं, जो रघुकुलश्रेष्ठ, सारे जगत के स्वामी हैं or राम, रामभद्र, रामचंद्र, ब्रह्मस्वरूप, रघुनाथ, भगवान, सीता के पति, को हम नमस्कार करते हैं।"));
        questionList.add(new QuestionModel3("Salute to the one who is swift as thought,more powerful than wind,the one who has conquered his senses,best amongst the wise,Chief among the Monkeys,to Lord Rama's Messenger,the incomparable Lord Hanuman,let me seek refuge.", "जो विचार के रूप में तेज है, हवा से अधिक शक्तिशाली है, जिसने अपनी इंद्रियों को जीत लिया है, जो बुद्धिमानों में सर्वश्रेष्ठ है, बंदरों में प्रमुख है, भगवान राम के दूत, अतुलनीय भगवान हनुमान, मुझे शरण लेने दो"));
        questionList.add(new QuestionModel3("We pray the three-eyed Lord Shiva who is fragrant and who nourishes devotees.Worshipping him may we be liberated from death for the sake of immortality just as the ripe cucumber easily separates itself from binding stalk","हम तीन आंखों वाले भगवान शिव से प्रार्थना करते हैं जो सुगंधित हैं और जो भक्तों का पोषण करते हैं। उनकी पूजा करने से हम अमरता के लिए मृत्यु से मुक्त हो सकते हैं जैसे पका हुआ खीरा आसानी से अपने आप को बंधने से अलग कर लेता है"));
        questionList.add(new QuestionModel3("I worship Lord Krishna, who is the spiritual master of the universe, who is the son of Vasudeva, who is the Lord, who killed Kamsa and Cāṇūra, and who is the bliss of Devaki.","मैं भगवान कृष्ण की पूजा करता हूं, जो ब्रह्मांड के आध्यात्मिक गुरु हैं, जो वासुदेव के पुत्र हैं, भगवान हैं, जिन्होंने कंस और चैर का वध किया, और देवकी का आनंद है।"));

        setQuestion(here_level);
    }

    private void setQuestion(int here_level) {

        eng_meaning.setText(questionList.get(here_level).getEnglish_meaning());
        hindi_meaning.setText(questionList.get(here_level).getHindi_meaning());

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent new_intent = new Intent(QuestionActivity3.this, MainActivity.class);
        startActivity(new_intent);
        return;
    }
}
