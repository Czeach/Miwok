package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    // set MediaPlayer as a global variable
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        // create an array list of words //
        final ArrayList<Color> colors = new ArrayList<Color>();


        //colors.add
        colors.add(new Color("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        colors.add(new Color("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        colors.add(new Color("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        colors.add(new Color("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        colors.add(new Color("black", "kululli", R.drawable.color_black, R.raw.color_black));
        colors.add(new Color("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        colors.add(new Color("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colors.add(new Color("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        // the adapter creates a layout for each item on the list using the simple_list_item_1.xml layout resource defined in the android framework. the list item layout resource contains one textview which the adapter will set
        ColorAdapter colorAdapter = new ColorAdapter (this, colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(colorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Color color = colors.get(position);
                mMediaPlayer.create(ColorsActivity.this, color.getAudioResourceID());
                // no need to call prepare(); create() does that for you
                mMediaPlayer.start();
            }
        });
    }

}
