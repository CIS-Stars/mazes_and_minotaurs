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
import com.example.cis.mazeminotaurs.util.Util;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jsmith on 9/11/17.
 */

public class Hunter extends Specialist {
    public Hunter() {
        this(null,null);
    }

    public Hunter(PlayerCharacter playerCharacter, Weapon weaponOfChoice) {
        // Null for this value means that it is linked to weapon of choice.
        setPossibleStartWeapons(null);
        setPossibleWeaponsOfChoice(new Weapon[] {
                EquipmentDB.getInstance().getWeapon(CommonStrings.BOW.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.JAVELIN.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SLING.getValue()),
        });

        Score[] primAttrs = {Score.SKILL, Score.WITS};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        // Setting up for equipment check
        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        setWeaponOfChoice(weaponOfChoice);
        startGear.add(getWeaponOfChoice());
        Equipment ammo = Util.getAmmo(getWeaponOfChoice());
        startGear.add(ammo);// Equipment done

        startGear.add(equipmentDB.getWeapon(CommonStrings.DAGGER.getValue()));
        startGear.add(equipmentDB.getWeapon(CommonStrings.SPEAR.getValue()));

        int rolledGold = rollDice.roll(6, 3) * 5;

        setBasicHits(10);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setResId(Classes.HUNTER.getResId());
        setRequiredGender(Gender.EITHER);
        setSpecialScoreId(R.string.hunter_talent);
        setStartMoney(rolledGold);
        setStartGear(startGear);
    }
    
    public int getDeadlyAimBonus() {
        return getCharacter().getScore(Score.SKILL).getScore();
    }
}
