package com.patrick.musicplayer;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by Patrick on 10/28/2016.
 */
public class MainFragment extends ListFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();
        ArrayList<SongInfo> musicList = (ArrayList<SongInfo>)arguments.getSerializable(MainActivity.MAIN_FRAGMENT_BUNDLE);

        this.setListAdapter(new MusicInfoAdapter(getActivity().getApplicationContext(), musicList));
    }

    public class MusicInfoAdapter extends ArrayAdapter<SongInfo> {
        public MusicInfoAdapter(Context context, ArrayList<SongInfo> musicList) {
            super(context, 0, musicList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            SongInfo song = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.song_info, parent, false);
            }

            // Lookup view for data population
            TextView title = (TextView) convertView.findViewById(R.id.song_title);

            // Populate the data into the template view using the data object
            title.setText(song.getTitle());

            // Return the completed view to render on screen
            return convertView;
        }
    }
}
