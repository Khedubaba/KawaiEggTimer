package com.codellion.kawaieggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int minutes, seconds;
    boolean playStatus = false;
    ImageButton playStopBtn;

    public void playButtonPressed(View view){
        if(playStatus){
            playStopBtn.setImageResource(R.drawable.ic_play_play_48dp);
            playStatus = false;
        }
        else{
            playStopBtn.setImageResource(R.drawable.ic_stop_white_48dp);
            playStatus = true;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView timeMinText = (TextView) findViewById(R.id.minutestextView);
        final TextView timeSecText = (TextView) findViewById(R.id.secondstextView);

        playStopBtn = (ImageButton) findViewById(R.id.playButton);

        final SeekBar timeSeekbar = (SeekBar) findViewById(R.id.timerseekBar);
        timeSeekbar.setMax(600);
        timeSeekbar.setProgress(30);
        final String zero = "0";

        timeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minutes = progress/60;
                seconds = progress - (minutes * 60);
                Log.i("Minutes", Integer.toString(minutes));
                Log.i("Seconds", Integer.toString(seconds));
                if(minutes != 10){
                    timeMinText.setText(zero + Integer.toString(minutes));
                    if(seconds < 10){
                        timeSecText.setText(zero + Integer.toString(seconds));
                    }
                    else{
                        timeSecText.setText(Integer.toString(seconds));
                    }
                }
                else{
                    timeMinText.setText(Integer.toString(minutes));
                    if(seconds < 10){
                        timeSecText.setText(zero + Integer.toString(seconds));
                    }
                    else{
                        timeSecText.setText(Integer.toString(seconds));
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
