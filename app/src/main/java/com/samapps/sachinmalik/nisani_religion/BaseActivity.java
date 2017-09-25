package com.samapps.sachinmalik.nisani_religion;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samapps.sachinmalik.nisani_religion.Interfaces.PlayerCallback;
import com.samapps.sachinmalik.nisani_religion.player.Audio;
import com.samapps.sachinmalik.nisani_religion.player.PlayerView;

import java.util.ArrayList;

/**
 * Created by sachinmalik on 10/09/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    String TAG="BaseActivity";
    String url="http://www.prapatti.com/slokas/mp3/hanumaanchalisaa.mp3";
    PlayerView playerView;
    ArrayList<Audio> jcAudios;
    private ArrayList<Class> runningActivities = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void addThisActivityToRunningActivityies (Class cls) {
        if (!runningActivities.contains(cls)) runningActivities.add(cls);
    }

    public void removeThisActivityFromRunningActivities (Class cls) {
        if (runningActivities.contains(cls)) runningActivities.remove(cls);
    }

    public boolean isActivityInBackStack (Class cls) {
        return runningActivities.contains(cls);
    }

    public void playAudio() {
        Log.e(TAG,"on playaudio");
        playerView.playAudio(playerView.getMyPlaylist().get(0));
        playerView.createNotification();
    }

    public void addContentView(ViewGroup viewGroup) {
        playerView=new PlayerView(this);
        jcAudios = new ArrayList<>();
        jcAudios.add(Audio.createFromURL("hanuman chalisa",url));
        playerView.initPlaylist(jcAudios);
       /* CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) viewGroup.getLayoutParams();

        params.setAnchorId(R.id.detail_view);
        params.anchorGravity = Gravity.BOTTOM; //we will anchor to the bottom line of the appbar
        params.gravity = Gravity.BOTTOM; //we want to be BELOW that line
        playerView.setLayoutParams(params);*/
        viewGroup.addView(playerView);
    }

}
