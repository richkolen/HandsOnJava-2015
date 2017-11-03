package com.example.richardkolen1.colormemory;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by richardkolen1 on 24-08-15.
 */
public class Color1Activity extends Activity {

    MediaPlayer buttonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcolor1);

        buttonSound = MediaPlayer.create(this, R.raw.beep);

        final Thread startTimer = new Thread() {
            public void run() {

                try {

                    buttonSound.start();
                    sleep(1000);



                    Intent i = new Intent(Color1Activity.this, Color2Activity.class);
                    startActivity(i);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };


        startTimer.start();
    }

}
