package com.samapps.sachinmalik.nisani_religion.player.PlayerExceptions;

/**
 * Created by jean on 01/09/16.
 */

public class AudioUrlInvalidException extends IllegalStateException {
    public AudioUrlInvalidException(String url) {
        super("The url does not appear valid: " + url);
    }
}
