package com.example.cis.mazeminotaurs.character.stats;

import com.example.cis.mazeminotaurs.R;

import java.io.Serializable;

/**
 * Enumeration of Scores for characters.
 * @author jsmith on 3/30/17.
 */

public enum Score implements Serializable {
    /**
     * Value for might.
     */
    MIGHT(R.string.might),

    /**
     * Value for grace.
     */
    GRACE(R.string.grace),

    /**
     * Value for skill.
     */
    SKILL(R.string.skill),

    /**
     * Value for will.
     */
    WILL(R.string.will),

    /**
     * Value for wits.
     */
    WITS(R.string.wits),

    /**
     * Value for luck.
     */
    LUCK(R.string.luck);

    /**
     * The resource id for the score's name.
     */
    private int mResId;

    /**
     * Default constructor.
     *
     * @param resId the resource id of the score's name.
     */
    Score(int resId) {
        mResId = resId;
    }

    /**
     * Getter for mResId.
     *
     * @return the value of mResId.
     */
    public int getResId() {
        return mResId;
    }

    /**
     * Helper getter for noble's martial heritage.
     * @return a score array containing {@code Score.WILL}, {@code Score.WITS},
     * and {@code Score.GRACE}.
     */
    public static Score[] getMentalScores() {
        return new Score[]{WILL, WITS, GRACE};
    }

    /**
     * Helper getter for noble's mental heritage.
     * @return a score array containing {@code Score.MIGHT}, {@code Score.SKILL}.
     */
    public static Score[] getMartialScores() {
        return new Score[]{MIGHT, SKILL};
    }
}
