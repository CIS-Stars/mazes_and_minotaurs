package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.character.Character;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.stats.Score;

import java.util.ArrayList;

/**
 * Created by jusmith on 3/31/17.
 *
 * Level is the actual of a character
 * EffectiveLevel is what level the character will be after handling leveling up
 *
 * Example of Usage:
 *   Assume:
 *     mLevel = 1;
 *     mExperience = 2000;
 *     mEffectiveLevel = 3;
 *   After calling doLevelUp
 *      mLevel = 2;
 *      mExperience = 2000;
 *      mEffectiveLevel = 3;
 *   After calling doLevelUp
 *      mLevel = 3;
 *      mExperience = 2000;
 *      mEffectiveLevel = 3;
 */

public abstract class BaseClass {
    private int mAddedHits = 0;
    private int mBasicHits;
    private Character mCharacter;
    private int mExperience = 0;
    private int mLevel = 1;
    private int mEffectiveLevel = 1;
    private Score[] mPrimaryAttributes;
    private Gender mRequiredGender;
    private int mResId;
    private ArrayList<Equipment> mStartGear;
    private int mStartGold;

    public void updateLevel(){
        if (getExperience() < 1000) {
            setEffectiveLevel(1);
        }
        if (getExperience() < 2000) {
            setEffectiveLevel(2);
        }
        if (getExperience() < 4000) {
            setEffectiveLevel(3);
        }
        if (getExperience() < 7000) {
            setEffectiveLevel(4);
        }
        if (getExperience() < 10000) {
            setEffectiveLevel(5);
        }
        if (getExperience() > 9999) {
            setEffectiveLevel(6);
        }
    }

    public int getAddedHits() {
        return mAddedHits;
    }

    public void setAddedHits(int addedHits) {
        mAddedHits = addedHits;
    }

    public int getBasicHits() {
        return mBasicHits;
    }

    public void setBasicHits(int basicHits) {
        mBasicHits = basicHits;
    }

    public Character getCharacter() {
        return mCharacter;
    }

    public void setCharacter(Character character) {
        mCharacter = character;
    }

    public Score[] getPrimaryAttributes() {
        return mPrimaryAttributes;
    }

    public void setPrimaryAttributes(Score[] primaryAttributes) {
        mPrimaryAttributes = primaryAttributes;
    }

    public Gender getRequiredGender() {
        return mRequiredGender;
    }

    public void setRequiredGender(Gender requiredGender) {
        mRequiredGender = requiredGender;
    }

    public int getResId() {
        return mResId;
    }

    public void setResId(int resId) {
        mResId = resId;
    }

    public ArrayList<Equipment> getStartGear() {
        return mStartGear;
    }

    public void setStartGear(ArrayList<Equipment> startGear) {
        mStartGear = startGear;
    }

    public int getStartGold() {
        return mStartGold;
    }

    public void setStartGold(int startGold) {
        mStartGold = startGold;
    }

    public int getExperience() {
        return mExperience;
    }

    public void setExperience(int experience) {
        mExperience = experience;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    public int getEffectiveLevel() {
        return mEffectiveLevel;
    }

    public void setEffectiveLevel(int effectiveLevel) {
        mEffectiveLevel = effectiveLevel;
    }

    // Dummy Method
    // Returns the ResId of the level descriptions
    public int getLevelDescription() {
        return -12321;
    }

    // Dummy Methods
    public void doLevelUp() {}
    public void doLevelDown() {}
}
