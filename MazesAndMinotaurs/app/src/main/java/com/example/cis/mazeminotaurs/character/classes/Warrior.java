package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;

import java.util.Arrays;


/**
 * The superclass of all Warrior game classes.
 * @author jusmith on 3/31/17.
 */

public abstract class Warrior extends BaseClass {
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
    private static final int[] sLevelDescriptions = {R.string.warrior_one, R.string.warrior_two,
            R.string.warrior_three, R.string.warrior_four,
            R.string.warrior_five, R.string.warrior_six};

    /**
     * Protected Constructor. Only meant to set a private property.
     */
    protected Warrior() {
        setHitsImprove(4);
    }

    /**
     * Getter for the sLevelDescriptions property.
     *
     * @return the value of sLevelDescriptions
     */
    public static int[] getLevelDescriptions() {
        return sLevelDescriptions;
    }


    /**
     * Getter of the mWeaponOfChoice property
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
     * Gets the level description corresponding to the current level of the class.
     * @return resource id of the current level description
     */
    @Override
    public int getLevelDescription() {
        updateLevel();
        return sLevelDescriptions[getLevel() - 1];
    }
}
