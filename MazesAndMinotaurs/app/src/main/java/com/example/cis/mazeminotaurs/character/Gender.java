package com.example.cis.mazeminotaurs.character;

import com.example.cis.mazeminotaurs.R;

/**
 * Created by jusmith on 3/31/17.
 */

public enum Gender {
    MALE(R.string.male),
    FEMALE(R.string.female),
    EITHER(R.string.either_gender);

    private int mResId;

    Gender(int resId) {
        mResId = resId;
    }

    public int getResId() {
        return mResId;
    }

    public void setResId(int resId) {
        mResId = resId;
    }
}
