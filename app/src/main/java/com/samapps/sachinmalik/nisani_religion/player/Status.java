package com.samapps.sachinmalik.nisani_religion.player;

/**
 * Created by rio on 02 January 2017.
 */

public class Status {
  enum PlayState {
    PLAY, PAUSE, STOP, UNINTIALIZED
  }

  private Audio jcAudio;
  private long duration;
  private long currentPosition;
  private PlayState playState;

  public Status() {
    this(null, 0, 0, PlayState.UNINTIALIZED);
  }

  public Status(Audio jcAudio, long duration, long currentPosition, PlayState playState) {
    this.jcAudio = jcAudio;
    this.duration = duration;
    this.currentPosition = currentPosition;
    this.playState = playState;
  }

  public Audio getJcAudio() {
    return jcAudio;
  }

  public void setJcAudio(Audio jcAudio) {
    this.jcAudio = jcAudio;
  }

  public long getDuration() {
    return duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }

  public long getCurrentPosition() {
    return currentPosition;
  }

  public void setCurrentPosition(long currentPosition) {
    this.currentPosition = currentPosition;
  }

  public PlayState getPlayState() {
    return playState;
  }

  public void setPlayState(PlayState playState) {
    this.playState = playState;
  }
}
