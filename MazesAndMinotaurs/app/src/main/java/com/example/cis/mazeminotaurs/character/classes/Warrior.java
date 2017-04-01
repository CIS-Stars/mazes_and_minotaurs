package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.R;


/**
 * Created by jusmith on 3/31/17.
 */

public abstract class Warrior extends BaseClass {
    private int mWeaponOfChoice;
    private static final int[] mLevelDescriptions = {R.string.warrior_one, R.string.warrior_two,
                                                     R.string.warrior_three, R.string.warrior_four,
                                                     R.string.warrior_five, R.string.warrior_six};

    public int getWeaponOfChoice() {
        return mWeaponOfChoice;
    }

    public void setWeaponOfChoice(int weaponOfChoice) {
        mWeaponOfChoice = weaponOfChoice;
    }

    @Override
    public int getLevelDescription() {
        updateLevel();
        return mLevelDescriptions[getLevel() - 1];
    }
}
