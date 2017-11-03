package com.example.richardkolen1.colormemory;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ExplainActivity extends Activity implements View.OnClickListener{

    MediaPlayer buttonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        buttonSound = MediaPlayer.create(this, R.raw.beep);

        Button back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.backButton:

                    buttonSound.start();

                    startActivity(new Intent(this, MainActivity.class));
                    break;

        }

    }
}
