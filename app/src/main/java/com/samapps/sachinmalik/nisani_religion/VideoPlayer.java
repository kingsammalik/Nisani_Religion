package com.samapps.sachinmalik.nisani_religion;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggablePanel;
import com.github.pedrovgs.DraggableView;

public class VideoPlayer extends AppCompatActivity implements DraggableListener {
    MediaController mediaController;
    VideoView videoView;
    DraggablePanel draggableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
         videoView = (VideoView)findViewById(R.id.video_view);
        draggableView = (DraggablePanel)findViewById(R.id.draggable_view);
        videoView.setVideoPath(
                "https://www.fmdload.xyz/upload_file/Videos/MP4/New%20Bollywood%20Mp4%20Videos%20(2017)/Golmaal%20Again%20(2017)%20Mp4%20Videos%20/Golmaal%20Again%20-%20Official%20Trailer%20-%20MP4.mp4");

        videoView.start();
        draggableView.setDraggableListener(this);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                /*
                 * add media controller
                 */
                        mediaController = new MediaController(VideoPlayer.this);
                        mediaController.show();
                        videoView.setMediaController(mediaController);
                /*
                 * and set its position on screen
                 */
                        mediaController.setAnchorView(videoView);
                    }
                });
            }
        });
    }

    @Override
    public void onMaximized() {
        Log.e("playeractity","on maximized");
    }

    @Override
    public void onMinimized() {
        Log.e("playeractity","on minimized");
    }

    @Override
    public void onClosedToLeft() {
        Log.e("playeractity","on closeto left");
        finish();
    }

    @Override
    public void onClosedToRight() {
        Log.e("playeractity","on closedto right");
    }

    @Override
    protected void onDestroy() {
        videoView.stopPlayback();
        super.onDestroy();
    }
}
