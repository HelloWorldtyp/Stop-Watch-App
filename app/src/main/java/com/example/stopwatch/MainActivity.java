package com.example.stopwatch;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView timeView;
    TextView timeView2;
    TextView timeView3;
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

        timeView = findViewById(R.id.timeView);
        timeView2 = findViewById(R.id.timeView2);
        timeView3 = findViewById(R.id.timeView3);
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
            timeView.setText(time+"");
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
                if(time >= 4){
                    time = 0;
                    time++;
                }
            }else{
                time--;
            }
            timeView.setText(time+"");
            handler.postDelayed(event, one_speed);
        }
    }

    private class secondCountEvent implements Runnable {

        @Override
        public void run(){
            if(up){
                time2++;
                if(time2 >= 4){
                    time2 = 0;
                    time2++;
                }
            }else{
                time2--;
            }
            timeView2.setText(time2+"");
            handler.postDelayed(event2, second_speed);
        }
    }

    private class thirdCountEvent implements Runnable {

        @Override
        public void run(){
            if(up){
                time3++;
                if(time3 >= 4){
                    time3 = 0;
                    time3++;
                }
            }else{
                time3--;
            }
            timeView3.setText(time3+"");
            handler.postDelayed(event3, third_speed);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("time",time);
        super.onSaveInstanceState(savedInstanceState);
    }

}