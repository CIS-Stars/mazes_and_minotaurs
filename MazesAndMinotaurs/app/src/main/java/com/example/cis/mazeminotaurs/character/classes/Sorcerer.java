package com.example.cis.mazeminotaurs.character.classes;

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
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by zsteck on 9/12/17.
 */

public class Sorcerer extends Magician {

    public Sorcerer() {
        this(null,null);
    }

    public Sorcerer(PlayerCharacter playerCharacter, Weapon startingWeapon) {
        setPossibleStartWeapons(new Weapon[] {
                EquipmentDB.getInstance().getWeapon(CommonStrings.DAGGER.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.STAFF.getValue()),
        });

        Score[] primAttrs = {Score.WITS, Score.WILL};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        int rolledGold = rollDice.roll(6, 3) * 10;

        //TODO add a wand weapon

        if (Arrays.asList(getPossibleStartWeapons()).contains(startingWeapon)) {
            startGear.add(startingWeapon);
        } else {
            startGear.add(getPossibleStartWeapons()[0]);
        }

        setBasicHits(8);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.EITHER);
        setResId(Classes.SORCEROR.getResId());
        setSpecialTalentResId(R.string.sorcerer_talent);
        setStartMoney(rolledGold);
        setStartGear(startGear);
    }

    public int getMysticalStrength(){
        return 12 + getSpecialTalent();
    }

    public int getPowerPoints(){
        return (4 * getLevel()) + getCharacter().getScore(Score.GRACE).getModifier();
    }
}