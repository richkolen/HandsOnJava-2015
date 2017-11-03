package com.example.richardkolen1.colormemory;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by richardkolen1 on 24-08-15.
 */
public class GameActivity extends Activity implements View.OnClickListener{

    MediaPlayer buttonSound;
    MediaPlayer buttonSound2;
    MediaPlayer buttonSound3;
    MediaPlayer buttonSound4;

    public TextView lat_view;
    public TextView long_view;

    Tracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        lat_view = (TextView)findViewById(R.id.textView4);
        long_view = (TextView)findViewById(R.id.textView5);

                gps = new Tracker(GameActivity.this);

                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    lat_view.setText("latitude:" + latitude);
                    long_view.setText("latitude:" + longitude);

                } else {
                    gps.showSettingsAlert();
                }


        Button colorOne = (Button) findViewById(R.id.button);
        colorOne.setOnClickListener(this);

        Button colorTwo = (Button) findViewById(R.id.button2);
        colorTwo.setOnClickListener(this);

        Button colorThree = (Button) findViewById(R.id.button3);
        colorThree.setOnClickListener(this);

        Button colorFour = (Button) findViewById(R.id.button4);
        colorFour.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button:

                buttonSound = MediaPlayer.create(this, R.raw.bell);
                buttonSound.start();
                break;

            case R.id.button2:

                buttonSound2 = MediaPlayer.create(this, R.raw.bleep);
                buttonSound2.start();
                break;

            case R.id.button3:

                buttonSound3 = MediaPlayer.create(this, R.raw.beep);
                buttonSound3.start();
                break;

            case R.id.button4:

                buttonSound4 = MediaPlayer.create(this, R.raw.bloop);
                buttonSound4.start();
                break;

        }

    }
}
