package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    // set MediaPlayer as a global variable
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

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
        PhraseAdapter phraseAdapter = new PhraseAdapter (this, phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(phraseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Phrase phrase = phrases.get(position);
                mMediaPlayer.create(PhrasesActivity.this, phrase.getAudioResourceID());
                // no need to call prepare(); create() does that for you
                mMediaPlayer.start();
            }
        });
    }
}
