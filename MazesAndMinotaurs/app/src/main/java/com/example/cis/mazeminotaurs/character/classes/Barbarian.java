package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.AttributeScore;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.util.Util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jusmith on 3/31/17.
 */

public class Barbarian extends Warrior {
    private ArrayList<Score> mScoreLevelChoice = new ArrayList<>();

    public Barbarian(PlayerCharacter playerCharacter, int choiceWeapon, int startingMissleWeapon) {
        Score[] primAttributes = {Score.MIGHT, Score.WILL};
        ArrayList<Integer> wepsOfChoice = new ArrayList<>();
        for (int weaponResId: Util.sBarbWeapons) {
            wepsOfChoice.add(weaponResId);
        }

        int rolledGold = 0;
        for (int i =0; i < 3; i++) {
            rolledGold += Util.roll(6);
        }

        setBasicHits(12);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.MALE);
        setResId(Classes.BARBARIAN.getResId());
        // Cannot set up without Equipment made
        // setStartGear();
        setStartGold(rolledGold * 5);

        // If choice weapon isn't valid, just select a random valid choice
        if (wepsOfChoice.contains(choiceWeapon)) {
            setWeaponOfChoice(choiceWeapon);
        } else {
            setWeaponOfChoice(wepsOfChoice.get(Util.roll(wepsOfChoice.size()) - 1));
        }
    }

    @Override
    public void doLevelUp(){
        Score[] possibleScores = {Score.SKILL, Score.WILL, Score.MIGHT};
        doLevelUp(possibleScores[Util.roll(3) - 1]);
    }

    public void doLevelUp(Score score) {
        if (getLevel() < getEffectiveLevel()){

            Score[] selectableScores = {Score.SKILL, Score.WILL, Score.MIGHT};
            ArrayList<Score> possibleScores = new ArrayList<>();
            for (Score selectScore: selectableScores) {
                if(!(getCharacter().getScore(selectScore).getScore() >= 20) ||
                   !(getCharacter().getScore(selectScore).getScore() >= 21 &&
                    Arrays.asList(getPrimaryAttributes()).contains(selectScore))) {
                    possibleScores.add(selectScore);
                }
            }

            Score selectedScore;
            if (possibleScores.contains(score)) {
                selectedScore = score;
            } else {
                selectedScore = possibleScores.get(Util.roll(3) - 1);
            }

            while (getCharacter().getScore(selectedScore).getScore() >= 20 ||
                    (getCharacter().getScore(selectedScore).getScore() >= 21 &&
                    Arrays.asList(getPrimaryAttributes()).contains(selectedScore))) {
                selectedScore = possibleScores.get((possibleScores.indexOf(selectedScore) + 1) % possibleScores.size());
            }

            AttributeScore selectedAttrScore = getCharacter().getScore(selectedScore);
            AttributeScore luck = getCharacter().getScore(Score.LUCK);

            luck.setScore(luck.getScore() + 1);
            selectedAttrScore.setScore(selectedAttrScore.getScore() + 2);
            setAddedHits(getAddedHits() + 4);

            setLevel(getLevel() + 1);
            getScoreLevelChoice().add(selectedScore);
        }
    }

    @Override
    public void doLevelDown(){
        if (getLevel() > 1) {
            AttributeScore luck = getCharacter().getScore(Score.LUCK);
            AttributeScore lastScoreLeveled = getCharacter().getScore(getScoreLevelChoice().get(getScoreLevelChoice().size() - 1));

            setAddedHits(getAddedHits() - 4);
            luck.setScore(luck.getScore() - 1);
            lastScoreLeveled.setScore(lastScoreLeveled.getScore() - 2);
        }
    }

    public ArrayList<Score> getScoreLevelChoice() {
        return mScoreLevelChoice;
    }

    public void setScoreLevelChoice(ArrayList<Score> scoreLevelChoice) {
        mScoreLevelChoice = scoreLevelChoice;
    }

    public int getBattleMightBonus(){
        return getCharacter().getScore(Score.MIGHT).getModifier();
    }

    public int getBattleFuryBonus(){
        return getCharacter().getScore(Score.WILL).getModifier();
    }
}
