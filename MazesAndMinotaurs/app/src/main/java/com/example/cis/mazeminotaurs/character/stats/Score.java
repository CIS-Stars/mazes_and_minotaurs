package com.example.cis.mazeminotaurs.character.stats;

import com.example.cis.mazeminotaurs.R;

import java.io.Serializable;

/**
 * Created by jusmith on 3/30/17.
 */

public enum Score implements Serializable {
    MIGHT(R.string.might),
    GRACE(R.string.grace),
    SKILL(R.string.skill),
    WILL(R.string.will),
    WITS(R.string.wits),
    LUCK(R.string.luck);

    private int mResId;

    Score(int resId) {
        mResId = resId;
    }

    public int getResId() {
        return mResId;
    }

    public static Score[] getMentalScores() {
        return new Score[]{WILL, WITS, GRACE};
    }

    public static Score[] getMartialScores() {
        return new Score[]{MIGHT, SKILL};
    }
}
