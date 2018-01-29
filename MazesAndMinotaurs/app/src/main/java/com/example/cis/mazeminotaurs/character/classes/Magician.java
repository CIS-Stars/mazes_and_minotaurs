package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.stats.Score;

/**
 * The superclass of all Magician classes in the game.
 *
 * Created by jusmith on 4/5/17.
 */

public abstract class Magician extends BaseClass {
    /**
     * The resource id of the special talent.
     */
    private int mSpecialTalentResId;

    /**
     * This is an array of all of the level descriptions of the class type.
     */
    private static final int[] mLevelDescriptions = {R.string.magic_one, R.string.magic_two,
            R.string.magic_three, R.string.magic_four,
            R.string.magic_five, R.string.magic_six};

    /**
     * Get the value of the special talent of the class
     *
     * @return the sum of the character's primary attributes' modifiers.
     */
    public int getSpecialTalent(){
        int total = 0;
        for (Score score: getPrimaryAttributes()) {
            total += getCharacter().getScore(score).getModifier();
        }
        return total;
    }

    /**
     * Getter for the mSpecialTalentResId property
     * @return the value of mSpecialScoreId
     */
    public int getSpecialTalentResId() {
        return mSpecialTalentResId;
    }

    /**
     * Setter for the mSpecialTalentResId property
     * @param specialTalentResId the new value of mSpecialScoreId
     */
    public void setSpecialTalentResId(int specialTalentResId) {
        mSpecialTalentResId = specialTalentResId;
    }

    /**
     * Gets the level description corresponding to the current level of the class.
     * @return resource id of the current level description
     */
    @Override
    public int getLevelDescription() {
        updateLevel();
        return mLevelDescriptions[getLevel() - 1];
    }

    /**
     * Calculated value of their mystical strength
     *
     * @return value of mystical strength.
     */
    public int getMysticalStrength(){
        return 12 + getSpecialTalent();
    }

    /**
     * The available power points of the class.
     * @return number of power points available to the class
     */
    public abstract int getPowerPoints();
}

