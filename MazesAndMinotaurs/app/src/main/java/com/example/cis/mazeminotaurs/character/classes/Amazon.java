package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.AttributeScore;
import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by jusmith on 4/13/17.
 */

public class Amazon extends Warrior implements Level {
    private ArrayList<HashMap<Score, Integer>> mScoreLevelChoice = new ArrayList<>();

    public Amazon(PlayerCharacter playerCharacter, Weapon startingMeleeWeapon){
        Score[] primAttrs = {Score.SKILL, Score.GRACE};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        // Setup for checking starting gear
        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Weapon> possibleWeapons = new ArrayList<>();
        for (int id: Util.sMeleeWeapons) {
            if (id != R.string.dagger) {
                possibleWeapons.add(equipmentDB.getWeapon(id));
            }
        }
        ArrayList<Equipment> startGear = new ArrayList<>();

        // Check the starting melee weapon if it is valid
        if (possibleWeapons.contains(startingMeleeWeapon)) {
            startGear.add(startingMeleeWeapon);
        } else {
            startGear.add(possibleWeapons.get(0));
        }

        // Adding the rest of the equipment
        startGear.add(equipmentDB.getWeapon(R.string.dagger));
        startGear.add(equipmentDB.getArmor(R.string.shield));
        startGear.add(equipmentDB.getWeapon(R.string.bow));
        startGear.add(equipmentDB.getWeapon(R.string.arrows));

        // Roll for gold
        int rolledGold = Util.roll(6, 3) * 5;

        setBasicHits(12);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.FEMALE);
        setResId(Classes.AMAZON.getResId());
        setStartGold(rolledGold);
        setStartGear(startGear);
        setWeaponOfChoice(equipmentDB.getWeapon(R.string.bow));
    }

    @Override
    public void doLevelUp() {
        Score[] possibleScores = {Score.GRACE, Score.SKILL, Score.WILL, Score.MIGHT, Score.WITS};
        doLevelUp(possibleScores[Util.roll(possibleScores.length) - 1]);
    }
    @Override
    public void doLevelUp(Score score) {
        if (getLevel() < getEffectiveLevel()) {
            Score[] choices = {Score.GRACE, Score.SKILL, Score.WILL, Score.MIGHT, Score.WITS};
            ArrayList<Score> possibleScores = new ArrayList<>();
            for (Score selectScore: choices) {
                if (getCharacter().canAddToScore(selectScore)) {
                    possibleScores.add(selectScore);
                }
            }

            Score selectedScore;
            if (possibleScores.contains(score)) {
                selectedScore = score;
            } else {
                selectedScore = possibleScores.get(Util.roll(possibleScores.size()) - 1);
            }
            if (possibleScores.size() > 0) {
                while(!getCharacter().canAddToScore(selectedScore)) {
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
    @Override
    public void doLevelDown(){
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

            setAddedHits(getAddedHits() - 4);
            luck.setScore(levelData.get(Score.LUCK));
            lastScoreLeveled.setScore(levelData.get(lastSelectedScore));
            setLevel(getLevel() - 1);
        }
    }

    public ArrayList<HashMap<Score, Integer>> getScoreLevelChoice() {
        return mScoreLevelChoice;
    }

    public void setScoreLevelChoice(ArrayList<HashMap<Score, Integer>> scoreLevelChoice) {
        mScoreLevelChoice = scoreLevelChoice;
    }

    public int getDeadlyShotBonus() {
        return getCharacter().getScore(Score.SKILL).getModifier();
    }

    public int getBattleGraceBonus() {
        return getCharacter().getScore(Score.GRACE).getModifier();
    }
}
