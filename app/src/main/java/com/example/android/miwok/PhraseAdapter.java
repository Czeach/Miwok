package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PhraseAdapter extends ArrayAdapter<Phrase> {

    public PhraseAdapter(Activity context, ArrayList<Phrase> phrases) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, phrases);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the Word object located at this position in the list
        Phrase currentPhrase = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_name
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_name);

        // Get the miwok translation from the current word object and
        // set this text on the miwok TextView
        miwokTextView.setText(currentPhrase.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID english_name
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.english_name);

        // Get the english translation from the current word object and
        // set this text on the english TextView
        englishTextView.setText(currentPhrase.getDefaultTranslation());

        //Find the imageView in the list_item.xml layout
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.images);

        if (currentPhrase.hasImage()){
            //Set the image to iconView
            iconView.setImageResource(currentPhrase.getImageResourceID());

            // make sure the view is visible
            iconView.setVisibility(View.VISIBLE);
        }
        else {
            // otherwise, hide the imageView
            iconView.setVisibility(View.GONE);
        }

        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
