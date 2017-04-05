package com.example.cis.mazeminotaurs.character;

import android.util.Log;

import com.example.cis.mazeminotaurs.Armor;
import com.example.cis.mazeminotaurs.AttributeScore;
import com.example.cis.mazeminotaurs.AttributeScoreGenerator;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.classes.Barbarian;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.example.cis.mazeminotaurs.character.stats.Score;

import java.util.HashMap;

/**
 * Created by jusmith on 3/30/17.
 */

public class PlayerCharacter {
    private final String TAG = "PlayerCharacter Class";

    private HashMap<Score, AttributeScore> mCoreStats = new HashMap<>();
    private BaseClass mCharClass;
    private Gender mGender;
    private int mAge;
    private String mName;
    private Armor mHelmet;
    private Armor mBreastplate;
    private Armor mShield;

    public PlayerCharacter() {
        Log.i(TAG, "Creating PlayerCharacter");
        setAge(0);
        setCharClass(new Barbarian(this, R.string.barb_axe, R.string.bow));
        setName("Thorin");
        
        AttributeScore[] scores = new AttributeScoreGenerator().nextValidSet();
        for (int i = 0; i < scores.length; i++) {
            mCoreStats.put(Score.values()[i], scores[i]);
        }
    }

    public int getMeleeMod() {
        return getCoreStatScore(Score.MIGHT).getModifier() +
                getCoreStatScore(Score.GRACE).getModifier() +
                getCoreStatScore(Score.LUCK).getModifier();
    }

    public int getMissleMod() {
        return getCoreStatScore(Score.SKILL).getModifier() +
                getCoreStatScore(Score.WITS).getModifier() +
                getCoreStatScore(Score.LUCK).getModifier();
    }

    public int getInititive() {
        return 10 + getCoreStatScore(Score.SKILL).getModifier() +
                getCoreStatScore(Score.WITS).getModifier();
    }

    public int getDC() {
        return 12 + getCoreStatScore(Score.LUCK).getModifier();
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
        return getCoreStatScore(Score.WILL).getModifier() +
                getCoreStatScore(Score.GRACE).getModifier() +
                getCoreStatScore(Score.LUCK).getModifier();
    }

    public AttributeScore getCoreStatScore(Score scoreStat) {
        return mCoreStats.get(scoreStat);
    }

    public HashMap<Score, AttributeScore> getCoreStats() {
        return mCoreStats;
    }

    public void setCoreStats(HashMap<Score, AttributeScore> coreStats) {
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
