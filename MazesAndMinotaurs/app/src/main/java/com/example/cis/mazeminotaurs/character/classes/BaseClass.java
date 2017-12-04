package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.AttributeScore;
import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.stats.Score;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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

    private Score[] mPossibleLevelScores;
    private Weapon[] mPossibleStartWeapons;

    private ArrayList<HashMap<Score, Integer>> mScoreLevelChoice = new ArrayList<>();

    private static final int LUCK_IMPROVE = 1;

    public void doLevelUp(Score score) {
        if (getLevel() >= getEffectiveLevel() ||
                !Arrays.asList(getPossibleLevelScores()).contains(score)) return;

        // Initialization
        HashMap<Score, Integer> levelData = new HashMap<>();
        AttributeScore selectedScore = getCharacter().getScore(score);

        // Save the scores' values into a hash map for reverting
        levelData.put(Score.LUCK, getCharacter().getScore(Score.LUCK).getScore());
        levelData.put(score, selectedScore.getScore());
        getScoreLevelChoice().add(levelData);

        // Improve the scores
        getCharacter().addToScore(score, 2, true);
        getCharacter().addToScore(Score.LUCK, LUCK_IMPROVE, true);
        setAddedHits(getAddedHits() + getHitsImprove());

        // Wrapping up
        setLevel(getLevel() + 1);
        getCharacter().validateScores();
    }

    public void doLevelDown() {
        if (getLevel() > 1) {
            HashMap<Score, Integer> levelData = getScoreLevelChoice().remove(getScoreLevelChoice().size() - 1);
            Score lastSelectedScore = null;
            for (Object rawScore : levelData.keySet().toArray()) {
                Score score = (Score) rawScore;
                if (score != Score.LUCK) {
                    lastSelectedScore = score;
                    break;
                }
            }

            AttributeScore luck = getCharacter().getScore(Score.LUCK);
            AttributeScore lastScoreLeveled = getCharacter().getScore(lastSelectedScore);

            setAddedHits(getAddedHits() - getHitsImprove());
            luck.setScore(levelData.get(Score.LUCK));
            lastScoreLeveled.setScore(levelData.get(lastSelectedScore));
            setLevel(getLevel() - 1);
        }
    }

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

    public ArrayList<HashMap<Score, Integer>> getScoreLevelChoice() {
        return mScoreLevelChoice;
    }

    public void setScoreLevelChoice(ArrayList<HashMap<Score, Integer>> scoreLevelChoice) {
        mScoreLevelChoice = scoreLevelChoice;
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

    public Score[] getPossibleLevelScores() {
        return mPossibleLevelScores;
    }

    public void setPossibleLevelScores(Score[] possibleLevelScores) {
        mPossibleLevelScores = possibleLevelScores;
    }

    // Dummy Method
    // Returns the ResId of the level descriptions
    public abstract int getLevelDescription();
}
