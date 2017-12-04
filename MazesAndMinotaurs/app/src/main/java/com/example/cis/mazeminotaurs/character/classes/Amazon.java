package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
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
 * Created by jusmith on 4/13/17.
 */

public class Amazon extends Warrior {

    public Amazon() {
        this(null,null);
    }

    public Amazon(PlayerCharacter playerCharacter, Weapon startingWeapon){
        setPossibleStartWeapons(new Weapon[]{
                EquipmentDB.getInstance().getWeapon(CommonStrings.AXE.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SPEAR.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SWORD.getValue()),
        });

        setPossibleWeaponsOfChoice(new Weapon[]{
                EquipmentDB.getInstance().getWeapon(CommonStrings.BOW.getValue()),
        });

        Score[] primAttrs = {Score.SKILL, Score.GRACE};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        // Setup for checking starting gear
        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        // Check the starting melee weapon if it is valid
        if (Arrays.asList(getPossibleStartWeapons()).contains(startingWeapon)) {
            startGear.add(startingWeapon);
        } else {
            startGear.add(getPossibleStartWeapons()[0]);
        }
        setWeaponOfChoice(equipmentDB.getWeapon(CommonStrings.BOW.getValue()));

        // Adding the rest of the equipment
        startGear.add(equipmentDB.getWeapon(CommonStrings.DAGGER.getValue()));
        startGear.add(equipmentDB.getArmor(CommonStrings.SHIELD.getValue()));
        startGear.add(equipmentDB.getWeapon(CommonStrings.BOW.getValue()));
        startGear.add(equipmentDB.getWeapon(CommonStrings.ARROWS.getValue()));

        // Roll for gold
        int rolledGold = rollDice.roll(6, 3) * 5;

        setBasicHits(12);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.FEMALE);
        setResId(Classes.AMAZON.getResId());
        setStartMoney(rolledGold);
        setStartGear(startGear);

    }

    public int getDeadlyShotBonus() {
        return getCharacter().getScore(Score.SKILL).getModifier();
    }

    public int getBattleGraceBonus() {
        return getCharacter().getScore(Score.GRACE).getModifier();
    }
}