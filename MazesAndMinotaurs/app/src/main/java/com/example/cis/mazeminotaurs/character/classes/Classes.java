package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.R;

/**
 * Created by jusmith on 3/30/17.
 */

public enum Classes {
    AMAZON,
    AMAZON_B,
    ARCHER,
    BARBARIAN(R.string.barbarian, Barbarian.class),
    BEASTMASTER,
    CAVALRYMAN,
    CENTAUR,
    ELEMENTALIST,
    HUNTER,
    LYRIST,
    NOBLE,
    NYMPH,
    PRIEST,
    SHAPESHIFTER,
    SORCEROR,
    SPEARMAN,
    THIEF;

    int mResId;
    Class mJavaClass;

    Classes() {

    }

    Classes(int resId, Class javaClass) {
        mResId = resId;
        mJavaClass = javaClass;
    }

    public int getResId() {
        return mResId;
    }

    public void setResId(int resId) {
        mResId = resId;
    }

    public Class getJavaClass() {
        return mJavaClass;
    }

    public void setJavaClass(Class javaClass) {
        mJavaClass = javaClass;
    }
}
