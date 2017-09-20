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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by jsmith on 9/11/17.
 */

public class Hunter extends Specialist implements Level{
    private ArrayList<HashMap<Score, Integer>> mScoreLevelChoice = new ArrayList<>();

    public Hunter() {
        this(null,null);
    }

    public Hunter(PlayerCharacter playerCharacter, Weapon weaponOfChoice) {
        // Null for this value means that it is linked to weapon of choice.
        setPossibleStartWeapons(null);
        setPossibleWeaponsOfChoice(new Weapon[] {
                EquipmentDB.getInstance().getWeapon(R.string.bow),
                EquipmentDB.getInstance().getWeapon(R.string.javelin),
                EquipmentDB.getInstance().getWeapon(R.string.sling),
        });

        Score[] primAttrs = {Score.SKILL, Score.WITS};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        // Setting up for equipment check
        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        setWeaponOfChoice(weaponOfChoice);
        startGear.add(getWeaponOfChoice());

        switch (getWeaponOfChoice().getResId()) {
            case R.string.bow:
                startGear.add(equipmentDB.getWeapon(R.string.bow));
                startGear.add(equipmentDB.getWeapon(R.string.arrows));
                break;
            case R.string.javelin:
                startGear.add(equipmentDB.getWeapon(R.string.javelin));
                break;
            case R.string.sling:
                startGear.add(equipmentDB.getWeapon(R.string.sling));
                startGear.add(equipmentDB.getWeapon(R.string.slingshot));
                break;
        }// Equipment done

        startGear.add(equipmentDB.getWeapon(R.string.dagger));
        startGear.add(equipmentDB.getWeapon(R.string.spear));

        int rolledGold = Util.roll(6, 3) * 5;

        setBasicHits(10);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setResId(Classes.HUNTER.getResId());
        setRequiredGender(Gender.EITHER);
        setSpecialScoreId(R.string.hunter_talent);
        setStartMoney(rolledGold);
        setStartGear(startGear);
    }

    @Override
    public void doLevelUp() {
        Score[] possibleScores = {Score.SKILL, Score.WILL, Score.WITS};
        doLevelUp(possibleScores[Util.roll(possibleScores.length) - 1]);
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
                selectedScore = possibleScores.get(Util.roll(possibleScores.size()) - 1);
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
    
    public int getDeadlyAimBonus() {
        return getCharacter().getScore(Score.SKILL).getScore();
    }

    public ArrayList<HashMap<Score, Integer>> getScoreLevelChoice() {
        return this.mScoreLevelChoice;
    }

    public void setScoreLevelChoice(ArrayList<HashMap<Score, Integer>> scoreLevelChoice) {
        this.mScoreLevelChoice = scoreLevelChoice;
    }

}
