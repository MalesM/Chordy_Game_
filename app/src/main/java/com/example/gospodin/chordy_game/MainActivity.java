package com.example.gospodin.chordy_game;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button dugmeStart, dugmePregled;
    static final String[] chordNames = new String[]{"A","B","C","D","E"};
    static final int[] chordImagdes = new int[]{
            R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d, R.drawable.e};
    static final int[] chords = new int[]{
            R.raw.a, R.raw.b, R.raw.c, R.raw.d, R.raw.e
    };
    static SoundPool soundPool;
    static boolean loaded = false;
    static int[] soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dugmeStart = (Button) findViewById(R.id.dugmeStart);
        dugmePregled = (Button) findViewById(R.id.dugmePregled);

        soundID = new int[chords.length];

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        for(int i=0 ; i<chords.length ; i++){
            soundID[i] = soundPool.load(this, chords[i], 1);
        }
    }

    public void startGame(View v){
        Intent i = new Intent(MainActivity.this, GameActivity.class);
        startActivity(i);
    }
    public void chordPreview(View v){
        Intent i = new Intent(MainActivity.this, ChordPreview.class);
        startActivity(i);
    }
}

