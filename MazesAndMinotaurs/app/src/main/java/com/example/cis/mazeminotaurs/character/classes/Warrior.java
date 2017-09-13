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

    private static final int[] mLevelDescriptions = {R.string.warrior_one, R.string.warrior_two,
                                                     R.string.warrior_three, R.string.warrior_four,
                                                     R.string.warrior_five, R.string.warrior_six};

    public Weapon getWeaponOfChoice() {
        return mWeaponOfChoice;
    }

    public void setWeaponOfChoice(Weapon weaponOfChoice) {
        if (Arrays.asList(mPossibleWeaponsOfChoice).contains(weaponOfChoice)) {
            mWeaponOfChoice = weaponOfChoice;
        } else {
            System.out.println("Invalid assignment of weaponOfChoice. Assigning default.");
            mWeaponOfChoice = mPossibleWeaponsOfChoice[0];
        }
    }

    @Override
    public int getLevelDescription() {
        updateLevel();
        return mLevelDescriptions[getLevel() - 1];
    }
}
