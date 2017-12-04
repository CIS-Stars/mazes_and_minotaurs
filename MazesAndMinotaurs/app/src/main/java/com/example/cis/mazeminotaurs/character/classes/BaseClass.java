package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.stats.Score;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jusmith on 3/31/17.
 *
 * Level is the actual level of a character
 * EffectiveLevel is what level the character should be
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

public abstract class BaseClass implements Serializable{
    private int mAddedHits = 0;
    private int mBasicHits;
    private int mHitsImprove = 2;
    private PlayerCharacter mPlayerCharacter;
    private int mExperience = 0;
    private int mLevel = 1;
    private int mEffectiveLevel = 1;
    private ArrayList<Score> mPrimaryAttributes;
    private Gender mRequiredGender;
    private int mResId;
    private ArrayList<Equipment> mStartGear;
    private int mStartMoney;

    private Weapon[] mPossibleStartWeapons;

    private static final int LUCK_IMPROVE = 1;

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
        return this.mAddedHits;
    }

    public void setAddedHits(int addedHits) {
        this.mAddedHits = addedHits;
    }

    public int getBasicHits() {
        return this.mBasicHits;
    }

    public void setBasicHits(int basicHits) {
        this.mBasicHits = basicHits;
    }

    public PlayerCharacter getCharacter() {
        return this.mPlayerCharacter;
    }

    public void setCharacter(PlayerCharacter playerCharacter) {
        this.mPlayerCharacter = playerCharacter;
    }

    public ArrayList<Score> getPrimaryAttributes() {
        return this.mPrimaryAttributes;
    }

    public void setPrimaryAttributes(ArrayList<Score> primaryAttributes) {
        this.mPrimaryAttributes = primaryAttributes;
    }

    public Gender getRequiredGender() {
        return this.mRequiredGender;
    }

    public void setRequiredGender(Gender requiredGender) {
        this.mRequiredGender = requiredGender;
    }

    public int getResId() {
        return this.mResId;
    }

    public void setResId(int resId) {
        this.mResId = resId;
    }

    public ArrayList<Equipment> getStartGear() {
        return this.mStartGear;
    }

    public void setStartGear(ArrayList<Equipment> startGear) {
        this.mStartGear = startGear;
    }

    public int getStartMoney() {
        return this.mStartMoney;
    }

    public void setStartMoney(int startMoney) {
        this.mStartMoney = startMoney;
    }

    public int getExperience() {
        return this.mExperience;
    }

    public void setExperience(int experience) {
        this.mExperience = experience;
    }

    public int getLevel() {
        return this.mLevel;
    }

    public void setLevel(int level) {
        this.mLevel = level;
    }

    public int getEffectiveLevel() {
        return this.mEffectiveLevel;
    }

    public void setEffectiveLevel(int effectiveLevel) {
        this.mEffectiveLevel = effectiveLevel;
    }

    public int getHitsImprove() {
        return this.mHitsImprove;
    }

    protected void setHitsImprove(int hitsImprove) {
        this.mHitsImprove = hitsImprove;
    }

    public Weapon[] getPossibleStartWeapons() {
        return this.mPossibleStartWeapons;
    }

    public void setPossibleStartWeapons(Weapon[] possibleStartWeapons) {
        this.mPossibleStartWeapons = possibleStartWeapons;
    }

    // Dummy Method
    // Returns the ResId of the level descriptions
    public abstract int getLevelDescription();
}
