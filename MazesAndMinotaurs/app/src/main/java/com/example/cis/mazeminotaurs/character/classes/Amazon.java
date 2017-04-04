package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.Character;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.util.Util;

/**
 * Created by jusmith on 4/4/17.
 */

public class Amazon extends Warrior{
    public Amazon(Character character) {
        Score[] primAttributes = {Score.SKILL, Score.GRACE};

        int rolledGold = 0;
        for (int i = 0; i < 3; i++) {
            rolledGold += Util.roll(6);
        }

        setAddedHits(0);
        setBasicHits(12);
        setCharacter(character);
        setExperience(0);
        setLevel(1);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.FEMALE);
        setResId(Classes.AMAZON.getResId());
        setStartGold(rolledGold);
        setWeaponOfChoice(R.string.bow);
    }

    public int getDeadlyShotBonus(){
        return getCharacter().getCoreStatScore(Score.SKILL).getModifier();
    }

    public int getBattleGraceBonus(){
        return getCharacter().getCoreStatScore(Score.GRACE).getModifier();
    }
}
