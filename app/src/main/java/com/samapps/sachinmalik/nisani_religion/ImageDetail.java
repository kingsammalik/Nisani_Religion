package com.samapps.sachinmalik.nisani_religion;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.samapps.sachinmalik.nisani_religion.Interfaces.PlayerCallback;
import com.samapps.sachinmalik.nisani_religion.player.Audio;
import com.samapps.sachinmalik.nisani_religion.player.PlayerView;

import java.util.ArrayList;

public class ImageDetail extends BaseActivity {

    //PlayerCallback playerCallback;
    ViewGroup viewGroup;
    String url="http://www.prapatti.com/slokas/mp3/hanumaanchalisaa.mp3";
    PlayerView jcPlayerView;
    ArrayList<Audio> jcAudios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView im = (ImageView)findViewById(R.id.header_logo);
        im.setImageResource(R.drawable.image5);
        viewGroup=(ViewGroup)findViewById(R.id.root_view);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        jcPlayerView = (PlayerView) findViewById(R.id.player);
        jcAudios = new ArrayList<>();
        jcAudios.add(Audio.createFromURL("hanuman chalisa",url));
        jcPlayerView.initPlaylist(jcAudios);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"on click");
                jcPlayerView.setVisibility(View.VISIBLE);
                jcPlayerView.playAudio(jcPlayerView.getMyPlaylist().get(0));
                jcPlayerView.createNotification();
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                //ImageDetail.super.addContentView(viewGroup);
                ImageDetail.super.playAudio(jcPlayerView);
            }
        });
    }
}
