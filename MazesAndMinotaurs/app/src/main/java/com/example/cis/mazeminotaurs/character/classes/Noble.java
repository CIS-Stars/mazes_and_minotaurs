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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by jusmith on 5/15/17.
 */

public class Noble extends Warrior implements Level {
    private ArrayList<HashMap<Score, Integer>> mScoreLevelChoice = new ArrayList<>();

    public Noble() {
        this(null,null,null,null);
    }

    public Noble(PlayerCharacter playerCharacter, Weapon weaponOfChoice, Score martialHeritage, Score mentalHeritage) {
        setPossibleStartWeapons(new Weapon[]{});
        setPossibleWeaponsOfChoice(new Weapon[]{
                EquipmentDB.getInstance().getWeapon(R.string.bow),
                EquipmentDB.getInstance().getWeapon(R.string.javelin),
                EquipmentDB.getInstance().getWeapon(R.string.spear),
                EquipmentDB.getInstance().getWeapon(R.string.sword),
        });

        Score martialScore;
        if (martialHeritage != null && (martialHeritage.equals(Score.MIGHT) || martialHeritage.equals(Score.SKILL))) {
            martialScore = martialHeritage;
        } else {
            martialScore = Score.MIGHT;
        }
        // Noble - Heroic Heritage
        Score mentalScore;
        if (mentalHeritage != null &&
                (mentalHeritage.equals(Score.WITS) ||
                        mentalHeritage.equals(Score.WILL) ||
                        mentalHeritage.equals(Score.GRACE))) {
            mentalScore = mentalHeritage;
        } else {
            mentalScore = Score.WITS;
        }

        Score[] primAttrs = {martialScore, Score.LUCK};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        // Check if the weapon of choice is valid
        setWeaponOfChoice(weaponOfChoice);

        int rolledGold = rollDice.roll(6, 3) * 100;

        startGear.add(equipmentDB.getWeapon(R.string.sword));
        startGear.add(equipmentDB.getWeapon(R.string.dagger));
        startGear.add(equipmentDB.getArmor(R.string.shield));
        startGear.add(equipmentDB.getArmor(R.string.helmet));
        startGear.add(equipmentDB.getArmor(R.string.breastplate));

        setBasicHits(12);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.EITHER);
        setResId(Classes.NOBLE.getResId());
        setStartMoney(rolledGold);
        setStartGear(startGear);

        // TODO find a way to get around this hack-y method.
        /* Explanation
            If the Noble is chosen for the new character it will crash due to a lack of
            Character. However, we still need access to the code of the constructor.
         */
        if (getCharacter() != null) {
            getCharacter().getScore(martialScore).setScore(getCharacter().getScore(martialScore).getScore() + 2);
            getCharacter().getScore(mentalScore).setScore(getCharacter().getScore(mentalScore).getScore() + 2);
        }
    }

    @Override
    public void doLevelUp() {
        Score[] possibleScores = {Score.GRACE, Score.SKILL, Score.WILL, Score.MIGHT, Score.WITS};
        doLevelUp(possibleScores[rollDice.roll(possibleScores.length) - 1]);
    }

    @Override
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

    @Override
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

    public void doHeritage(Score physical, Score mental) {

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
}
