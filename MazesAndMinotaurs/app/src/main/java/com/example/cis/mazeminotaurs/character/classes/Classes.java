package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.R;

/**
 * Enumeration of Classes that are intended to be implemented.
 * <i>As of 12/8/17, Not all of these are currently implemented.</i>
 * @author jusmith on 3/30/17.
 */

public enum Classes {
    /**
     * Values for Amazon.
     */
    AMAZON(R.string.amazon, Amazon.class),

    /**
     * Values for the alternate Amazon.
     */
    AMAZON_B,

    /**
     * Values for Archer.
     */
    ARCHER,

    /**
     * Values for Barbarian
     */
    BARBARIAN(R.string.barbarian, Barbarian.class),

    /**
     * Values for Beastmaster
     */
    BEASTMASTER,

    /**
     * Values for Cavalryman
     */
    CAVALRYMAN,

    /**
     * Values for Centaur
     */
    CENTAUR(R.string.centaur, Centaur.class),

    /**
     * Values for Elementalist
     */
    ELEMENTALIST(R.string.elementalist, Elementalist.class),

    /**
     * Values for Hunter
     */
    HUNTER(R.string.hunter, Hunter.class),

    /**
     * Values for Lyrist
     */
    LYRIST(R.string.lyrist, Lyrist.class),

    /**
     * Values for Noble
     */
    NOBLE(R.string.noble, Noble.class),

    /**
     * Values for Nymph
     */
    NYMPH(R.string.nymph, Nymph.class),

    /**
     * Values for Priest
     */
    PRIEST(R.string.priest, Priest.class),

    /**
     * Values for Shapeshifter
     */
    SHAPESHIFTER,

    /**
     * Values for Sorcerer
     */
    SORCERER(R.string.sorceror, Sorcerer.class),

    /**
     * Values for Spearman
     */
    SPEARMAN(R.string.spearman, Spearman.class),

    /**
     * Values for Thief
     */
    THIEF(R.string.thief, Thief.class);

    /**
     * The resource id for the class' name.
     */
    int mResId;

    /**
     * The java class that represents the class.
     */
    Class mJavaClass;

    /**
     * Blank constructor. Doesn't setup anything.
     */
    Classes() {

    }

    /**
     * Recommended constructor.
     *
     * @param resId     the resource id of the class' name
     * @param javaClass the java class for the class.
     */
    Classes(int resId, Class javaClass) {
        mResId = resId;
        mJavaClass = javaClass;
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

    /**
     * Getter for mJavaClass.
     * @return the value of mJavaClass.
     */
    public Class getJavaClass() {
        return mJavaClass;
    }

    /**
     * Setter for mJavaClass
     * @param javaClass the new value of mJavaClass.
     */
    public void setJavaClass(Class javaClass) {
        mJavaClass = javaClass;
    }
}
