package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.AttributeScore;
import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by jusmith on 3/31/17.
 */

public class Barbarian extends Warrior implements Level{
    private ArrayList<HashMap<Score, Integer>> mScoreLevelChoice = new ArrayList<>();

    public Barbarian() {
        this(null, null, null);
    }

    public Barbarian(PlayerCharacter playerCharacter, Weapon weaponOfChoice, Weapon startWeapon) {
        setPossibleStartWeapons(new Weapon[]{
                EquipmentDB.getInstance().getWeapon(R.string.bow),
                EquipmentDB.getInstance().getWeapon(R.string.javelin),
                EquipmentDB.getInstance().getWeapon(R.string.sling),
        });

        setPossibleWeaponsOfChoice(new Weapon[] {
                EquipmentDB.getInstance().getWeapon(R.string.barb_axe),
                EquipmentDB.getInstance().getWeapon(R.string.barb_club),
                EquipmentDB.getInstance().getWeapon(R.string.barb_mace),
                EquipmentDB.getInstance().getWeapon(R.string.barb_sword),
        });

        Score[] primAttrs = {Score.MIGHT, Score.WILL};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        EquipmentDB equipDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        //Equipment checks
        setWeaponOfChoice(weaponOfChoice);
        startGear.add(getWeaponOfChoice());

        if (Arrays.asList(getPossibleStartWeapons()).contains(startWeapon)){
            switch (startWeapon.getResId()) {
                case R.string.bow:
                    startGear.add(equipDB.getWeapon(R.string.bow));
                    startGear.add(equipDB.getWeapon(R.string.arrows));
                    break;
                case R.string.javelin:
                    startGear.add(equipDB.getWeapon(R.string.javelin));
                break;
                case R.string.sling:
                    startGear.add(equipDB.getWeapon(R.string.sling));
                    startGear.add(equipDB.getWeapon(R.string.slingshot));
                    break;
                default:
                    //PANIC
                    System.out.println("PANIC in Barbarian Ranged Weapon Case");
                    break;
            }
        } else {
            startGear.add(equipDB.getWeapon(R.string.bow));
            startGear.add(equipDB.getWeapon(R.string.arrows));
        }

        int rolledGold = Util.roll(6, 3) * 5;

        //Add the rest of starting equipment
        startGear.add(equipDB.getWeapon(R.string.dagger));
        startGear.add(equipDB.getArmor(R.string.shield));

        setBasicHits(12);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.MALE);
        setResId(Classes.BARBARIAN.getResId());
        setStartMoney(rolledGold * 5);
        setStartGear(startGear);
    }

    public void doLevelUp(){
        Score[] possibleScores = {Score.SKILL, Score.WILL, Score.MIGHT};
        doLevelUp(possibleScores[Util.roll(3) - 1]);
    }

    public void doLevelUp(Score score) {
        if (getLevel() < getEffectiveLevel()){

            Score[] choices = {Score.SKILL, Score.WILL, Score.MIGHT};
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
            setAddedHits(getAddedHits() + 4);

            setLevel(getLevel() + 1);
            getCharacter().validateScores();
        }
    }

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
        return this.mScoreLevelChoice;
    }

    public void setScoreLevelChoice(ArrayList<HashMap<Score, Integer>> scoreLevelChoice) {
        this.mScoreLevelChoice = scoreLevelChoice;
    }

    public int getBattleMightBonus(){
        return getCharacter().getScore(Score.MIGHT).getModifier();
    }

    public int getBattleFuryBonus(){
        return getCharacter().getScore(Score.WILL).getModifier();
    }
}
