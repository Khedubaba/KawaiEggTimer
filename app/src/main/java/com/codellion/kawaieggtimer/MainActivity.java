package com.codellion.kawaieggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int minutes;
    int seconds;
    boolean playStatus = false;
    ImageButton playStopBtn;
    CountDownTimer mCountDownTimer;
    TextView timeSecText;
    SeekBar timeSeekbar;
    MediaPlayer mMediaPlayer;
    ImageView mImageView;

    public void resetTimer(){
        playStatus = false;
        timeSecText.setText("0:30");
        timeSeekbar.setProgress(30);
        timeSeekbar.setEnabled(true);
        mCountDownTimer.cancel();
        playStopBtn.setImageResource(R.drawable.ic_play_play_48dp);

    }
    public void playButtonPressed(View view){
        if(playStatus){
            playStopBtn.setImageResource(R.drawable.ic_play_play_48dp);
            playStatus = false;
            timeSeekbar.setEnabled(true);
            mImageView.setImageResource(R.drawable.egg2);
            //stop timer code and change button image to play
            mCountDownTimer.cancel();
            resetTimer();

        }
        else{
            playStopBtn.setImageResource(R.drawable.ic_stop_white_48dp);
            playStatus = true;
            timeSeekbar.setEnabled(false);
            //start timer code and change button image to stop
            mCountDownTimer.start();
        }
    }

    public void updateTimer(int secondsLeft){
        minutes = secondsLeft/60;
        seconds = secondsLeft - (minutes * 60);
        String secondString = Integer.toString(seconds);
        if(seconds <= 9){
            secondString = "0" + secondString;
        }
        timeSecText.setText(Integer.toString(minutes) + ":" + secondString);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = mImageView = (ImageView) findViewById(R.id.eggimageView);

        final int sec = 30;
        final int half = sec/2;
        mCountDownTimer = new CountDownTimer(sec*1000 + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("Seconds Left: ", String.valueOf(millisUntilFinished/1000));
                updateTimer((int)millisUntilFinished/1000);
                if(half == ((int)millisUntilFinished/1000)){
                    mImageView.setImageResource(R.drawable.crackegg2);
                }
            }

            @Override
            public void onFinish() {
                mImageView.setImageResource(R.drawable.chick2);
                Log.i("Done: ", "No more countdown.");
                mMediaPlayer.start();
            }
        };


        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
        timeSecText = (TextView) findViewById(R.id.timerTextView);

        playStopBtn = (ImageButton) findViewById(R.id.playButton);

        timeSeekbar = (SeekBar) findViewById(R.id.timerseekBar);
        timeSeekbar.setMax(600);
        timeSeekbar.setProgress(30);
        final String zero = "0";

        timeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, final int progress, boolean fromUser) {
                final int halfTime = progress/2;

                updateTimer(progress);
                mCountDownTimer = new CountDownTimer(timeSeekbar.getProgress()*1000 + 100, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Log.i("Seconds Left: ", String.valueOf(millisUntilFinished/1000));
                        updateTimer((int)millisUntilFinished/1000);
                        if(halfTime == ((int)millisUntilFinished/1000)){
                            mImageView.setImageResource(R.drawable.crackegg2);
                        }
                    }

                    @Override
                    public void onFinish() {
                        Log.i("Done: ", "No more countdown.");
                        mImageView.setImageResource(R.drawable.chick2);
                        mMediaPlayer.start();
                    }
                };
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
