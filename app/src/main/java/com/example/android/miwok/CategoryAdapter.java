package com.example.android.miwok;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

// CategoryAdapter is a FragmentPagerAdapter that can provide the layout for each list item based on a data source which is a list of {@link Word} objects.

public class CategoryAdapter extends FragmentPagerAdapter {

    //Context of the app
    private Context mContext;

    // Create a new CategoryAdapter object.
    // fm is the fragment manager that will keep each fragment's state in the adapter across swipes.
    public CategoryAdapter(Context context, FragmentManager fm){
        super(fm);
        mContext = context;
    }

    //  Return the Fragment that should be displayed for the given page number.
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new NumbersFragment();
        }
        else if (position == 1){
            return new FamMemFragment();
        }
        else if (position == 2){
            return new ColorsFragment();
        }
        else {
            return new PhrasesFragment();
        }
    }

    // Return the total number of pages
    @Override
    public int getCount() {
        return 4;
    }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return mContext.getString(R.string.category_numbers);
            }
            else if (position == 1) {
                return mContext.getString(R.string.category_family);
            }
            else if (position == 2) {
                return mContext.getString(R.string.category_colors);
            }
            else {
                return mContext.getString(R.string.category_phrases);
            }
        }


}

