package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {

    // set MediaPlayer as a global variable
    private MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_members);

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
        FamMemAdapter famMemAdapter = new FamMemAdapter (this, famMems);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(famMemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                FamMem famMem = famMems.get(position);
                mMediaPlayer.create(FamilyMembersActivity.this, famMem.getAudioResourceID());
                // no need to call prepare(); create() does that for you
                mMediaPlayer.start();
            }
        });
    }
}
