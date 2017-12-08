package com.example.cis.mazeminotaurs.character;

import com.example.cis.mazeminotaurs.R;

/**
 * The Enumeration of money types in Mazes and Minotaurs.
 * @author jusmith on 4/5/17.
 */

public enum Money {
    /**
     * Value of copper.
     */
    COPPER(R.string.copper),

    /**
     * Value of silver.
     */
    SILVER(R.string.silver),

    /**
     * Value of gold.
     */
    GOLD(R.string.gold);

    /**
     * The resource id of the money type's name.
     */
    private int resId;

    /**
     * Default constructor.
     *
     * @param resId the resource id of the money type's name.
     */
    Money(int resId) {
        setResId(resId);
    }

    /**
     * Getter of resId.
     * @return the value of resId.
     */
    public int getResId() {
        return resId;
    }

    /**
     * Setter of resId.
     * @param resId the new value of resId.
     */
    public void setResId(int resId) {
        this.resId = resId;
    }
}
