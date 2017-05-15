package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by jusmith on 4/13/17.
 */

public class Amazon extends Warrior implements Level {
    private ArrayList<HashMap<Score, Integer>> mScoreLevelChoice = new ArrayList<>();

    public Amazon(PlayerCharacter playerCharacter, Weapon startingMeleeWeapon){
        Score[] primAttrs = {Score.SKILL, Score.GRACE};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        // Setup for checking starting gear
        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Weapon> possibleWeapons = new ArrayList<>();
        for (int id: Util.sMeleeWeapons) {
            if (id != R.string.dagger) {
                possibleWeapons.add(equipmentDB.getWeapon(id));
            }
        }
        ArrayList<Equipment> startGear = new ArrayList<>();

        // Check the starting melee weapon if it is valid
        if (possibleWeapons.contains(startingMeleeWeapon)) {
            startGear.add(startingMeleeWeapon);
        } else {
            startGear.add(possibleWeapons.get(0));
        }

        // Roll for gold
        int rolledGold = Util.roll(6, 3);

    }

    @Override
    public void doLevelUp() {
    }
    @Override
    public void doLevelUp(Score score) {
    }
    @Override
    public void doLevelDown(){

    }
}
