package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamMemFragment extends Fragment {

    // set MediaPlayer as a global variable
    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            // The AUDIOFOCUS_LOSS_TRANSIENT case means that we have lost audio focus for a short period of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK
            // case means that the app is allowed to continue playing audio, but at a lower volume.
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                // Pause playback and reset player to the start of the file. i.e play the word from the beginning
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
            // AUDIOFOCUS_GAIN means that the app has regained audio focus and can resume playback
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                // Resume playback
                mMediaPlayer.start();
            }
            // AUDIOFOCUS_LOSS means the app has lost audio focus
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                // Release playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };


    public FamMemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_family_members, container, false);

        // Create and set up the audio manager to request audio focus
        mAudioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        // create an array list of words //
        final ArrayList<FamMem> famMems = new ArrayList<FamMem>();

        //add family members
        famMems.add(new FamMem("father", "әpә", R.drawable.family_father, R.raw.family_father));
        famMems.add(new FamMem("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        famMems.add(new FamMem("son", "angsi", R.drawable.family_son, R.raw.family_son));
        famMems.add(new FamMem("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        famMems.add(new FamMem("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        famMems.add(new FamMem("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        famMems.add(new FamMem("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        famMems.add(new FamMem("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        famMems.add(new FamMem("grand mother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        famMems.add(new FamMem("grand father", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        // the adapter creates a layout for each item on the list using the simple_list_item_1.xml layout resource defined in the android framework. the list item layout resource contains one textview which the adapter will set
        FamMemAdapter famMemAdapter = new FamMemAdapter (getActivity(), famMems);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(famMemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get the object at the given position the user clicked on
                FamMem famMem = famMems.get(position);

                // release the media player if it already exists before playing a different sound
                releaseMediaPlayer();

                // Request audio focus for play back
                int result = mAudioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    // Audio focus has been granted

                    // create a media player to handle playing sounds in the app
                    mMediaPlayer = MediaPlayer.create(getActivity(), famMem.getAudioResourceID());

                    // no need to call prepare(); create() does that for you
                    mMediaPlayer.start();


                    // set up a listener so you can stop and release the media player once the sound has finished playing
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        // Release the mediaplayer resources after the activity is stopped because it wont be playing anymore sounds
        releaseMediaPlayer();
    }

    // Clean up the media player by releasing its resources.
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Abandon audio focus when playback complete. This also unregisters AudioFocusChangeListener so we dont get anymore callbacks
            mAudioManager.abandonAudioFocus(afChangeListener);
        }

    }
}
