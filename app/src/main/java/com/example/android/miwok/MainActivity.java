package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the view that shows the numbers category//
        TextView numbers = (TextView)findViewById(R.id.numbers);

        // set a clickListener on that view//
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent numbersIntent = new Intent (MainActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
            }
        });

        TextView familyMembers = (TextView)findViewById(R.id.family);

        familyMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent (MainActivity.this, FamilyMembersActivity.class);
                startActivity(familyIntent);
            }
        });

        TextView colors = (TextView)findViewById(R.id.colors);

        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colors = new Intent (MainActivity.this, ColorsActivity.class);
                startActivity(colors);
            }
        });

        final TextView phrases = (TextView)findViewById(R.id.phrases);

        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phrases = new Intent (MainActivity.this, PhrasesActivity.class);
                startActivity(phrases);
            }
        });
    }

}
