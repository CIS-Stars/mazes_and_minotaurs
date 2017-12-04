package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;

import java.util.Arrays;


/**
 * Created by jusmith on 3/31/17.
 */

public abstract class Warrior extends BaseClass {
    private Weapon mWeaponOfChoice;
    private Weapon[] mPossibleWeaponsOfChoice;

    private static final int[] sLevelDescriptions = {R.string.warrior_one, R.string.warrior_two,
                                                     R.string.warrior_three, R.string.warrior_four,
                                                     R.string.warrior_five, R.string.warrior_six};

    protected Warrior() {
        setHitsImprove(4);
    }

    public static int[] getLevelDescriptions() {
        return sLevelDescriptions;
    }


    public Weapon getWeaponOfChoice() {
        return this.mWeaponOfChoice;
    }

    public void setWeaponOfChoice(Weapon weaponOfChoice) {
        if (Arrays.asList(this.mPossibleWeaponsOfChoice).contains(weaponOfChoice)) {
            this.mWeaponOfChoice = weaponOfChoice;
        } else {
            this.mWeaponOfChoice = this.mPossibleWeaponsOfChoice[0];
        }
    }

    public Weapon[] getPossibleWeaponsOfChoice() {
        return this.mPossibleWeaponsOfChoice;
    }

    public void setPossibleWeaponsOfChoice(Weapon[] possibleWeaponsOfChoice) {
        this.mPossibleWeaponsOfChoice = possibleWeaponsOfChoice;
    }

    @Override
    public int getLevelDescription() {
        updateLevel();
        return sLevelDescriptions[getLevel() - 1];
    }
}
