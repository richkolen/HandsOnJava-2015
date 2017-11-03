package com.example.richardkolen1.colormemory;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends ActionBarActivity implements View.OnClickListener{

    public SeekBar seek_bar;
    public TextView text_view;

    CharSequence getUserInput;

    MediaPlayer buttonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        barActivity();

        buttonSound = MediaPlayer.create(this, R.raw.beep);

        Button save = (Button) findViewById(R.id.saveButton);
        save.setOnClickListener(this);
    }

    public void barActivity(){

        seek_bar = (SeekBar)findViewById(R.id.seekBarSetting);
        text_view = (TextView)findViewById(R.id.textViewSetting);

        seek_bar.setMax(1);
        seek_bar.setMax(4);
        seek_bar.setProgress(2);

        text_view.setText("" + seek_bar.getProgress());

        seek_bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int seekBar_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                        seekBar_value = i;
                        text_view.setText("" + i);

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                        text_view.setText("" + seekBar_value);

                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_about) {
            goToAbout();
            return true;
        }

        switch(id) {
            case R.id.action_home:
                goToHome();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void goToAbout (){
        startActivity(new Intent(this, AboutActivity.class));
    }

    protected void goToHome (){
        startActivity(new Intent(this, MainActivity.class));
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.saveButton:

                buttonSound.start();

                getUserInput = text_view.getText().toString();

                Intent i = new Intent(SettingsActivity.this, MainActivity.class);
                i.putExtra("user", getUserInput);
                startActivity(i);

        }

    }
}
