package com.samapps.sachinmalik.nisani_religion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jean.jcplayer.JcAudio;
import com.example.jean.jcplayer.JcAudioPlayer;
import com.example.jean.jcplayer.JcPlayerService;
import com.example.jean.jcplayer.JcPlayerView;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity implements JcPlayerService.JcPlayerServiceListener{

    String url="http://www.prapatti.com/slokas/mp3/hanumaanchalisaa.mp3";
    JcPlayerView jcPlayerView;
    ArrayList<JcAudio> jcAudios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_player);
        jcPlayerView = (JcPlayerView) findViewById(R.id.player);
        jcAudios = new ArrayList<>();
        jcAudios.add(JcAudio.createFromURL("hanuman chalisa",url));
        jcPlayerView.initPlaylist(jcAudios);
        ((TextView)findViewById(R.id.shlok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jcPlayerView.playAudio(jcPlayerView.getMyPlaylist().get(0));
                jcPlayerView.createNotification();
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
