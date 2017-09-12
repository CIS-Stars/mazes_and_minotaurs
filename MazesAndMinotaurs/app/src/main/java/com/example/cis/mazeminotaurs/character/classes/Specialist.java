package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.stats.Score;

/**
 * Created by jusmith on 3/31/17.
 */

public abstract class Specialist extends BaseClass {
    private int mSpecialScoreId;
    private Weapon mWeaponOfChoice;

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
}
