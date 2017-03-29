package com.example.gospodin.chordy_game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Button b1, b2, b3, b4, playB;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        inic();
        changeAnswers();
    }

    public void checkAnswer(View v){

    }

    public void changeAnswers(){

    }


    public class Answer{
        private int[] arr;
        private int element;

        public Answer(int[] arr, int element){
            this.arr=arr;
            this.element=element;
        }
        public int getAnswer(){return this.element;}
        public int[] getArr(){return this.arr;}

    }
    public Answer myArray(int[] arr){
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
        int[] niz2 = Arrays.copyOfRange(arr, element-pozicija, element-pozicija+4);

        return new Answer(niz2, element);
    }

    public void inic(){
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        playB = (Button) findViewById(R.id.playB);
    }
}
