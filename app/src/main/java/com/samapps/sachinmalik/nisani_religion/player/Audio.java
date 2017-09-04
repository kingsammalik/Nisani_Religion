package com.samapps.sachinmalik.nisani_religion.player;

import android.support.annotation.RawRes;

import java.io.Serializable;

/**
 * Created by jean on 27/06/16.
 */

public class Audio implements Serializable {
    private long id;
    private String title;
    private int position;
    private String path;
    private Origin origin;


    public Audio(String title, String path, Origin origin) {
        // It looks bad
        //int randomNumber = path.length() + title.length();

        // We init id  -1 and position with -1. And let JcPlayerView define it.
        // We need to do this because there is a possibility that the user reload previous playlist
        // from persistence storage like sharedPreference or SQLite.
        this.id = -1;
        this.position = -1;
        this.title = title;
        this.path = path;
        this.origin = origin;
    }

    public Audio(String title, String path, long id, int position, Origin origin) {
        this.id = id;
        this.position = position;
        this.title = title;
        this.path = path;
        this.origin = origin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public static Audio createFromRaw(@RawRes int rawId) {
        return new Audio(String.valueOf(rawId), String.valueOf(rawId), Origin.RAW);
    }

    public static Audio createFromRaw(String title, @RawRes int rawId) {
        return new Audio(title, String.valueOf(rawId), Origin.RAW);
    }

    public static Audio createFromAssets(String assetName) {
        return new Audio(assetName, assetName, Origin.ASSETS);
    }

    public static Audio createFromAssets(String title, String assetName) {
        return new Audio(title, assetName, Origin.ASSETS);
    }

    public static Audio createFromURL(String url) {
        return new Audio(url, url, Origin.URL);
    }

    public static Audio createFromURL(String title, String url) {
        return new Audio(title, url, Origin.URL);
    }

    public static Audio createFromFilePath(String filePath) {
        return new Audio(filePath, filePath, Origin.FILE_PATH);
    }

    public static Audio createFromFilePath(String title, String filePath) {
        return new Audio(title, filePath, Origin.FILE_PATH);
    }
}