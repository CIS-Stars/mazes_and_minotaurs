package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.rollDice.rollDice;
import com.example.cis.mazeminotaurs.util.CommonStrings;
import com.example.cis.mazeminotaurs.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by jusmith on 3/31/17.
 */

public class Barbarian extends Warrior {

    public Barbarian() {
        this(null, null, null);
    }

    public Barbarian(PlayerCharacter playerCharacter, Weapon weaponOfChoice, Weapon startWeapon) {
        setPossibleStartWeapons(new Weapon[]{
                EquipmentDB.getInstance().getWeapon(CommonStrings.BOW.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.JAVELIN.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SLING.getValue()),
        });

        setPossibleWeaponsOfChoice(new Weapon[] {
                EquipmentDB.getInstance().getWeapon(CommonStrings.BARB_AXE.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.BARB_CLUB.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.BARB_MACE.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.BARB_SWORD.getValue()),
        });

        // The scores that can be used for level ups.
        setPossibleLevelScores(new Score[]{Score.SKILL, Score.WILL, Score.MIGHT});

        Score[] primAttrs = {Score.MIGHT, Score.WILL};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        EquipmentDB equipDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        //Equipment checks
        setWeaponOfChoice(weaponOfChoice);
        startGear.add(getWeaponOfChoice());

        if (Arrays.asList(getPossibleStartWeapons()).contains(startWeapon)){
            startGear.add(startWeapon);
            Equipment ammo = Util.getAmmo(startWeapon);
            if (ammo != null) {startGear.add(ammo);}
        } else {
            startGear.add(equipDB.getWeapon(CommonStrings.BOW.getValue()));
            startGear.add(equipDB.getWeapon(CommonStrings.ARROWS.getValue()));
        }

        int rolledGold = rollDice.roll(6, 3) * 5;

        //Add the rest of starting equipment
        startGear.add(equipDB.getWeapon(CommonStrings.DAGGER.getValue()));
        startGear.add(equipDB.getArmor(CommonStrings.SHIELD.getValue()));

        setBasicHits(12);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.MALE);
        setResId(Classes.BARBARIAN.getResId());
        setStartMoney(rolledGold * 5);
        setStartGear(startGear);
    }

    public int getBattleMightBonus(){
        return getCharacter().getScore(Score.MIGHT).getModifier();
    }

    public int getBattleFuryBonus(){
        return getCharacter().getScore(Score.WILL).getModifier();
    }
}
