package com.patrick.musicplayer;

import java.io.Serializable;

/**
 * Created by Patrick on 10/28/2016.
 */

public class SongInfo implements Serializable {
    private long id;
    private String title;

    public SongInfo(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
