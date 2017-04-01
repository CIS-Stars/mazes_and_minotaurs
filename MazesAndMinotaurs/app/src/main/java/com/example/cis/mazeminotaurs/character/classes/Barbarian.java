package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.Character;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.util.Util;

import java.util.ArrayList;

/**
 * Created by jusmith on 3/31/17.
 */

public class Barbarian extends Warrior {

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

        setAddedHits(0);
        setBasicHits(12);
        setCharacter(character);
        setExperience(0);
        setLevel(1);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.MALE);
        setResId(R.string.barbarian);
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

    public int getBattleMightBonus(){
        return getCharacter().getMod(Score.MIGHT);
    }

    public int getBattleFuryBonus(){
        return getCharacter().getMod(Score.WILL);
    }
}
