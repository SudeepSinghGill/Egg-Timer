package iiitkota.com.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MediaPlayer tickSound;
    MediaPlayer hornSound;
    SeekBar timerSeekBar;
    TextView textView;
    boolean counterIsActive = false;
    Button timerButton;
    CountDownTimer countDownTimer;

    public void reset()
    {
        textView.setText("00:30");
        timerSeekBar.setProgress(30);
        timerButton.setText("GO");
        countDownTimer.cancel();
        counterIsActive = false;
        timerSeekBar.setEnabled(true);

    }

    public void updater(int Time)
    {
        int minutes = Time/60;
        int remSeconds = Time%60;
        textView.setText(String.format("%02d",minutes)+":"+String.format("%02d",remSeconds));


    }

    public void ControlTimer(View view) {
        if(counterIsActive==false)
        {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            timerButton.setText("STOP");
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    tickSound.start();
                    updater((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    hornSound.start();
                    reset();
                }


            }.start();
        }
        else
        {
            reset();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar) findViewById(R.id.seekBar);
        timerButton = (Button) findViewById(R.id.button2);
        tickSound = MediaPlayer.create(this,R.raw.tick);
        hornSound = MediaPlayer.create(this,R.raw.horn);
        textView = (TextView) findViewById(R.id.timerTextView);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updater(progress);

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
