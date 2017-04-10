package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.util.Util;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jusmith on 4/4/17.
 */

public class Amazon extends Warrior{
    public Amazon(PlayerCharacter playerCharacter) {
        Score[] primAttrs = {Score.SKILL, Score.GRACE};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        int rolledGold = 0;
        for (int i = 0; i < 3; i++) {
            rolledGold += Util.roll(6);
        }

        setAddedHits(0);
        setBasicHits(12);
        setCharacter(playerCharacter);
        setExperience(0);
        setLevel(1);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.FEMALE);
        setResId(Classes.AMAZON.getResId());
        setStartGold(rolledGold);
        setWeaponOfChoice(R.string.bow);
    }

    public int getDeadlyShotBonus(){
        return getCharacter().getScore(Score.SKILL).getModifier();
    }

    public int getBattleGraceBonus(){
        return getCharacter().getScore(Score.GRACE).getModifier();
    }
}
