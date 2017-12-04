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
import java.util.Collections;

/**
 * Created by jusmith on 5/15/17.
 */

public class Spearman extends Warrior {
    public Spearman() {
        this(null);
    }

    public Spearman(PlayerCharacter playerCharacter) {
        setPossibleStartWeapons(new Weapon[]{});
        setPossibleWeaponsOfChoice(new Weapon[]{EquipmentDB.getInstance().getWeapon(CommonStrings.SPEAR.getValue())});


        Score[] primAttrs = {Score.SKILL, Score.WILL};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        int rolledGold = rollDice.roll(6, 3) * 10;

        startGear.add(equipmentDB.getWeapon(CommonStrings.SPEAR.getValue()));
        startGear.add(equipmentDB.getWeapon(CommonStrings.SWORD.getValue()));
        startGear.add(equipmentDB.getWeapon(CommonStrings.DAGGER.getValue()));
        startGear.add(equipmentDB.getArmor(CommonStrings.SHIELD.getValue()));
        startGear.add(equipmentDB.getArmor(CommonStrings.HELMET.getValue()));
        startGear.add(equipmentDB.getArmor(CommonStrings.BREASTPLATE.getValue()));

        setBasicHits(12);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.MALE);
        setResId(Classes.SPEARMAN.getResId());
        setStartMoney(rolledGold);
        setStartGear(startGear);
        setWeaponOfChoice(equipmentDB.getWeapon(CommonStrings.SPEAR.getValue()));
    }

    public int getDefensiveFightingBonus() {
        return getCharacter().getScore(Score.SKILL).getModifier();
    }

    public int getMartialDisciplineBonus() {
        return getCharacter().getScore(Score.WILL).getModifier();
    }
}
