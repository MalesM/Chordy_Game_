package com.example.gospodin.chordy_game;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String>{


    public CustomAdapter(Context context, int resource, String[] chordNames ){
        super(context,resource,chordNames);
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View v = convertView;

        if( v==null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_row, null);
        }

        TextView textView = (TextView) v.findViewById(R.id.rowText);
        ImageView imageView = (ImageView) v.findViewById(R.id.rowImage);
        Button button = (Button) v.findViewById(R.id.rowButton);

        textView.setText(MainActivity.chordNames[position]);
        imageView.setImageResource(MainActivity.chordImagdes[position]);
        button.setText("PLAY");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.loaded){
                    MainActivity.soundPool.play(MainActivity.soundID[position], 1, 1, 1, 0, 1f);
                }
            }
        });

        return v;
    }

}
