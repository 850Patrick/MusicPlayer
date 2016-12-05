package com.patrick.musicplayer;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    public static final String MAIN_FRAGMENT_BUNDLE = "MAIN_FRAGMENT_BUNDLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(MAIN_FRAGMENT_BUNDLE, getAllMusic());

            Fragment newFragment = new MainFragment();
            newFragment.setArguments(bundle);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.activity_main , newFragment).commit();
        }
    }

    /**
     * Returns a list of all music in the music folder on the phone.
     *
     * @return All music with their titles and IDs
     */
    private ArrayList<SongInfo> getAllMusic() {
        ArrayList<SongInfo> musicList = new ArrayList<>();

        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(
                uri,
                null,
                MediaStore.Audio.Media.DATA + " like ? ",
                new String[] {"%music%"},
                null
        );
//I love you
        if (cursor == null) {
            Log.e(TAG, "Query failed, cursor returned null");
        }
        else if (!cursor.moveToFirst()) {
            Log.d(TAG, "No music found");
        }
        else {
            int titleColumn = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            do {
                long id = cursor.getLong(idColumn);
                String title = cursor.getString(titleColumn);

                musicList.add(new SongInfo(id, title));
            } while (cursor.moveToNext());
        }

        return musicList;
    }
}
