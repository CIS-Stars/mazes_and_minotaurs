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
 * This is the abstract base layer of every class for the character in the game.
 *
 * @author jusmith on 3/31/17.
 */

public abstract class BaseClass implements Serializable{
    /**
     * Represents how many hits gained either through effects or level up.
     */
    private int mAddedHits = 0;

    /**
     * Represents how many hits they have gained from their class, defined by the game rules.
     */
    private int mBasicHits;

    /**
     * Represents how many hits to add on level up.
     */
    private int mHitsImprove = 2;

    /**
     * The character that this class is linked to.
     */
    private PlayerCharacter mPlayerCharacter;

    /**
     * The amount of experience gained.
     */
    private int mExperience = 0;

    /**
     * The level of the character linked.
     */
    private int mLevel = 1;

    /**
     * The level that the character should be.
     * Basically, in order to prevent the issue of automatically leveling up,
     * the values are kept separate.
     */
    private int mEffectiveLevel = 1;

    /**
     * The attributes that are given higher priority, decided by the game rules.
     */
    private ArrayList<Score> mPrimaryAttributes;

    /**
     * The gender that the player must be in order to select this class.
     */
    private Gender mRequiredGender;

    /**
     * The resource id of this class' name.
     */
    private int mResId;

    /**
     * The gear that is given on game start to the player.
     */
    private ArrayList<Equipment> mStartGear;

    /**
     * The money that is given on game start to the player.
     */
    private int mStartMoney;

    /**
     * The scores that can gain points on level up.
     */
    private Score[] mPossibleLevelScores;

    /**
     * The weapons that the player can start with.
     */
    private Weapon[] mPossibleStartWeapons;

    /**
     * 'Captures' the previous values of scores before level up.
     * Shouldn't be touched unless for the {@code BaseClass.doLevelDown} method.
     */
    private ArrayList<HashMap<Score, Integer>> mScoreLevelChoice = new ArrayList<>();

    /**
     * The amount of points to improve the luck score on level up.
     */
    public static final int LUCK_IMPROVE = 1;

    /**
     * Levels up the character and increases their scores automatically.
     * If {@code score} is not in {@code mPossibleLevelScores}, the method won't run.
     * If the player is not at the right amount of experience to level up,
     * the method won't run.
     *
     * @param score the user desired score to improve.
     */
    public void doLevelUp(Score score) {
        // TODO This should throw an exception.
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

    /**
     * Reverts the player down to their previous level. This also reverts scores.
     * This method isn't used in the code, but is useful for testing.
     */
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

    /**
     * Updates mEffectiveLevel's value according to the experience table.
     */
    public void updateLevel(){
        if (getExperience() < 1000) {
            setEffectiveLevel(1);
        } else if (getExperience() < 2000) {
            setEffectiveLevel(2);
        } else if (getExperience() < 4000) {
            setEffectiveLevel(3);
        } else if (getExperience() < 7000) {
            setEffectiveLevel(4);
        } else if (getExperience() < 10000) {
            setEffectiveLevel(5);
        } else if (getExperience() > 9999) {
            setEffectiveLevel(6);
        }
    }

    /**
     * Getter for mAddedHits.
     *
     * @return the value of mAddedHits.
     */
    public int getAddedHits() {
        return this.mAddedHits;
    }

    /**
     * Setter for mAddedHits.
     * @param addedHits the new value of mAddedHits.
     */
    public void setAddedHits(int addedHits) {
        this.mAddedHits = addedHits;
    }

    /**
     * Getter for mBasicHits.
     * @return the value of mBasicHits.
     */
    public int getBasicHits() {
        return this.mBasicHits;
    }

    /**
     * Setter for mBasicHits.
     * @param basicHits the new value of mBasicHits.
     */
    public void setBasicHits(int basicHits) {
        this.mBasicHits = basicHits;
    }

    /**
     * Getter for mPlayerCharacter.
     * @return the value of mPlayerCharacter.
     */
    public PlayerCharacter getCharacter() {
        return this.mPlayerCharacter;
    }

    /**
     * Setter for mPlayerCharacter.
     * @param playerCharacter the new value of mPlayerCharacter.
     */
    public void setCharacter(PlayerCharacter playerCharacter) {
        this.mPlayerCharacter = playerCharacter;
    }

    /**
     * Getter for mPrimaryAttributes.
     * @return the value of mPrimaryAttributes.
     */
    public ArrayList<Score> getPrimaryAttributes() {
        return this.mPrimaryAttributes;
    }

    /**
     * Setter for mPrimaryAttributes.
     * @param primaryAttributes the new value for mPrimaryAttributes.
     */
    public void setPrimaryAttributes(ArrayList<Score> primaryAttributes) {
        this.mPrimaryAttributes = primaryAttributes;
    }

    /**
     * Getter for mRequiredGender.
     * @return the value of mRequiredGender.
     */
    public Gender getRequiredGender() {
        return this.mRequiredGender;
    }

    /**
     * Setter for mRequiredGender.
     * @param requiredGender the new value of mRequiredGender.
     */
    public void setRequiredGender(Gender requiredGender) {
        this.mRequiredGender = requiredGender;
    }

    /**
     * Getter for mResId.
     * @return the value of mResId.
     */
    public int getResId() {
        return this.mResId;
    }

    /**
     * Setter for mResId.
     * @param resId the new value of mResId.
     */
    public void setResId(int resId) {
        this.mResId = resId;
    }

    /**
     * Getter for mStartGear.
     * @return the value of mStartGear.
     */
    public ArrayList<Equipment> getStartGear() {
        return this.mStartGear;
    }

    /**
     * Setter for mStartGear.
     * @param startGear the new value of mStartGear.
     */
    public void setStartGear(ArrayList<Equipment> startGear) {
        this.mStartGear = startGear;
    }

    /**
     * Getter for mStartMoney.
     * @return the value of mStartMoney.
     */
    public int getStartMoney() {
        return this.mStartMoney;
    }

    /**
     * Setter for mStartMoney.
     * @param startMoney the new value of mStartMoney.
     */
    public void setStartMoney(int startMoney) {
        this.mStartMoney = startMoney;
    }

    /**
     * Helper method for adding on to the value of mExperience.
     * @param experience the amount of experience to add.
     */
    public void addExperience(int experience) {
        // TODO this should probably have safety caps to stop exceptions.
        this.mExperience += experience;
    }

    /**
     * Getter for mExperience.
     * @return the value of mExperience.
     */
    public int getExperience() {
        return this.mExperience;
    }

    /**
     * Setter for mExperience.
     * @param experience the new value of mExperience.
     */
    public void setExperience(int experience) {
        this.mExperience = experience;
    }

    /**
     * Getter for mLevel.
     * @return the value of mLevel.
     */
    public int getLevel() {
        return this.mLevel;
    }

    /**
     * Setter for mLevel.
     * @param level the new value of mLevel.
     */
    public void setLevel(int level) {
        this.mLevel = level;
    }

    /**
     * Getter for mEffectiveLevel.
     * @return the value of mEffectiveLevel.
     */
    public int getEffectiveLevel() {
        return this.mEffectiveLevel;
    }

    /**
     * Setter for mEffectiveLevel.
     * @param effectiveLevel the new value of mEffectiveLevel.
     */
    public void setEffectiveLevel(int effectiveLevel) {
        this.mEffectiveLevel = effectiveLevel;
    }

    /**
     * Getter for mScoreLevelChoice.
     * @return the value of mScoreLevelChoice.
     */
    public ArrayList<HashMap<Score, Integer>> getScoreLevelChoice() {
        return mScoreLevelChoice;
    }

    public void setScoreLevelChoice(ArrayList<HashMap<Score, Integer>> scoreLevelChoice) {
        mScoreLevelChoice = scoreLevelChoice;
    }

    /**
     * Getter for mHitsImprove.
     * @return the value of mHitsImprove.
     */
    public int getHitsImprove() {
        return this.mHitsImprove;
    }

    protected void setHitsImprove(int hitsImprove) {
        this.mHitsImprove = hitsImprove;
    }

    /**
     * Getter for mPossibleStartWeapons.
     * @return the value of mPossibleStartWeapons.
     */
    public Weapon[] getPossibleStartWeapons() {
        return this.mPossibleStartWeapons;
    }

    /**
     * Setter for mPossibleStartWeapons.
     * @param possibleStartWeapons the new value of mPossibleStartWeapons.
     */
    public void setPossibleStartWeapons(Weapon[] possibleStartWeapons) {
        this.mPossibleStartWeapons = possibleStartWeapons;
    }

    /**
     * Getter for mPossibleLevelScores.
     * @return the value of mPossibleLevelScores.
     */
    public Score[] getPossibleLevelScores() {
        return mPossibleLevelScores;
    }

    /**
     * Setter for mPossibleLevelScores.
     * @param possibleLevelScores the new value of mPossibleLevelScores.
     */
    public void setPossibleLevelScores(Score[] possibleLevelScores) {
        mPossibleLevelScores = possibleLevelScores;
    }

    /**
     * Gets the level description corresponding to the current level of the class.
     * @return resource id of the current level description
     */
    public abstract int getLevelDescription();
}
