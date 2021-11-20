package com.example.quiz;

import android.media.MediaPlayer;

public class songs_list {

    MediaPlayer song;

    public songs_list(MediaPlayer song) {
        this.song = song;
    }

    public MediaPlayer getSong() {
        return song;
    }

    public void setSong(MediaPlayer song) {
        this.song = song;
    }
}
