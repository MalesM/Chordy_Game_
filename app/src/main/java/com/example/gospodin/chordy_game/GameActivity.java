package com.example.gospodin.chordy_game;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Button b1, b2, b3, b4, playB;
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
        if (flagPlay == 1){
            switch (v.getId()){
                case R.id.b1: if (b1.getText().toString().equals(MainActivity.chordNames[answerID]) && flagPick == 0){
                    processPositive(); b1.setBackgroundColor(Color.GREEN); flagAnswer=1; flagPick=1;}
                    else {processNegative(); b1.setBackgroundColor(Color.RED); flagAnswer=1; flagPick=1;}
                    break;
                case R.id.b2: if (b2.getText().toString().equals(MainActivity.chordNames[answerID]) && flagPick == 0){
                    processPositive(); b2.setBackgroundColor(Color.GREEN); flagAnswer=1; flagPick=1;}
                    else {processNegative(); b2.setBackgroundColor(Color.RED); flagAnswer=1; flagPick=1;}
                    break;
                    case R.id.b3: if (b3.getText().toString().equals(MainActivity.chordNames[answerID]) && flagPick == 0){
                    processPositive(); b3.setBackgroundColor(Color.GREEN); flagAnswer=1; flagPick=1;}
                    else {processNegative(); b3.setBackgroundColor(Color.RED); flagAnswer=1; flagPick=1;}
                    break;
                    case R.id.b4: if (b4.getText().toString().equals(MainActivity.chordNames[answerID]) && flagPick == 0){
                    processPositive(); b4.setBackgroundColor(Color.GREEN); flagAnswer=1; flagPick=1;}
                    else {processNegative(); b4.setBackgroundColor(Color.RED); flagAnswer=1; flagPick=1;}
                    break;
                    default: throw new RuntimeException("Unknown button ID");
            }
        }else {Toast toast = Toast.makeText(this, "Listen first!",Toast.LENGTH_SHORT);toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0) ;toast.show();}
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

    }

    public void processNegative(){

    }

    public void next(View v){
        if (flagAnswer == 0 ){
            Toast toast = Toast.makeText(this, "Choose answer!",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0) ;
            toast.show();
        }else{
            changeAnswers();
            resetButtonColor();
            flagAnswer = 0;
            flagPlay = 0;
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
        int element = new Random().nextInt(arr.length);
        int pozicija = new Random().nextInt(4);
        System.out.println(pozicija);
        if ((element - pozicija) < 0) {
            pozicija = element;
        }

        if((element - pozicija + 3 )>(arr.length - 1)){
            pozicija = element - arr.length + 4;
        }
        System.out.println(pozicija);
        System.out.println(arr[element]);
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
    }
}
