package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.stats.Score;

/**
 * Created by jusmith on 4/5/17.
 */

public abstract class Magician extends BaseClass {
    private int mSpecialTalentResId;

    private static final int[] mLevelDescriptions = {R.string.magic_one, R.string.magic_two,
            R.string.magic_three, R.string.magic_four,
            R.string.magic_five, R.string.magic_six};

    public int getSpecialTalent(){
        int total = 0;
        for (Score score: getPrimaryAttributes()) {
            total += getCharacter().getScore(score).getModifier();
        }
        return total;
    }

    public int getSpecialTalentResId() {
        return mSpecialTalentResId;
    }

    public void setSpecialTalentResId(int specialTalentResId) {
        mSpecialTalentResId = specialTalentResId;
    }

    @Override
    public int getLevelDescription() {
        updateLevel();
        return mLevelDescriptions[getLevel() - 1];
    }

    public int getMysticalStrength(){
        return 12 + getSpecialTalent();
    }

    public abstract int getPowerPoints();
}

