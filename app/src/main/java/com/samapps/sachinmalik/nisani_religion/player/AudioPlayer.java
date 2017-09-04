package com.samapps.sachinmalik.nisani_religion.player;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.samapps.sachinmalik.nisani_religion.player.PlayerExceptions.AudioListNullPointerException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jean on 12/07/16.
 */

class AudioPlayer {
    private PlayerService jcPlayerService;
    private PlayerView.JcPlayerViewServiceListener listener;
    private PlayerView.OnInvalidPathListener invalidPathListener;
    private PlayerView.JcPlayerViewStatusListener statusListener;
    private NotificationPlayerService jcNotificationPlayer;
    private List<Audio> playlist;
    private Audio currentJcAudio;
    private int currentPositionList;
    private Context context;
    private static AudioPlayer instance = null;
    private boolean mBound = false;
    private boolean playing;
    private boolean paused;
    private int position = 1;

    public AudioPlayer(Context context, List<Audio> playlist, PlayerView.JcPlayerViewServiceListener listener) {
        this.context = context;
        this.playlist = playlist;
        this.listener = listener;
        instance = AudioPlayer.this;
        this.jcNotificationPlayer = new NotificationPlayerService(context);

        initService();
    }

    public void setInstance(AudioPlayer instance) {
        this.instance = instance;
    }

    public void registerNotificationListener(PlayerView.JcPlayerViewServiceListener notificationListener) {
        this.listener = notificationListener;
        if (jcNotificationPlayer != null) {
            jcPlayerService.registerNotificationListener(notificationListener);
        }
    }

    public void registerInvalidPathListener(PlayerView.OnInvalidPathListener registerInvalidPathListener) {
        this.invalidPathListener = registerInvalidPathListener;
        if (jcPlayerService != null) {
            jcPlayerService.registerInvalidPathListener(invalidPathListener);
        }
    }

    public void registerServiceListener(PlayerView.JcPlayerViewServiceListener jcPlayerServiceListener) {
        this.listener = jcPlayerServiceListener;
        if (jcPlayerService != null) {
            jcPlayerService.registerServicePlayerListener(jcPlayerServiceListener);
        }
    }

    public void registerStatusListener(PlayerView.JcPlayerViewStatusListener statusListener) {
        this.statusListener = statusListener;
        if (jcPlayerService != null) {
            jcPlayerService.registerStatusListener(statusListener);
        }
    }

    public static AudioPlayer getInstance() {
        return instance;
    }

    public void playAudio(Audio JcAudio) throws AudioListNullPointerException {
        if (playlist == null || playlist.size() == 0) {
            throw new AudioListNullPointerException();
        }
        currentJcAudio = JcAudio;
        jcPlayerService.play(currentJcAudio);
        updatePositionAudioList();
        playing = true;
        paused = false;
    }

    private void initService(){
        if (!mBound) {
            startJcPlayerService();
        } else {
            mBound = true;
        }
    }

    public void nextAudio() throws AudioListNullPointerException {
        if (playlist == null || playlist.size() == 0) {
            throw new AudioListNullPointerException();
        } else {
            if (currentJcAudio != null) {
                try {
                    Audio nextJcAudio = playlist.get(currentPositionList + position);
                    this.currentJcAudio = nextJcAudio;
                    jcPlayerService.stop();
                    jcPlayerService.play(nextJcAudio);

                } catch (IndexOutOfBoundsException e) {
                    playAudio(playlist.get(0));
                    e.printStackTrace();
                }
            }

            updatePositionAudioList();
            playing = true;
            paused = false;
        }
    }

    public void previousAudio() throws AudioListNullPointerException {
        if (playlist == null || playlist.size() == 0) {
            throw new AudioListNullPointerException();
        } else {
            if (currentJcAudio != null) {
                try {
                    Audio previousJcAudio = playlist.get(currentPositionList - position);
                    this.currentJcAudio = previousJcAudio;
                    jcPlayerService.stop();
                    jcPlayerService.play(previousJcAudio);

                } catch (IndexOutOfBoundsException e) {
                    playAudio(playlist.get(0));
                    e.printStackTrace();
                }
            }

            updatePositionAudioList();
            playing = true;
            paused = false;
        }
    }

    public void pauseAudio() {
        jcPlayerService.pause(currentJcAudio);
        paused = true;
        playing = false;
    }

    public void continueAudio() throws AudioListNullPointerException {
        if (playlist == null || playlist.size() == 0) {
            throw new AudioListNullPointerException();
        } else {
            if (currentJcAudio == null) {
                currentJcAudio = playlist.get(0);
            }
            playAudio(currentJcAudio);
            playing = true;
            paused = false;
        }
    }

    public void createNewNotification(int iconResource) {
        if (currentJcAudio != null) {
            jcNotificationPlayer.createNotificationPlayer(currentJcAudio.getTitle(), iconResource);
        }
    }

    public void updateNotification() {
        jcNotificationPlayer.updateNotification();
    }

    public void seekTo(int time) {
        if (jcPlayerService != null) {
            jcPlayerService.seekTo(time);
        }
    }

    private void updatePositionAudioList() {
        for (int i = 0; i < playlist.size(); i++) {
            if (playlist.get(i).getId() == currentJcAudio.getId()) {
                this.currentPositionList = i;
            }
        }
    }

    private synchronized void startJcPlayerService() {
        if (!mBound) {
            Intent intent = new Intent(context.getApplicationContext(), PlayerService.class);
            intent.putExtra(NotificationPlayerService.PLAYLIST, (Serializable) playlist);
            intent.putExtra(NotificationPlayerService.CURRENT_AUDIO, currentJcAudio);
            context.bindService(intent, mConnection, context.getApplicationContext().BIND_AUTO_CREATE);
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            PlayerService.JcPlayerServiceBinder binder = (PlayerService.JcPlayerServiceBinder) service;
            jcPlayerService = binder.getService();

            if (listener != null) {
                jcPlayerService.registerServicePlayerListener(listener);
            }
            if (invalidPathListener != null) {
                jcPlayerService.registerInvalidPathListener(invalidPathListener);
            }
            if (statusListener != null) {
                jcPlayerService.registerStatusListener(statusListener);
            }
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
            playing = false;
            paused = true;
        }
    };

    boolean isPaused() {
        return paused;
    }

    boolean isPlaying() {
        return playing;
    }

    public void kill() {
        if (jcPlayerService != null) {
            jcPlayerService.stop();
            jcPlayerService.destroy();
        }

        if (mBound)
            try {
                context.unbindService(mConnection);
            } catch (IllegalArgumentException e) {
                //TODO: Add readable exception here
            }

        if (jcNotificationPlayer != null) {
            jcNotificationPlayer.destroyNotificationIfExists();
        }

        if (AudioPlayer.getInstance() != null)
            AudioPlayer.getInstance().setInstance(null);
    }

    public List<Audio> getPlaylist() {
        return playlist;
    }

    public Audio getCurrentAudio() {
        return jcPlayerService.getCurrentAudio();
    }
}
