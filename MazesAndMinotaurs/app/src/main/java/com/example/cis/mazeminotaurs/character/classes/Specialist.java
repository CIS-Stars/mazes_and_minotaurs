package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.stats.Score;

/**
 * Created by jusmith on 3/31/17.
 */

public abstract class Specialist extends BaseClass {
    private int mSpecialScoreId;
    private Weapon mWeaponOfChoice;
    private static final int[] mLevelDescriptions = {R.string.special_one, R.string.special_two,
            R.string.special_three, R.string.special_four,
            R.string.special_five, R.string.special_six};

    public Weapon getWeaponOfChoice() {
        return mWeaponOfChoice;
    }

    public void setWeaponOfChoice(Weapon weaponOfChoice) {
        mWeaponOfChoice = weaponOfChoice;
    }

    public int getSpecialScoreId() { return mSpecialScoreId; }

    public void setSpecialScoreId(int specialScoreId) {
        mSpecialScoreId = specialScoreId;
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
