package com.example.richardkolen1.colormemory;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;


public class AboutActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        VideoView video = (VideoView) findViewById(R.id.videoView);
        video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.allaboutme));
        video.setMediaController(new MediaController(this));
        video.requestFocus();
        video.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_about, menu);
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
            case R.id.action_home:
                goToHome();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void goToSettings(){
        startActivity(new Intent(this, SettingsActivity.class));
    }

    protected void goToHome (){
        startActivity(new Intent(this, MainActivity.class));
    }

}
