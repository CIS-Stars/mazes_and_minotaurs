package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.util.CommonStrings;
import com.example.cis.mazeminotaurs.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by jusmith on 5/15/17.
 */

public class Centaur extends Warrior {

    public Centaur() {
        this(null,null,null);
    }

    public Centaur(PlayerCharacter playerCharacter, Weapon weaponOfChoice, Weapon startingWeapon) {
        setPossibleStartWeapons(new Weapon[]{
                EquipmentDB.getInstance().getWeapon(CommonStrings.BOW.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.JAVELIN.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SLING.getValue()),
        });
        setPossibleWeaponsOfChoice(new Weapon[] {
                EquipmentDB.getInstance().getWeapon(CommonStrings.BOW.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.CLUB.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.JAVELIN.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SPEAR.getValue()),
        });


        Score[] primAttrs = {Score.MIGHT, Score.SKILL};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        // Setting up for equipment check
        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        setWeaponOfChoice(weaponOfChoice);;

        Weapon finalStartingWeapon;
        if (Arrays.asList(getPossibleStartWeapons()).contains(startingWeapon)) {
            finalStartingWeapon = startingWeapon;
        } else {
            finalStartingWeapon = getPossibleStartWeapons()[0];
        }

        startGear.add(finalStartingWeapon);
        Equipment ammo = Util.getAmmo(finalStartingWeapon);
        if (ammo != null) {startGear.add(ammo);}
        // Equipment done

        int rolledGold = 0;

        startGear.add(equipmentDB.getWeapon(CommonStrings.DAGGER.getValue()));
        startGear.add(equipmentDB.getWeapon(CommonStrings.SPEAR.getValue()));

        setBasicHits(12);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setResId(Classes.CENTAUR.getResId());
        setRequiredGender(Gender.MALE);
        setStartMoney(rolledGold);
        setStartGear(startGear);
    }

    public int getExtraoridnaryAgilityBonus(){
        return getCharacter().getScore(Score.SKILL).getModifier();
    }
}
