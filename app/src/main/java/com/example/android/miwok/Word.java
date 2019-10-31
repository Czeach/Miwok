package com.example.android.miwok;

// it contains a Miwok translation and a default translation for each word
public class Word {

    // default translation for the word
    private String mDefaultTranslation;

    // miwok translation for the word
    private  String mMiwokTranslation;

    //private integer variable to hold the image resource id
    private int mImageResourceID;

    //private integer variable to hold the audio resource id
    private int mAudioResourceID;

    // create a new word object
    // defaultTranslation is the word in english
    // miwokTranslation is the word in miwok
    public Word (String defaultTranslation, String miwokTranslation, int audioResourceID){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceID = audioResourceID;
    }

    // create a new word object
    // defaultTranslation is the word in english
    // miwokTranslation is the word in miwok
    // imageResourceID is the image associated with the word
    public Word (String defaultTranslation, String miwokTranslation, int imageResourceID, int audioResourceID){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceID = imageResourceID;
        mAudioResourceID = audioResourceID;
    }

    // get the default translation of the word
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    // get the miwok translation of the word
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    // get the image
    public int getImageResourceID(){
        return mImageResourceID;
    }

    // get the audio
    public int getAudioResourceID(){
        return mAudioResourceID;
    }

}
