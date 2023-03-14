package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView timeView;
    ImageView timeView2;
    ImageView timeView3;
    Drawable cherry;
    Drawable graphs;
    Drawable strawberry;
    Drawable pear;
    Button button;


    int time;
    int time2;
    int time3;
    boolean up;
    int one_speed;
    int second_speed;
    int third_speed;

    CountEvent event;
    secondCountEvent event2;
    thirdCountEvent event3;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeView = findViewById(R.id.imageView);
        timeView2 = findViewById(R.id.imageView2);
        timeView3 = findViewById(R.id.imageView3);
        cherry = getDrawable(R.drawable.cherry);
        graphs = getDrawable(R.drawable.grape);
        strawberry = getDrawable(R.drawable.strawberry);
        pear = getDrawable(R.drawable.pear);

        button = findViewById(R.id.button);

        time = 0;
        time2 = 0;
        time3 = 0;
        one_speed = 500;
        second_speed = 300;
        third_speed = 100;
        up = true;

        event = new CountEvent();
        event2 = new secondCountEvent();
        event3 = new thirdCountEvent();
        handler = new Handler();

        if(savedInstanceState != null){
            time = savedInstanceState.getInt("time");
            timeView.setImageDrawable(cherry);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(button.getText().equals("Start")) {
                    handler.postDelayed(event, 1000);
                    handler.postDelayed(event2, 1500);
                    handler.postDelayed(event3, 2000);
                    button.setText("Stop");

                }else{
                    handler.removeCallbacks(event);
                    handler.removeCallbacks(event2);
                    handler.removeCallbacks(event3);
                    button.setText("Start");
                    Toast t;
                    if (time == time2 && time == time3 && button.getText().equals("Stop")) {
                        t = Toast.makeText(getApplicationContext(), "JACKPOT!!!", Toast.LENGTH_SHORT);
                    } else {
                        t = Toast.makeText(getApplicationContext(), "No Jackpot YOU LOSE!!!!", Toast.LENGTH_SHORT);
                    }
                    t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    t.show();
                }
            }
        });

    }

    private class CountEvent implements Runnable {

        @Override
        public void run(){
            if(up){
                time++;
                if(time >= 4)
                    time = 0;
                    if(time == 0){
                        timeView.setImageDrawable(cherry);

                }else if(time == 1){
                    timeView.setImageDrawable(graphs);
                }else if(time == 2){
                    timeView.setImageDrawable(strawberry);
                }else {
                        timeView.setImageDrawable(pear);
                    }
            }else {
                time--;
            }
            handler.postDelayed(event, one_speed);
        }
    }

    private class secondCountEvent implements Runnable {

        @Override
        public void run(){
            if(up){
                time2++;
                if(time2 >= 4)
                    time2 = 0;
                if(time2 == 0){
                    timeView2.setImageDrawable(cherry);

                }else if(time2 == 1){
                    timeView2.setImageDrawable(graphs);
                }else if(time2 == 2){
                    timeView2.setImageDrawable(strawberry);
                }else {
                    timeView2.setImageDrawable(pear);
                }
            }else{
                time2--;
            }
            handler.postDelayed(event2, second_speed);
        }
    }

    private class thirdCountEvent implements Runnable {

        @Override
        public void run(){
            if(up){
                time3++;
                if(time3 >= 4)
                    time3 = 0;
                if(time3 == 0){
                    timeView3.setImageDrawable(cherry);

                }else if(time3 == 1){
                    timeView3.setImageDrawable(graphs);
                }else if(time3 == 2){
                    timeView3.setImageDrawable(strawberry);
                }else {
                    timeView3.setImageDrawable(pear);
                }
            }else{
                time3--;
            }
            handler.postDelayed(event3, third_speed);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("time",time);
        super.onSaveInstanceState(savedInstanceState);
    }

}