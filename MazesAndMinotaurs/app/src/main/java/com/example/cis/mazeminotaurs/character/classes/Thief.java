package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.AttributeScore;
import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.R;
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
 * Created by jsmith on 9/11/17.
 */

public class Thief extends Specialist implements Level{
    private ArrayList<HashMap<Score, Integer>> mScoreLevelChoice = new ArrayList<>();

    public Thief() {
        this(null,null);
    }

    public Thief(PlayerCharacter playerCharacter, Weapon weaponOfChoice) {
        setPossibleStartWeapons(new Weapon[]{});
        setPossibleWeaponsOfChoice(new Weapon[] {
                EquipmentDB.getInstance().getWeapon(CommonStrings.BOW.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SLING.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.THROW_KNIFE.getValue()),
        });

        Score[] primAttrs = {Score.LUCK, Score.WITS};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        // Setting up for equipment check
        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Weapon> possibleWepsOfChoice = new ArrayList<>();
        ArrayList<Equipment> startGear = new ArrayList<>();

        for (String choiceId : new String[]{CommonStrings.DAGGER.getValue(), CommonStrings.SLING.getValue(), CommonStrings.THROW_KNIFE.getValue()}) {
            possibleWepsOfChoice.add(equipmentDB.getWeapon(choiceId));
        }

        if (possibleWepsOfChoice.contains(weaponOfChoice)) {
            setWeaponOfChoice(weaponOfChoice);
        } else {
            setWeaponOfChoice(possibleWepsOfChoice.get(0));
        }

        startGear.add(equipmentDB.getWeapon(CommonStrings.DAGGER.getValue()));
        // Equipment done

        int rolledGold = rollDice.roll(6, 3) * 5;

        setBasicHits(10);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setResId(Classes.THIEF.getResId());
        setRequiredGender(Gender.EITHER);
        setSpecialScoreId(R.string.thief_talent);
        setStartMoney(rolledGold);
        setStartGear(startGear);
    }

    @Override
    public void doLevelUp() {
        Score[] possibleScores = {Score.SKILL, Score.WILL, Score.WITS};
        doLevelUp(possibleScores[rollDice.roll(possibleScores.length) - 1]);
    }

    @Override
    public void doLevelUp(Score score) {
        if (getLevel() < getEffectiveLevel()) {
            Score[] choices = {Score.SKILL, Score.WILL, Score.WITS};
            ArrayList<Score> possibleScores = new ArrayList<>();
            for (Score selectScore: choices) {
                if(getCharacter().canAddToScore(selectScore)) {
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

            // Contains information about changed scores
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
            setAddedHits(getAddedHits() + 2);

            setLevel(getLevel() + 1);
            getCharacter().validateScores();
        }
    }

    @Override
    public void doLevelDown() {
        if (getLevel() > 1) {
            HashMap<Score, Integer> levelData = getScoreLevelChoice().remove(getScoreLevelChoice().size() - 1);
            Score lastSelectedScore = null;
            for (Object rawScore: levelData.keySet().toArray()) {
                Score score = (Score) rawScore;
                if (score != Score.LUCK) {
                    lastSelectedScore = score;
                    break;
                }
            }

            AttributeScore luck = getCharacter().getScore(Score.LUCK);
            AttributeScore lastScoreLeveled = getCharacter().getScore(lastSelectedScore);

            setAddedHits(getAddedHits() - 2);
            luck.setScore(levelData.get(Score.LUCK));
            lastScoreLeveled.setScore(levelData.get(lastSelectedScore));
            setLevel(getLevel() - 1);
        }
    }

    public int getEvasionBonus() {
        return getCharacter().getScore(Score.WITS).getScore();
    }

    public ArrayList<HashMap<Score, Integer>> getScoreLevelChoice() {
        return this.mScoreLevelChoice;
    }

    public void setScoreLevelChoice(ArrayList<HashMap<Score, Integer>> scoreLevelChoice) {
        this.mScoreLevelChoice = scoreLevelChoice;
    }
}
