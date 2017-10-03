package com.example.cis.mazeminotaurs.character.stats;

/**
 * Created by jusmith on 3/30/17.
 */

public enum Score {
    MIGHT,
    GRACE,
    SKILL,
    WILL,
    WITS,
    LUCK;

    public Score[] getMentalScores() {
        return new Score[]{WILL, WITS, GRACE};
    }

    public Score[] getMartialScores() {
        return new Score[]{MIGHT, SKILL};
    }
}
