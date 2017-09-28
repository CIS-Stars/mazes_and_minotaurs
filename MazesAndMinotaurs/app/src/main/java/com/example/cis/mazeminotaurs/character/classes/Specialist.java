package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.stats.Score;

import java.util.Arrays;

/**
 * Created by jusmith on 3/31/17.
 */

public abstract class Specialist extends BaseClass {
    private int mSpecialScoreId;
    private Weapon mWeaponOfChoice;
    private Weapon[] mPossibleWeaponsOfChoice;

    private static final int[] mLevelDescriptions = {R.string.special_one, R.string.special_two,
            R.string.special_three, R.string.special_four,
            R.string.special_five, R.string.special_six};

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

    public int getSpecialScoreId() { return this.mSpecialScoreId; }

    public void setSpecialScoreId(int specialScoreId) {
        this.mSpecialScoreId = specialScoreId;
    }
    
    public int getSpecialTalent(){
        int total = 0;
        for (Score score: getPrimaryAttributes()) {
            total += getCharacter().getScore(score).getModifier();
        }
        return total;
    }
    
    @Override
    public int getLevelDescription() {
        updateLevel();
        return mLevelDescriptions[getLevel() - 1];
    }
}
