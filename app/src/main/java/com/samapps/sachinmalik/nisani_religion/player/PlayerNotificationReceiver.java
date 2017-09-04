package com.samapps.sachinmalik.nisani_religion.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.samapps.sachinmalik.nisani_religion.player.PlayerExceptions.AudioListNullPointerException;

public class PlayerNotificationReceiver extends BroadcastReceiver {
    public PlayerNotificationReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AudioPlayer jcAudioPlayer = AudioPlayer.getInstance();
        String action = "";

        if (intent.hasExtra(NotificationPlayerService.ACTION)) {
            action = intent.getStringExtra(NotificationPlayerService.ACTION);
        }

        switch (action) {
            case NotificationPlayerService.PLAY:
                try {
                    jcAudioPlayer.continueAudio();
                    jcAudioPlayer.updateNotification();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case NotificationPlayerService.PAUSE:
                try {
                    if(jcAudioPlayer != null) {
                        jcAudioPlayer.pauseAudio();
                        jcAudioPlayer.updateNotification();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case NotificationPlayerService.NEXT:
                try {
                    jcAudioPlayer.nextAudio();
                } catch (AudioListNullPointerException e) {
                    try {
                        jcAudioPlayer.continueAudio();
                    } catch (AudioListNullPointerException e1) {
                        e1.printStackTrace();
                    }
                }
                break;

            case NotificationPlayerService.PREVIOUS:
                try {
                    jcAudioPlayer.previousAudio();
                } catch (Exception e) {
                    try {
                        jcAudioPlayer.continueAudio();
                    } catch (AudioListNullPointerException e1) {
                        e1.printStackTrace();
                    }
                }
                break;
        }
    }
}
