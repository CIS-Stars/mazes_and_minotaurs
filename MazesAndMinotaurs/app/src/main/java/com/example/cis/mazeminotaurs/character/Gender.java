package com.example.cis.mazeminotaurs.character;

import com.example.cis.mazeminotaurs.R;

/**
 * The Enumeration of genders in Mazes and Minotaurs.
 * @author jusmith on 3/31/17.
 */

public enum Gender {
    /**
     * Value of Male.
     */
    MALE(R.string.male),

    /**
     * Value of Female.
     */
    FEMALE(R.string.female),

    /**
     * Value of Either.
     */
    EITHER(R.string.either_gender);

    /**
     * The resource id of the gender's name.
     */
    private int mResId;

    /**
     * Default constructor.
     *
     * @param resId resource id of the gender's name.
     */
    Gender(int resId) {
        mResId = resId;
    }

    /**
     * Getter for mResId.
     * @return the value of mResId.
     */
    public int getResId() {
        return mResId;
    }

    /**
     * Setter for mResId.
     * @param resId the new value of mResId.
     */
    public void setResId(int resId) {
        mResId = resId;
    }
}
