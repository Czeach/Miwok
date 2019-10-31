package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FamMemAdapter extends ArrayAdapter<FamMem> {

    public FamMemAdapter(Activity context, ArrayList<FamMem> famMems) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, famMems);
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
        FamMem currentFamMem = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_name
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_name);

        // Get the miwok translation from the current word object and
        // set this text on the miwok TextView
        miwokTextView.setText(currentFamMem.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID english_name
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.english_name);

        // Get the english translation from the current word object and
        // set this text on the english TextView
        englishTextView.setText(currentFamMem.getDefaultTranslation());

        //Find the imageView in the list_item.xml layout
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.images);

        //Get the image resources ID
        //Set the image to iconView
        iconView.setImageResource(currentFamMem.getImageResourceID());

        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
