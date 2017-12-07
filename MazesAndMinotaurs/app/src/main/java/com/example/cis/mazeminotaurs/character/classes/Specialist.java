package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.stats.Score;

import java.util.Arrays;

/**
 * The superclass of both Specialist classes in the game.
 *
 * @author jusmith on 3/31/17.
 */

public abstract class Specialist extends BaseClass {
    /**
     * The resource id of the special score.
     */
    private int mSpecialScoreId;

    /**
     * The current weapon of choice for the class.
     */
    private Weapon mWeaponOfChoice;

    /**
     * The array of valid values for mWeaponOfChoice.
     */
    private Weapon[] mPossibleWeaponsOfChoice;

    /**
     * This is an array of all of the level descriptions of the class type.
     */
    private static final int[] mLevelDescriptions = {R.string.special_one, R.string.special_two,
            R.string.special_three, R.string.special_four,
            R.string.special_five, R.string.special_six};

    /**
     * Getter of the mWeaponOfChoice property
     *
     * @return the value of mWeaponOfChoice
     */
    public Weapon getWeaponOfChoice() {
        return this.mWeaponOfChoice;
    }

    /**
     * Setter of the mWeaponOfChoice property.
     * Safe-checks, if weaponOfChoice is not found in mPossibleWeaponsOfChoice,
     * it will automatically be set to the first item in mPossibleWeaponsOfChoice.
     * @param weaponOfChoice the possible new value of mWeaponOfChoice
     */
    public void setWeaponOfChoice(Weapon weaponOfChoice) {
        if (Arrays.asList(this.mPossibleWeaponsOfChoice).contains(weaponOfChoice)) {
            this.mWeaponOfChoice = weaponOfChoice;
        } else {
            this.mWeaponOfChoice = this.mPossibleWeaponsOfChoice[0];
        }
    }

    /**
     * Getter of the mPossibleWeaponsOfChoice property.
     * @return the value of mPossibleWeaponsOfChoice
     */
    public Weapon[] getPossibleWeaponsOfChoice() {
        return this.mPossibleWeaponsOfChoice;
    }

    /**
     * Setting for the mPossibleWeaponsOfChoice property.
     * @param possibleWeaponsOfChoice the new value of mPossibleWeaponsOfChoice
     */
    public void setPossibleWeaponsOfChoice(Weapon[] possibleWeaponsOfChoice) {
        this.mPossibleWeaponsOfChoice = possibleWeaponsOfChoice;
    }

    /**
     * Getter for the mSpecialScoreId property
     * @return the value of mSpecialScoreId
     */
    public int getSpecialScoreId() { return this.mSpecialScoreId;
    }

    /**
     * Setter for the mSpecialScoreId property
     * @param specialScoreId the new value of mSpecialScoreId
     */
    public void setSpecialScoreId(int specialScoreId) {
        this.mSpecialScoreId = specialScoreId;
    }

    /**
     * Gets the value of the special talent
     * @return The sum of the character's primary attributes' modifiers.
     */
    public int getSpecialTalent(){
        int total = 0;
        for (Score score: getPrimaryAttributes()) {
            total += getCharacter().getScore(score).getModifier();
        }
        return total;
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
}
