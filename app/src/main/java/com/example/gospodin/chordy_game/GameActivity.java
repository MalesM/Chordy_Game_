package com.example.gospodin.chordy_game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

import static android.widget.Toast.makeText;

public class GameActivity extends AppCompatActivity {

    Button b1, b2, b3, b4, playB;
    TextView scoreText;
    private int flagPlay=0, flagAnswer=0, flagPick = 0;
    private int answerID;
    private int score, count;
    private Drawable d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        inic();
        changeAnswers();
        d = b1.getBackground();
    }

    public void checkAnswer(View v){
        if (flagPlay == 1 && flagPick == 0){
            switch (v.getId()){
                case R.id.b1:if (b1.getText().toString().equals(MainActivity.chordNames[answerID])){
                    processPositive(); b1.setBackgroundColor(Color.GREEN); flagAnswer=1; flagPick=1;}
                    else {processNegative(); b1.setBackgroundColor(Color.RED); flagAnswer=1; flagPick=1;}
                    break;
                case R.id.b2: if (b2.getText().toString().equals(MainActivity.chordNames[answerID])){
                    processPositive(); b2.setBackgroundColor(Color.GREEN); flagAnswer=1; flagPick=1;}
                    else {processNegative(); b2.setBackgroundColor(Color.RED); flagAnswer=1; flagPick=1;}
                    break;
                    case R.id.b3: if (b3.getText().toString().equals(MainActivity.chordNames[answerID])){
                    processPositive(); b3.setBackgroundColor(Color.GREEN); flagAnswer=1; flagPick=1;}
                    else {processNegative(); b3.setBackgroundColor(Color.RED); flagAnswer=1; flagPick=1;}
                    break;
                    case R.id.b4: if (b4.getText().toString().equals(MainActivity.chordNames[answerID])){
                    processPositive(); b4.setBackgroundColor(Color.GREEN); flagAnswer=1; flagPick=1;}
                    else {processNegative(); b4.setBackgroundColor(Color.RED); flagAnswer=1; flagPick=1;}
                    break;
                    default: throw new RuntimeException("Unknown button ID");
            }
        }else if (flagPick == 0){Toast toast = makeText(this, "Listen first!",Toast.LENGTH_SHORT);toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0) ;toast.show();}
    }

    public void changeAnswers(){
        Answer answer = myArray(MainActivity.chordNames);
        b1.setText(answer.getArr()[0]);
        b2.setText(answer.getArr()[1]);
        b3.setText(answer.getArr()[2]);
        b4.setText(answer.getArr()[3]);

        answerID = answer.getAnswer();

    }

    public void play(View v){
        if (MainActivity.loaded) {
            MainActivity.soundPool.play(MainActivity.soundID[answerID], 1, 1, 1, 0, 1f);
            flagPlay = 1;
        }
    }

    public void processPositive(){
        count++;
        score++;
        Toast toast = Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        scoreText.setText("Score: "+score+"/10");
    }

    public void processNegative(){
        count++;
        Toast toast = Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    public void next(View v) {
        if (count != 10) {
            if (flagAnswer == 0) {
                Toast toast = makeText(this, "Choose answer!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            } else {
                changeAnswers();
                resetButtonColor();
                flagAnswer = 0;
                flagPlay = 0;
                flagPick = 0;
            }
        }else {
            new AlertDialog.Builder(GameActivity.this)
                    .setTitle(scoreText.getText().toString())
                    .setMessage("Do you want to start new game?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            count = 0;
                            score = 0;
                            flagPlay = 0;
                            flagPick = 0;
                            flagAnswer = 0;
                            scoreText.setText("Score: 0/10");
                            changeAnswers();
                            resetButtonColor();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(GameActivity.this, MainActivity.class);
                            startActivity(i);
                        }
                    })
                    .show();

        }
    }

    private class Answer{
        private String[] arr;
        private int element;

        public Answer(String[] arr, int element){
            this.arr=arr;
            this.element=element;
        }
        public int getAnswer(){return this.element;}
        public String[] getArr(){return this.arr;}

    }
    public Answer myArray(String[] arr){
        int gornjaGranica, donjaGranica;
        int element = new Random().nextInt(arr.length);
        int pozicija = new Random().nextInt(4);

        if(element<arr.length/2){donjaGranica = 0; gornjaGranica = arr.length/2;}
            else{donjaGranica = arr.length/2; gornjaGranica = arr.length;}

        if ((element - pozicija) < donjaGranica) {
            pozicija = element;
        }
        if((element - pozicija + 3 )>(gornjaGranica - 1)){
            pozicija = element - gornjaGranica + 4;
        }
        String[] niz2 = Arrays.copyOfRange(arr, element-pozicija, element-pozicija+4);
        return new Answer(niz2, element);
    }

    public void resetButtonColor(){
        b1.setBackground(d);
        b2.setBackground(d);
        b3.setBackground(d);
        b4.setBackground(d);
    }
    public void inic(){
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        playB = (Button) findViewById(R.id.playB);
        scoreText = (TextView) findViewById(R.id.scoreText);
    }
}
