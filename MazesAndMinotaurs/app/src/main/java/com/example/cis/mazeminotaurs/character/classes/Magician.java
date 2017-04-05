package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.character.stats.Score;

/**
 * Created by jusmith on 4/5/17.
 */

public abstract class Magician extends BaseClass {
    private int mSpecialTalentResId;

    public int getSpecialTalent(){
        int total = 0;
        for (Score score: getPrimaryAttributes()) {
            total += getCharacter().getCoreStatScore(score).getModifier();
        }
        return total;
    }

    public int getMysticalStrength(){
        return 12 + getSpecialTalent();
    }
}