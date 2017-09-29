package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.R;

/**
 * Created by jusmith on 3/30/17.
 */

public enum Classes {
    AMAZON(R.string.amazon, Amazon.class),
    AMAZON_B,
    ARCHER,
    BARBARIAN(R.string.barbarian, Barbarian.class),
    BEASTMASTER,
    CAVALRYMAN,
    CENTAUR(R.string.centaur, Centaur.class),
    ELEMENTALIST(R.string.elementalist, Elementalist.class),
    HUNTER(R.string.hunter, Hunter.class),
    LYRIST(R.string.lyrist, Lyrist.class),
    NOBLE(R.string.noble, Noble.class),
    NYMPH(R.string.nymph, Nymph.class),
    PRIEST,
    SHAPESHIFTER,
    SORCEROR(R.string.sorceror, Sorcerer.class),
    SPEARMAN(R.string.spearman, Spearman.class),
    THIEF(R.string.thief, Thief.class);

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
