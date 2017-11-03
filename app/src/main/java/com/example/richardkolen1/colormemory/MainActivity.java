package com.example.richardkolen1.colormemory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    public SeekBar seek_bar;
    public TextView text_view;

    MediaPlayer buttonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barActivity();

        buttonSound = MediaPlayer.create(this, R.raw.beep);

        Button start = (Button) findViewById(R.id.startButton);
        start.setOnClickListener(this);

        Button explain = (Button) findViewById(R.id.explainButton);
        explain.setOnClickListener(this);
    }

    public void barActivity(){

        seek_bar = (SeekBar)findViewById(R.id.seekBar);
        text_view = (TextView)findViewById(R.id.textView);

        seek_bar.setMax(1);
        seek_bar.setMax(4);


        //String data = getIntent().getExtras().getString("user");
        //int f = Integer.parseInt(data);

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
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            goToSettings();
            return true;
        }

        switch(id) {
            case R.id.action_about:
                goToAbout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.startButton:
                if (isOnline()) {
                    buttonSound.start();

                    startActivity(new Intent(this, StartActivity.class));
                    break;
                } else {
                    toastMessage();
                }
            case R.id.explainButton:
                buttonSound.start();

                startActivity(new Intent(this, ExplainActivity.class));
                break;
        }

    }

    protected boolean isOnline() {
        ConnectivityManager check = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = check.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    protected void toastMessage (){
        Toast.makeText(this, "Zorg dat u eerst bent verbonden met het internet", Toast.LENGTH_LONG).show();}

    protected void goToSettings(){
        startActivity(new Intent(this, SettingsActivity.class));
    }

    protected void goToAbout (){
        startActivity(new Intent(this, AboutActivity.class));
    }

}
