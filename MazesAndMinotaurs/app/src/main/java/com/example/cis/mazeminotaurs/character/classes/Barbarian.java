package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.AttributeScore;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.Character;
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

    public Barbarian(Character character, int choiceWeapon, int startingMissleWeapon) {
        Score[] primAttributes = {Score.MIGHT, Score.WILL};
        ArrayList<Integer> wepsOfChoice = new ArrayList<>();
        wepsOfChoice.add(R.string.barb_axe);
        wepsOfChoice.add(R.string.barb_club);
        wepsOfChoice.add(R.string.barb_mace);
        wepsOfChoice.add(R.string.barb_sword);

        int rolledGold = 0;
        for (int i =0; i < 3; i++) {
            rolledGold += Util.roll(6);
        }

        setBasicHits(12);
        setCharacter(character);
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

            Score[] possibleScores = {Score.SKILL, Score.WILL, Score.MIGHT};
            Score selectedScore;
            if (Arrays.asList(possibleScores).contains(score)) {
                selectedScore = score;
            } else {
                selectedScore = possibleScores[Util.roll(3) - 1];
            }

            AttributeScore selectedAttrScore = getCharacter().getCoreStatScore(selectedScore);
            AttributeScore luck = getCharacter().getCoreStatScore(Score.LUCK);

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
            AttributeScore luck = getCharacter().getCoreStatScore(Score.LUCK);
            AttributeScore lastScoreLeveled = getCharacter().getCoreStatScore(getScoreLevelChoice().get(getScoreLevelChoice().size() - 1));

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
        return getCharacter().getCoreStatScore(Score.MIGHT).getModifier();
    }

    public int getBattleFuryBonus(){
        return getCharacter().getCoreStatScore(Score.WILL).getModifier();
    }
}
