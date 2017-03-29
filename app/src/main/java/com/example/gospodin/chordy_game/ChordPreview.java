package com.example.gospodin.chordy_game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ChordPreview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_chord);

        ListView list = (ListView) findViewById(R.id.list);
        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.list_row, MainActivity.chordNames);

        list.setAdapter(customAdapter);
    }
}
