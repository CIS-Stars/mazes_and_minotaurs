package com.example.cis.mazeminotaurs.character;

import com.example.cis.mazeminotaurs.Armor;
import com.example.cis.mazeminotaurs.AttributeScoreGenerator;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.classes.Barbarian;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.example.cis.mazeminotaurs.character.stats.Score;

import java.util.HashMap;

/**
 * Created by jusmith on 3/30/17.
 */

public class Character {
    private HashMap<Score, Integer> mCoreStats;
    private BaseClass mCharClass;
    private Gender mGender;
    private int mAge;
    private String mName;
    private Armor mHelmet;
    private Armor mBreastplate;
    private Armor mShield;

    public Character() {
        mAge = 0;
        setCharClass(new Barbarian(this, R.string.barb_axe, R.string.bow));
        for (Score scoreStat : Score.values()) {
            mCoreStats.put(scoreStat, 0);
        }
        mName = "Thorin";
    }

    public int getMod(Score scoreStat) {
        int statValue = mCoreStats.get(scoreStat);
        if (statValue <= 2) {
            return -4;
        } else if (statValue <= 4) {
            return -3;
        } else if (statValue <= 6) {
            return -2;
        } else if (statValue <= 8) {
            return -1;
        } else if (statValue <= 12) {
            return 0;
        } else if (statValue <= 14) {
            return 1;
        } else if (statValue <= 16) {
            return 2;
        } else if (statValue <= 18) {
            return 3;
        } else {
            return 4;
        }
    }

    public int getMeleeMod() {
        return getMod(Score.MIGHT) + getMod(Score.GRACE) + getMod(Score.LUCK);
    }

    public int getMissleMod() {
        return getMod(Score.SKILL) + getMod(Score.WITS) + getMod(Score.LUCK);
    }

    public int getInititive() {
        return 10 + getMod(Score.SKILL) + getMod(Score.WITS);
    }

    public int getDC() {
        return 12 + getMod(Score.LUCK);
    }

    public int getEDC() {
        int armorBonus = 0;
        if (mHelmet != null) {
            armorBonus += 2;
        }
        if (mBreastplate != null) {
            armorBonus += 2;
        }
        if (mShield != null) {
            armorBonus += 2;
        }

        return getDC() + armorBonus;
    }

    public int getHitTotal(){
        //return mCharClass.getHits() + getMod(Score.MIGHT);
        return 0;
    }

    public int getCharisma(){
        return getMod(Score.WILL) + getMod(Score.GRACE) + getMod(Score.LUCK);
    }

    public int getCoreStatScore(Score scoreStat) {
        return mCoreStats.get(scoreStat);
    }

    public HashMap<Score, Integer> getCoreStats() {
        return mCoreStats;
    }

    public void setCoreStats(HashMap<Score, Integer> coreStats) {
        mCoreStats = coreStats;
    }

    public BaseClass getCharClass() {
        return mCharClass;
    }

    public void setCharClass(BaseClass aClass) {
        mCharClass = aClass;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
