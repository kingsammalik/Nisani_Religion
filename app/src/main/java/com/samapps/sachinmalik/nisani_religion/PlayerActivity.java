package com.samapps.sachinmalik.nisani_religion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.samapps.sachinmalik.nisani_religion.player.Audio;
import com.samapps.sachinmalik.nisani_religion.player.PlayerView;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity implements PlayerView.JcPlayerViewServiceListener{

    String url="http://www.prapatti.com/slokas/mp3/hanumaanchalisaa.mp3";
    PlayerView jcPlayerView;
    ArrayList<Audio> jcAudios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_player);
        jcPlayerView = (PlayerView) findViewById(R.id.player);
        jcAudios = new ArrayList<>();
        jcAudios.add(Audio.createFromURL("hanuman chalisa",url));
        jcPlayerView.initPlaylist(jcAudios);
        ((TextView)findViewById(R.id.shlok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //jcPlayerView.playAudio(jcPlayerView.getMyPlaylist().get(0));
                //jcPlayerView.createNotification();
                startActivity(new Intent(PlayerActivity.this,ImageDetail.class));
            }
        });
    }

    @Override
    public void onPreparedAudio(String audioName, int duration) {
        Log.e("player","onpreparedaudio");
    }

    @Override
    public void onCompletedAudio() {
        Log.e("player","oncompleteaudio");
    }

    @Override
    public void onPaused() {
        Log.e("player","onpaused");
    }

    @Override
    public void onContinueAudio() {
        Log.e("player","oncontinue");
    }

    @Override
    public void onPlaying() {
        Log.e("player","playing");
    }

    @Override
    public void onTimeChanged(long currentTime) {
        Log.e("player","time chnaged");
    }

    @Override
    public void updateTitle(String title) {
        Log.e("player","update");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        jcPlayerView.kill();
    }
}
