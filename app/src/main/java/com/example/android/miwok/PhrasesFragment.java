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
public class PhrasesFragment extends Fragment {

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


    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_phrases, container, false);

        // Create and set up the audio manager to request audio focus
        mAudioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        // create an array list of words //
        final ArrayList<Phrase> phrases = new ArrayList<Phrase>();

        //phrases.add
        phrases.add(new Phrase("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        phrases.add(new Phrase("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        phrases.add(new Phrase("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        phrases.add(new Phrase("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        phrases.add(new Phrase("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        phrases.add(new Phrase("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        phrases.add(new Phrase("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        phrases.add(new Phrase("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        phrases.add(new Phrase("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        phrases.add(new Phrase("Come here.", "әnni'nem", R.raw.phrase_come_here));

        // the adapter creates a layout for each item on the list using the simple_list_item_1.xml layout resource defined in the android framework. the list item layout resource contains one textview which the adapter will set
        PhraseAdapter phraseAdapter = new PhraseAdapter (getActivity(), phrases);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(phraseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get the object at the given position the user clicked on
                Phrase phrase = phrases.get(position);

                // release the media player if it already exists before playing a different sound
                releaseMediaPlayer();

                // Request audio focus for play back
                int result = mAudioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    // Audio focus has been granted

                    // create a media player to handle playing sounds in the app
                    mMediaPlayer = MediaPlayer.create(getActivity(), phrase.getAudioResourceID());

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
