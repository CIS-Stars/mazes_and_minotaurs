package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.AttributeScore;
import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.rollDice.rollDice;
import com.example.cis.mazeminotaurs.util.CommonStrings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by jusmith on 5/15/17.
 */

public class Noble extends Warrior {
    private ArrayList<HashMap<Score, Integer>> mScoreLevelChoice = new ArrayList<>();

    private Score mPhysicalHeritage = Score.MIGHT;
    private Score mOtherHeritage = Score.WITS;
    private boolean mHasHeritage = false;

    public Noble() {
        this(null,null,null,null);
    }

    public Noble(PlayerCharacter playerCharacter, Weapon weaponOfChoice, Score martialHeritage, Score mentalHeritage) {
        setPossibleStartWeapons(new Weapon[]{});
        setPossibleWeaponsOfChoice(new Weapon[]{
                EquipmentDB.getInstance().getWeapon(CommonStrings.BOW.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.JAVELIN.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SPEAR.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SWORD.getValue()),
        });

        // Noble-Specific things
        mPhysicalHeritage = martialHeritage;
        mOtherHeritage = mentalHeritage;

        Score[] primAttrs = {mPhysicalHeritage, Score.LUCK};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        // Check if the weapon of choice is valid
        setWeaponOfChoice(weaponOfChoice);

        int rolledGold = rollDice.roll(6, 3) * 100;

        startGear.add(equipmentDB.getWeapon(CommonStrings.SWORD.getValue()));
        startGear.add(equipmentDB.getWeapon(CommonStrings.DAGGER.getValue()));
        startGear.add(equipmentDB.getArmor(CommonStrings.SHIELD.getValue()));
        startGear.add(equipmentDB.getArmor(CommonStrings.HELMET.getValue()));
        startGear.add(equipmentDB.getArmor(CommonStrings.BREASTPLATE.getValue()));

        setBasicHits(12);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.EITHER);
        setResId(Classes.NOBLE.getResId());
        setStartMoney(rolledGold);
        setStartGear(startGear);

        if (getCharacter() != null) {
            doHeritage();
        }
    }

    public void doLevelUp() {
        Score[] possibleScores = {Score.GRACE, Score.SKILL, Score.WILL, Score.MIGHT, Score.WITS};
        doLevelUp(possibleScores[rollDice.roll(possibleScores.length) - 1]);
    }

    public void doLevelUp(Score score) {
        if (getLevel() < getEffectiveLevel()) {
            Score[] choices = {Score.GRACE, Score.SKILL, Score.WILL, Score.MIGHT, Score.WITS};
            ArrayList<Score> possibleScores = new ArrayList<>();
            for (Score selectScore : choices) {
                if (getCharacter().canAddToScore(selectScore)) {
                    possibleScores.add(selectScore);
                }
            }

            Score selectedScore;
            if (possibleScores.contains(score)) {
                selectedScore = score;
            } else {
                selectedScore = possibleScores.get(rollDice.roll(possibleScores.size()) - 1);
            }
            if (possibleScores.size() > 0) {
                while (!getCharacter().canAddToScore(selectedScore)) {
                    selectedScore = possibleScores.get((possibleScores.indexOf(selectedScore) + 1) % possibleScores.size());
                }
            }

            HashMap<Score, Integer> levelData = new HashMap<>();
            if (getCharacter().canAddToScore(Score.LUCK)) {
                AttributeScore luck = getCharacter().getScore(Score.LUCK);
                levelData.put(Score.LUCK, luck.getScore());
                luck.setScore(luck.getScore() + 1);
            } else {
                levelData.put(Score.LUCK, 20);
            }

            AttributeScore selectedAttrScore = getCharacter().getScore(selectedScore);

            levelData.put(selectedScore, selectedAttrScore.getScore());
            getScoreLevelChoice().add(levelData);

            selectedAttrScore.setScore(selectedAttrScore.getScore() + 2);
            setAddedHits(getAddedHits() + 4);

            setLevel(getLevel() + 1);
            getCharacter().validateScores();
        }
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

            setAddedHits(getAddedHits() - 4);
            luck.setScore(levelData.get(Score.LUCK));
            lastScoreLeveled.setScore(levelData.get(lastSelectedScore));
            setLevel(getLevel() - 1);
        }
    }

    private void doHeritage() {
        doHeritage(mPhysicalHeritage, mOtherHeritage);
    }

    public void doHeritage(Score physical, Score other) {
        if (!hasHeritage()) {
            Score realPhysical;
            if (physical != null &&
                    (physical.equals(Score.MIGHT) || physical.equals(Score.SKILL))) {
                realPhysical = physical;
            } else {
                realPhysical = Score.MIGHT;
            }
            AttributeScore pScore = getCharacter().getScore(realPhysical);
            pScore.setScore(pScore.getScore() + 2);
            setPhysicalHeritage(physical);

            Score realOther;
            if (other != null &&
                    (other.equals(Score.WITS) || other.equals(Score.WILL) || other.equals(Score.GRACE))) {
                realOther = other;
            } else {
                realOther = Score.WITS;
            }
            AttributeScore oScore = getCharacter().getScore(realOther);
            oScore.setScore(oScore.getScore() + 2);
            setOtherHeritage(other);
        }
    }

    public int getBattleFortuneBonus() {
        return getCharacter().getScore(Score.LUCK).getModifier();
    }

    public ArrayList<HashMap<Score, Integer>> getScoreLevelChoice() {
        return this.mScoreLevelChoice;
    }

    public void setScoreLevelChoice(ArrayList<HashMap<Score, Integer>> scoreLevelChoice) {
        this.mScoreLevelChoice = scoreLevelChoice;
    }

    public Score getPhysicalHeritage() {
        return mPhysicalHeritage;
    }

    public void setPhysicalHeritage(Score physicalHeritage) {
        mPhysicalHeritage = physicalHeritage;
    }

    public Score getOtherHeritage() {
        return mOtherHeritage;
    }

    public void setOtherHeritage(Score otherHeritage) {
        mOtherHeritage = otherHeritage;
    }

    public boolean hasHeritage() {
        return mHasHeritage;
    }

    public void setHasHeritage(boolean hasHeritage) {
        mHasHeritage = hasHeritage;
    }
}
